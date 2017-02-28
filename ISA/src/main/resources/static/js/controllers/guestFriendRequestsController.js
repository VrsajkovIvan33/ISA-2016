/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestFriendRequestsController', [])
       .controller('GuestFriendRequestsController', function($localStorage, $scope, $uibModal,$location ,$stomp, $log, toastr, GuestFriendRequestsFactory){
           function init(){
               if($localStorage.logged == null)
                   $location.path("/");
               else {
                   if ($localStorage.logged.type != 'GUEST')
                       $location.path("/");
                   else {
                       $scope.loggedUser = $localStorage.logged;
                       $scope.friendRequestsNumber = 0;
                       $scope.showRequests = false;
                       GuestFriendRequestsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function (data) {
                           $scope.friendRequestsNumber = data;
                           if (data > 0)
                               $scope.showRequests = true;
                       });
                       $scope.friendRequests = [];
                       GuestFriendRequestsFactory.getFriendRequests($scope.loggedUser.id).success(function (data) {
                           if (data != null) {
                               $scope.friendRequests = data;
                           } else {
                               alert("Error, try again!");
                           }
                       });
                   }
               }
           };

           var friendRequestSubscription = null;
           var acceptedFriendRequestSubscription = null;
           init();

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
               .then(function(frame){
                   friendRequestSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                       toastr.info('You have new friend request!');
                       $scope.friendRequestsNumber = numberOfRequests;
                       if(numberOfRequests > 0)
                           $scope.showRequests = true;
                       GuestFriendRequestsFactory.getFriendRequests($scope.loggedUser.id).success(function(data){
                           if(data != null) {
                               $scope.friendRequests = data;
                           }else{
                               alert("Error, try again!");
                           }
                       });
                   });

                   acceptedFriendRequestSubscription = $stomp.subscribe('/topic/friendAcceptedRequest/' + $localStorage.logged.id, function(friend, headers, res){
                       toastr.info(friend.name + ' ' + friend.surname + ' accepted friend request.');
                   });
               });

           $scope.logOut = function(){
               $scope.disconnect();
               $localStorage.logged = null;
               $location.path("/");
           };

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };

           $scope.accept = function(id){
               $stomp.send('/app/acceptFriendRequest/' + $scope.loggedUser.id + '/' + id);
               var temp = [];
               for(i = 0; i<$scope.friendRequests.length; i++){
                   if($scope.friendRequests[i].id != id)
                       temp.push($scope.friendRequests[i]);
               }
               $scope.friendRequests = temp;
               $scope.friendRequestsNumber -= 1;
               if($scope.friendRequestsNumber > 0)
                   $scope.showRequests = true;
               else
                   $scope.showRequests = false;
           }

           $scope.ignore = function(id){
               GuestFriendRequestsFactory.ignoreFriendRequest($scope.loggedUser.id, id).success(function(data){
                   var temp = [];
                   for(i = 0; i<$scope.friendRequests.length; i++){
                       if($scope.friendRequests[i].id != id)
                           temp.push($scope.friendRequests[i]);
                   }
                   $scope.friendRequests = temp;
                   $scope.friendRequestsNumber -= 1;
                   if($scope.friendRequestsNumber > 0)
                       $scope.showRequests = true;
                   else
                       $scope.showRequests = false;
               });
           }
       });

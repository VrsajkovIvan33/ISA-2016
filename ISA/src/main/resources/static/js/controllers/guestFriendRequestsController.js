/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestFriendRequestsController', [])
       .controller('GuestFriendRequestsController', function($localStorage, $scope, $uibModal, $stomp, $log, toastr, GuestFriendRequestsFactory){
           function init(){
               $scope.loggedUser = $localStorage.logged;
               $scope.friendRequestsNumber = 0;
               $scope.showRequests = false;
               GuestFriendRequestsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function(data){
                   $scope.friendRequestsNumber = data;
                   if(data > 0)
                       $scope.showRequests = true;
               });
               $scope.friendRequests = [];
               GuestFriendRequestsFactory.getFriendRequests($scope.loggedUser.id).success(function(data){
                   if(data != null) {
                       $scope.friendRequests = data;
                   }else{
                       alert("Error, try again!");
                   }
               });
           };

           var friendRequestSubscription = null;
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
               });

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };
       });

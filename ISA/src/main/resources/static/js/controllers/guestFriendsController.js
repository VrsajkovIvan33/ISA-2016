/**
 * Created by Nole on 2/15/2017.
 */

angular.module('restaurantApp.GuestFriendsController', [])
       .controller('GuestFriendsController', function ($localStorage, $scope, $uibModal, $stomp, $log,  toastr, GuestFriendsFactory){
           function init(){
               $scope.loggedUser = $localStorage.logged;
               $scope.friends = [];
               $scope.viewMode = 'not';
               GuestFriendsFactory.getFriends($scope.loggedUser.id).success(function(data){
                   if(data != null){
                       $scope.friends = data;
                   }else{
                       alert("Error, try again!");
                   }
               });
               $scope.friendRequestsNumber = 0;
               $scope.showRequests = false;
               GuestFriendsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function(data){
                   $scope.friendRequestsNumber = data;
                   if(data > 0)
                       $scope.showRequests = true;
               });
           };

           var friendRequestsSubscription = null;
           var acceptedFriendRequestSubscription = null;
           var deleteFriendSubscription = null;
           init();

           $scope.sortByName = function(){
               $scope.viewMode = 'name';
           }

           $scope.sortBySurname = function(){
               $scope.viewMode = 'surname';
           }

           $scope.resetSort = function(){
               $scope.viewMode = 'not';
           }

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
                 .then(function(frame){
                     friendRequestsSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                         toastr.info('You have new friend request!');
                         $scope.friendRequestsNumber = numberOfRequests;
                         if(numberOfRequests > 0)
                             $scope.showRequests = true;
                     });

                     acceptedFriendRequestSubscription = $stomp.subscribe('/topic/friendAcceptedRequest/' + $localStorage.logged.id, function(friend, headers, res){
                         toastr.info(friend.name + ' ' + friend.surname + ' accepted friend request.');
                         GuestFriendsFactory.getFriends($scope.loggedUser.id).success(function(data){
                             if(data != null){
                                 $scope.friends = data;
                             }else{
                                 alert("Error, try again!");
                             }
                         });
                     });

                     deleteFriendSubscription = $stomp.subscribe('/topic/deleteFriend/' + $localStorage.logged.id, function(friend, headers, res){
                         GuestFriendsFactory.getFriends($scope.loggedUser.id).success(function(data){
                             if(data != null){
                                 $scope.friends = data;
                             }else{
                                 alert("Error, try again!");
                             }
                         });
                     });
                 });

           $scope.disconnect = function(){
               friendRequestsSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               deleteFriendSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };

           $scope.openSearchPeopleModal = function(){
                $uibModal.open({
                    templateUrl : 'html/guest/searchPeopleModal.html',
                    controller : 'SearchPeopleController'
                });
            }

            $scope.delete = function(friendId){
                $stomp.send('/app/deleteFriend/' + $scope.loggedUser.id + '/' + friendId);
                var temp = [];
                for(i = 0; i<$scope.friends.length; i++){
                    if($scope.friends[i].id != friendId)
                        temp.push($scope.friends[i]);
                }
                $scope.friends = temp;
            }
       })

       .controller('SearchPeopleController', function($localStorage, $scope, $stomp, $uibModalInstance, $log, toastr, $location, GuestFriendsFactory){
            function init(){
                $scope.foundPersons = [];
            };

           var subscription = null;
           init();

           $stomp.setDebug(function (args) {
               $log.debug(args)
           });

           $stomp.connect('/stomp', {})
                 .then(function(frame){
                     subscription = $stomp.subscribe('/topic/persons/' + $localStorage.logged.id, function(persons, headers, res){
                         $scope.$apply(function(){
                             $scope.foundPersons = persons;
                         });
                     }, {});
                 });

           $scope.search = function(personForSearch){
               var message = { 'message' : personForSearch };
               $stomp.send('/app/searchPersons/' + $localStorage.logged.id, message);
           };

           $scope.addFriend = function(id){
               $stomp.send('/app/addFriend/' + $localStorage.logged.id + '/' + id);
               var temp = [];
               for(i=0; i<$scope.foundPersons.length; i++){
                   if($scope.foundPersons[i].id != id)
                       temp.push($scope.foundPersons[i]);
               }
               $scope.foundPersons = temp;
               toastr.success('Friend request sent!');
           };

           $scope.close = function(){
               subscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               })
               $uibModalInstance.dismiss('cancel');
           };
        });

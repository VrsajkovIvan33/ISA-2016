/**
 * Created by Nole on 2/15/2017.
 */

angular.module('restaurantApp.GuestFriendsController', [])
       .controller('GuestFriendsController', function ($localStorage, $scope, $uibModal, $stomp, $log,  toastr, GuestFriendsFactory){
           function init(){
               $scope.loggedUser = $localStorage.logged;
               $scope.friends = [];
               GuestFriendsFactory.getFriends($scope.loggedUser.id).success(function(data){
                   if(data != null){
                       $scope.friends = data;
                   }else{
                       alert("Error, try again!");
                   }
               });
           };

           var friendRequestsSubscription = null;
           init();

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
                 .then(function(frame){
                     friendRequestsSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                         toastr.info('You have new friend request!');
                     });
                 });

           $scope.disconnect = function(){
               friendRequestsSubscription.unsubscribe();
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

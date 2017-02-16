/**
 * Created by Nole on 2/15/2017.
 */

angular.module('restaurantApp.GuestFriendsController', [])
       .controller('GuestFriendsController', function ($localStorage, $scope, $uibModal, GuestFriendsFactory){
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

            $scope.openSearchPeopleModal = function(){
                $uibModal.open({
                    templateUrl : 'html/guest/searchPeopleModal.html',
                    controller : 'SearchPeopleController'
                });
            }

           init();
       })

       .controller('SearchPeopleController', function($localStorage, $scope, $stomp, $uibModalInstance, $log, $location, GuestFriendsFactory){
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
                     subscription = $stomp.subscribe('/topic/persons', function(persons, headers, res){
                         $scope.foundPersons = persons;
                     }, {});
                 });

           $scope.search = function(personForSearch){
                var message = { 'message' : personForSearch };
                $stomp.send('/app/searchPersons/' + $localStorage.logged.id, message);
           };

           $scope.close = function(){
               subscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               })
               $uibModalInstance.dismiss('cancel');
           };
        });

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

       .controller('SearchPeopleController', function($localStorage, $scope, $uibModalInstance, $location, GuestFriendsFactory){
            function init(){

            };


            init();
        });

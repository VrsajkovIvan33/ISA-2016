/**
 * Created by Nole on 12/21/2016.
 */

angular.module('restaurantApp.GuestProfileController', [])
       .controller('GuestProfileController', function ($localStorage, $scope, $uibModal, GuestProfileFactory) {
          function init(){
                $scope.loggedUser = $localStorage.logged;
          };

          init();

           $scope.openUpdateModal = function () {
               $uibModal.open({
                   templateUrl : 'html/guest/updateGuestInfoModal.html',
                   controller : 'UpdateGuestProfileController',
               }).result.then(function(updatedUser){
                   $scope.loggedUser = updatedUser;
               });
           }

       })
       .controller('UpdateGuestProfileController', function ($localStorage, $scope, $uibModalInstance, $location, GuestProfileFactory) {
           function init(){
               $scope.userToUpdate = jQuery.extend(true, {}, $localStorage.logged);
           };

           init();

           $scope.update = function(user){
                  GuestProfileFactory.updateGuest(user).success(function(data){
                      if(data != null) {
                          $localStorage.logged = data;
                          $scope.userToUpdate = $localStorage.logged;
                          $uibModalInstance.close($localStorage.logged);
                      }else{
                          alert("It is not possible to change info");
                      }
                  });
           };

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           };
       });

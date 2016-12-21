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
                   controller : 'UpdateGuestProfileController'
               });
           }

       })
       .controller('UpdateGuestProfileController', function ($localStorage, $scope, $uibModalInstance, GuestProfileFactory) {
           function init(){
               $scope.loggedUser = $localStorage.logged;
           };

           init();

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           };
       });

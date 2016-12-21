/**
 * Created by Nole on 12/21/2016.
 */

angular.module('restaurantApp.GuestProfileController', [])
       .controller('GuestProfileController', function ($localStorage, $scope, GuestProfileFactory) {
          function init(){
                $scope.loggedUser = $localStorage.logged;
          };

          init();


       });

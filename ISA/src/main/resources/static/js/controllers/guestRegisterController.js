/**
 * Created by Verpsychoff on 11/24/2016.
 */

angular.module('restaurantApp.GuestRegisterController',[])
    .controller('GuestRegisterController', function ($scope, GuestRegisterFactory) {
        function init() {
            console.log("Kao neko registrovanje");
            $scope.newGuest = new Object();
            $scope.newGuest.gName = "";
            $scope.newGuest.gSurname = "";
            $scope.newGuest.gPassword = "";
            $scope.newGuest.gEmail = "";
        }

        init();

        $scope.registerGuest = function() {
            GuestRegisterFactory.postGuest($scope.newGuest).success(function(data) {
                console.log("Pokusao da registruje!")
            });
        }

    });
/**
 * Created by Verpsychoff on 11/24/2016.
 */

angular.module('restaurantApp.GuestRegisterController',[])
    .controller('GuestRegisterController', function ($scope, $location, GuestRegisterFactory) {
        function init() {
            console.log("Kao neko registrovanje");
        }

        init();

        $scope.registerGuest = function(guest, repeatedPassword) {
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(re.test(guest.gEmail)) {
                if (guest.gPassword === repeatedPassword) {
                    GuestRegisterFactory.postGuest(guest).success(function (data) {
                        $location.path('/');
                    });
                } else {
                    alert("Wrong password");
                }
            }
        }

    });
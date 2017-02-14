/**
 * Created by Verpsychoff on 11/25/2016.
 */

angular.module('restaurantApp.GuestRegisterFactory',[])
    .factory('GuestRegisterFactory', function ($http) {

        var factory = {};

        factory.postGuest = function(newGuest) {
            return $http.post('/registerGuest', {"name": newGuest.gName, "surname": newGuest.gSurname, "password": newGuest.gPassword, "email": newGuest.gEmail});
        }

        return factory;

    });
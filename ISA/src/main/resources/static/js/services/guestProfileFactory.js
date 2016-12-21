/**
 * Created by Nole on 12/21/2016.
 */

angular.module('restaurantApp.GuestProfileFactory', [])
       .factory('GuestProfileFactory', function($http){
           var factory = {};

           factory.updateGuest = function(guest){
               return $http.put('/updateUser', {"name": guest.name, "surname": guest.surname, "password": guest.password, "email": guest.email, "id": guest.id});
           }

           return factory;
       });
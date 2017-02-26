/**
 * Created by Nole on 2/26/2017.
 */

angular.module('restaurantApp.GuestReservationsFactory', [])
       .factory('GuestReservationsFactory', function ($http) {
           var factory = [];

           factory.getFriendRequestsNumber = function(id){
               return $http.get('/getFriendRequestsNumber/' + id, {"id":id});
           }

           factory.getReservations = function(id){
               return $http.get('/getReservations/' + id, {"id":id});
           }

           return factory;
       })

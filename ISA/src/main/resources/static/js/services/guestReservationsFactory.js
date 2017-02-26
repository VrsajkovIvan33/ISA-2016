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

           factory.updateReservation = function(id, orderItem){
               return $http.put('/updateReservation/' + id, orderItem);
           }

           factory.cancelReservation = function(id){
               return $http.delete('/deleteReservation/' + id, {"id":id});
           }

           return factory;
       })

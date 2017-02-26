/**
 * Created by Nole on 2/25/2017.
 */

angular.module('restaurantApp.InviteFactory',[])
       .factory('InviteFactory', function($http){
           var factory = {};

           factory.getReservation = function(id){
               return $http.get('/getReservation/' + id, {"id":id});
           }

           factory.acceptInvitation = function(guestId, reservationId){
               return $http.put('/acceptInvitation/' + guestId + '/' + reservationId, {"guestId":guestId, "reservationId":reservationId});
           }

           factory.declineInvitation = function(guestId, reservationId){
               return $http.put('/declineInvitation/' + guestId + '/' + reservationId, {"guestId":guestId, "reservationId":reservationId});
           }

           return factory;
       });
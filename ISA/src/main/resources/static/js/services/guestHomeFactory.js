/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestHomeFactory', [])
       .factory('GuestHomeFactory', function($http){
           var factory = [];

           factory.getFriendRequestsNumber = function(id){
               return $http.get('/getFriendRequestsNumber/' + id, {"id":id});
           }

           return factory;
       });

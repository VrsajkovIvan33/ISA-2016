/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestFriendRequestsFactory', [])
       .factory('GuestFriendRequestsFactory', function ($http) {
           var factory = [];

           factory.getFriendRequestsNumber = function(id){
               return $http.get('/getFriendRequestsNumber/' + id, {"id":id});
           }

           return factory;
       });
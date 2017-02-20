/**
 * Created by Nole on 2/15/2017.
 */

angular.module('restaurantApp.GuestFriendsFactory', [])
       .factory('GuestFriendsFactory', function($http){
          var factory = {};

           factory.getFriends = function (id) {
               return $http.get('/getFriends/' + id, {"id":id});
           }

           factory.getFriendRequestsNumber = function(id){
               return $http.get('/getFriendRequestsNumber/' + id, {"id":id});
           }

           return factory;
       });

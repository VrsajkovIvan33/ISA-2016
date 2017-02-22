/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestRestaurantsFactory', [])
       .factory('GuestRestaurantsFactory', function($http){
           var factory = [];

           factory.getFriendRequestsNumber = function(id){
               return $http.get('/getFriendRequestsNumber/' + id, {"id":id});
           }

           factory.getRestaurants = function(){
               return $http.get('/getRestaurants');
           }

           return factory;
       });
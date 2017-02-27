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

           factory.addReservation = function(reservation){
               return $http.post('/addReservation',  reservation );
           }

           factory.getReviews = function(){
               return $http.get('/getAverageReviews');
           }

           factory.getFriendsReviews = function(id){
               return $http.get('/getAverageFriendsReviews/'+id, {"id":id});
           }

           return factory;
       });
/**
 * Created by Nole on 11/24/2016.
 */

angular.module('restaurantApp.LoginFactory', [])
       .factory('LoginFactory', function($http){
           var factory = {};

           factory.getUser = function(user){
               return $http.post('/login', {"email":user.email, "password":user.password});
           };

           return factory;
       });
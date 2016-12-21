/**
 * Created by Nole on 12/19/2016.
 */

angular.module('restaurantApp.ConfirmFactory', [])
       .factory('ConfirmFactory', function($http){
            var factory = {};

           factory.confirmGuest = function (user) {
                return $http.post('/confirm', {"email":user.email, "password":user.password});
           };

           return factory;
       });
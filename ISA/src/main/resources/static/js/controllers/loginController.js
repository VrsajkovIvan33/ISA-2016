/**
 * Created by Nole on 11/24/2016.
 */

angular.module('restaurantApp.LoginController',[])
       .controller('LoginController', function ($scope, LoginFactory) {
           function init() {
               console.log("logovo se");
           }

           init();

           $scope.login = function(user){
                LoginFactory.getUser(user).success(function(data){
                   if(data){
                       $scope.logged = data;
                   }
                });
           };

       });
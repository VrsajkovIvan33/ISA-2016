/**
 * Created by Nole on 11/24/2016.
 */

angular.module('restaurantApp.LoginController',[])
       .controller('LoginController', function ($scope, $location, LoginFactory) {
           function init() {
               console.log("logovo se");
           }

           init();

           $scope.login = function(user){
                LoginFactory.getUser(user).success(function(data){
                   if(data){
                       $scope.logged = data;
                       if($scope.logged.type == 'BARTENDER'){
                           $location.path('/bartender');
                       }else if($scope.logged.type == 'COOK'){
                           $location.path('/cook');
                       }else if($scope.logged.type == 'GUEST'){
                           $location.path('/guest');
                       }else if($scope.logged.type == 'WAITER'){
                           $location.path('/waiter');
                       }
                   }
                });
           };

       });
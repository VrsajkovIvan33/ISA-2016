/**
 * Created by Nole on 11/24/2016.
 */

angular.module('restaurantApp.LoginController',[])
       .controller('LoginController', function ($localStorage, $scope, $location, LoginFactory) {
           function init() {
               console.log("logovo se");
           }

           init();

           $scope.login = function(user){
                LoginFactory.getUser(user)
                    .success(function(data){
                       if(data){
                           $localStorage.logged = data;
                           if($localStorage.logged.type == 'BARTENDER'){
                               $location.path('/bartender');
                           }else if($localStorage.logged.type == 'COOK'){
                               $location.path('/cook');
                           }else if($localStorage.logged.type == 'GUEST'){
                               $location.path('/guest');
                           }else if($localStorage.logged.type == 'WAITER'){
                               $location.path('/waiter');
                           }
                       }
                    })
                    .error(function(data){
                        alert("Wrong mail address or password!");
                    });
           };

       });
/**
 * Created by Nole on 11/8/2016.
 */
var app = angular.module('restaurantApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/welcome.html'
        })
        .when('/register',{
            templateUrl : 'html/guestRegister.html'
        })
        .when('/bartender',{
            templateUrl : 'html/bartender.html'
        })
        .when('/cook',{
            templateUrl : 'html/cook.html'
        })
        .when('/guest',{
            templateUrl : 'html/guest.html'
        })
        .when('/waiter',{
            templateUrl : 'html/waiter.html'
        })
        .when('/login', {
            templateUrl : 'html/login.html'
        })
        .when('/systemmanager', {
            templateUrl : 'html/systemmanager.html'
        });
}]);
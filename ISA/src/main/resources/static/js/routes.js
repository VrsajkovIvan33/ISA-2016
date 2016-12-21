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

        //routing for the restaurant manager
        .when('/restaurantManager',{
            templateUrl : 'html/waiter/home.html'
        })
        .when('/restaurantManager/calendar',{
            templateUrl : 'html/restaurantManager/calendar.html'
        })
        .when('/restaurantManager/tables',{
            templateUrl : 'html/restaurantManager/tables.html'
        })

        //routing for the waiter
        .when('/waiter',{
            templateUrl : 'html/waiter/home.html'
        })
        .when('/waiter/calendar',{
            templateUrl : 'html/waiter/calendar.html'
        })
        .when('/waiter/tables',{
            templateUrl : 'html/waiter/tables.html'
        })

        .when('/login', {
            templateUrl : 'html/login.html'
        })
        .when('/systemmanager', {
            templateUrl : 'html/systemmanager.html'
        })
        .when('/systemmanager/systemmanagers', {
            templateUrl : 'html/systemmanagers.html'
        })
        .when('/systemmanager/restaurantmanagers', {
            templateUrl : 'html/restaurantmanagers.html'
        })
        .when('/systemmanager/restaurants', {
            templateUrl: 'html/restaurants.html'
        })
        .when('/confirm', {
            templateUrl : 'html/confirm.html'
        });
}]);
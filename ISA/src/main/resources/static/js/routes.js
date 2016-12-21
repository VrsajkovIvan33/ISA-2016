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
            templateUrl : 'html/guest/guestHome.html'
        })
        .when('/guest/profile',{
            templateUrl : 'html/guest/guestProfile.html'
        })
        .when('/guest/friends',{
            templateUrl : 'html/guest/guestFriends.html'
        })
        .when('/guest/restaurants',{
            templateUrl : 'html/guest/guestRestaurants.html'
        })
        .when('/waiter',{
            templateUrl : 'html/waiter.html'
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
        })
        .when('/provider', {
            templateUrl : 'html/provider.html'
        });
}]);
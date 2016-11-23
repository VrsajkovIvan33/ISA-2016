/**
 * Created by Nole on 11/8/2016.
 */
var app = angular.module('restaurantApp.routes', ['ngRoute']);

app.config(['$routeProvider','$httpProvider', function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/',{
            templateUrl : 'html/welcome.html'
        })
        .when('/login', {
            templateUrl : 'html/login.html'
        });
}]);
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

        //routing for the guest
        .when('/guest',{
            templateUrl : 'html/guest/guestHome.html'
        })
        .when('/guest/profile',{
            templateUrl : 'html/guest/guestProfile.html'
        })
        .when('/guest/friends',{
            templateUrl : 'html/guest/guestFriends.html'
        })
        .when('/guest/friendRequests',{
            templateUrl : 'html/guest/guestFriendRequests.html'
        })
        .when('/guest/restaurants',{
            templateUrl : 'html/guest/guestRestaurants.html'
        })
        .when('/guest/reservations',{
            templateUrl : 'html/guest/guestReservations.html'
        })
        .when('/invite/:reservationId/:guestId',{
            templateUrl : 'html/invite.html'
        })

        //routing for the restaurant manager
        .when('/restaurantManager',{
            templateUrl : 'html/restaurantManager/home.html'
        })
        .when('/restaurantManager/calendar',{
            templateUrl : 'html/restaurantManager/calendar.html'
        })
        .when('/restaurantManager/tables',{
            templateUrl : 'html/restaurantManager/tables.html'
        })
        .when('/restaurantManager/providers',{
            templateUrl : 'html/restaurantManager/providers.html'
        })
        .when('/restaurantManager/waiters',{
            templateUrl : 'html/restaurantManager/waiters.html'
        })
        .when('/restaurantManager/bartenders',{
            templateUrl : 'html/restaurantManager/bartenders.html'
        })
        .when('/restaurantManager/cooks',{
            templateUrl : 'html/restaurantManager/cooks.html'
        })
        .when('/restaurantManager/menu',{
            templateUrl : 'html/restaurantManager/menu.html'
        })
        .when('/restaurantManager/mapBeverage',{
            templateUrl : 'html/restaurantManager/mapBeverage.html'
        })
        .when('/restaurantManager/statement',{
            templateUrl : 'html/restaurantManager/statement.html'
        })
        .when('/restaurantManager/tenders',{
            templateUrl : 'html/restaurantManager/tenders.html'
        })
        .when('/restaurantManager/offers',{
            templateUrl : 'html/restaurantManager/offers.html'
        })
        .when('/restaurantManager/reports',{
            templateUrl : 'html/restaurantManager/reports.html'
        })
        .when('/restaurantManager/chart',{
            templateUrl : 'html/restaurantManager/chart.html'
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
        .when('/waiter/unassignedOrders', {
            templateUrl : 'html/waiter/unassignedOrders.html'
        })
        .when('/waiter/waitersOrders', {
            templateUrl : 'html/waiter/waitersOrders.html'
        })
        .when('/waiter/profile', {
            templateUrl : 'html/waiter/waiterProfile.html'
        })

        //routing for the cook
        .when('/cook',{
            templateUrl : 'html/cook/home.html'
        })
        .when('/cook/calendar',{
            templateUrl : 'html/cook/calendar.html'
        })
        .when('/cook/cooksOrderItems',{
            templateUrl : 'html/cook/cooksOrderItems.html'
        })
        .when('/cook/unassignedOrderItems',{
            templateUrl : 'html/cook/unassignedOrderItems.html'
        })
        .when('/cook/profile', {
            templateUrl : 'html/cook/cookProfile.html'
        })

        //routing for the bartender
        .when('/bartender',{
            templateUrl : 'html/bartender/home.html'
        })
        .when('/bartender/calendar',{
            templateUrl : 'html/bartender/calendar.html'
        })
        .when('/bartender/bartendersOrderItems',{
            templateUrl : 'html/bartender/bartendersOrderItems.html'
        })
        .when('/bartender/unassignedOrderItems',{
            templateUrl : 'html/bartender/unassignedOrderItems.html'
        })
        .when('/bartender/profile',{
            templateUrl : 'html/bartender/bartenderProfile.html'
        })

        .when('/login', {
            templateUrl : 'html/login.html'
        })
        .when('/systemmanager', {
            templateUrl : 'html/systemManager/systemmanager.html'
        })
        .when('/systemmanager/systemmanagers', {
            templateUrl : 'html/systemManager/systemmanagers.html'
        })
        .when('/systemmanager/restaurantmanagers', {
            templateUrl : 'html/systemManager/restaurantmanagers.html'
        })
        .when('/systemmanager/restaurants', {
            templateUrl: 'html/systemManager/restaurants.html'
        })
        .when('/systemmanager/providers', {
            templateUrl: 'html/systemManager/providers.html'
        })
        .when('/confirm', {
            templateUrl : 'html/confirm.html'
        })


        .when('/provider', {
            templateUrl : 'html/provider/provider.html'
        })
        .when('/provider/profile', {
            templateUrl : 'html/provider/providerProfile.html'
        })
        .when('/provider/tenders', {
            templateUrl : 'html/provider/tenders.html'
        })
        .when('/provider/offerHistory', {
            templateUrl : 'html/provider/offerHistory.html'
        });
}]);
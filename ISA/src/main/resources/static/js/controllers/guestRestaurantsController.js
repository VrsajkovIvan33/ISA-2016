/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestRestaurantsController', [])
       .controller('GuestRestaurantsController', function($localStorage, $scope, $uibModal, $stomp, $log, toastr, GuestRestaurantsFactory){
           function init(){
               $scope.loggedUser = $localStorage.logged;
               $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];
               $scope.friendRequestsNumber = 0;
               $scope.showRequests = false;
               $scope.restaurants = [];
               GuestRestaurantsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function(data){
                   $scope.friendRequestsNumber = data;
                   if(data > 0)
                       $scope.showRequests = true;
               });

                GuestRestaurantsFactory.getRestaurants().success(function (data) {
                    $scope.restaurants = data;
                })
           };

           var friendRequestSubscription = null;
           var acceptedFriendRequestSubscription = null;
           var restaurantsByNameSubscription = null;
           var restaurantsByTypeSubscription = null;
           init();

           $scope.bookNow = function(restaurant){
               $uibModal.open({
                   templateUrl : 'html/guest/reservationModal.html',
                   controller : 'GuestReservationController',
                   resolve: {
                       param : function(){
                           return {'restaurant' : restaurant};
                       }
                   }
               });
           }

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
               .then(function(frame){
                   friendRequestSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                       toastr.info('You have new friend request!');
                       $scope.friendRequestsNumber = numberOfRequests;
                       if(numberOfRequests > 0)
                           $scope.showRequests = true;
                   });

                   acceptedFriendRequestSubscription = $stomp.subscribe('/topic/friendAcceptedRequest/' + $localStorage.logged.id, function(friend, headers, res){
                       toastr.info(friend.name + ' ' + friend.surname + ' accepted friend request.');
                   });

                   restaurantsByNameSubscription = $stomp.subscribe('/topic/restaurantsByName/' + $localStorage.logged.id, function(restaurants, headers, res){
                       $scope.$apply(function(){
                           $scope.restaurants = restaurants;
                       });
                   });

                   restaurantsByTypeSubscription = $stomp.subscribe('/topic/restaurantsByType/' + $localStorage.logged.id, function (restaurants, headers, res) {
                       $scope.$apply(function () {
                           $scope.restaurants = restaurants;
                       })
                   })
               });

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               restaurantsByNameSubscription.unsubscribe();
               restaurantsByTypeSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };

           $scope.searchByName = function(restaurantToSearch){
               var message = { 'message' : restaurantToSearch };
               $stomp.send('/app/searchRestaurantsByName/' + $localStorage.logged.id, message);

           };

           $scope.searchByType = function(type){
               var message = { 'message' : type };
               $stomp.send('/app/searchRestaurantsByType/' + $localStorage.logged.id, message);
           };

           $scope.getRestaurants = function(){
               GuestRestaurantsFactory.getRestaurants().success(function (data) {
                   $scope.restaurants = data;
               })
           }
       })
       .controller('GuestReservationController', function ($localStorage, $scope, $uibModalInstance, param, $log, toastr, GuestRestaurantsFactory) {
           function init(){
               $scope.restaurant = param.restaurant;
           };

           init();
       });
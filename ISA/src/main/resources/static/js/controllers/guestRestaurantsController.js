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
                           return {'restaurant' : restaurant };
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
       .controller('GuestReservationController', function ($localStorage, $scope, $stomp, $uibModal, $uibModalInstance, param, $log, toastr, GuestRestaurantsFactory) {
           function init(){
               $scope.restaurant = param.restaurant;
               $scope.modalMode = 1;
               $scope.reservationHelper = new Object();
               $scope.tables = [];
               $scope.foundFriends = [];
               $scope.invitedFriends = [];
               $scope.order = {
                   orderItems : [],
                   restaurantTable : null,
                   oStatus : 'Waiting for waiter',
                   oAssigned : false,
                   waiters : [],
                   currentWaiter : null,
                   year : 0,
                   month : 0,
                   day : 0,
                   hourOfArrival : 0,
                   minuteOfArrival : 0
               };
           };

           var subscription = null;
           var searchSubscription = null;
           init();

           $stomp.setDebug(function (args) {
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
                 .then(function(frame){
                     subscription = $stomp.subscribe('/topic/tables/' + $localStorage.logged.id, function(tables, headers, res){
                         $scope.tables = tables;
                     });

                     searchSubscription = $stomp.subscribe('/topic/searchFriends/' + $localStorage.logged.id, function(friends, headers, res){
                         $scope.$apply(function(){
                             var temp = [];
                             for(i = 0; i<friends.length; i++){
                                 var found = false;
                                 for(j = 0; j<$scope.invitedFriends.length; j++){
                                     if(friends[i].id == $scope.invitedFriends[j].id)
                                         found = true;
                                 }
                                 if(!found)
                                     temp.push(friends[i]);
                             }
                             $scope.foundFriends = temp;
                         });
                     });
                 });

           $scope.getFreeTables = function(reservationHelper){
               $scope.modalMode += 1;
               $stomp.send('/app/getTables/' + $scope.restaurant.id + '/' + $localStorage.logged.id, reservationHelper);
           }

           $scope.selectTable = function(table){
               table.selected = true;
               if($scope.order.restaurantTable == null)
                   $scope.order.restaurantTable = table;
           }

           $scope.search = function(friendForSearch){
               var message = { 'message' : friendForSearch };
               $stomp.send('/app/searchFriends/' + $localStorage.logged.id, message);
           }

           $scope.inviteFriend = function(person){
               $scope.invitedFriends.push(person)
               var temp = [];
               for(i = 0; i<$scope.foundFriends.length; i++){
                   if($scope.foundFriends[i].id != person.id)
                       temp.push($scope.foundFriends[i]);
               }
               $scope.foundFriends = temp;
               toastr.info(person.name + ' ' + person.surname + ' is added to invitation list.');
           }

           $scope.changeModalMode = function(mode){
               $scope.modalMode += 1;
           }

           $scope.openNewItemModal = function(){
               $uibModal.open({
                   templateUrl : 'html/guest/newOrderItemModal.html',
                   controller : 'NewOrderItemController',
                   resolve: {
                       param : function(){
                           return {'restaurant' : $scope.restaurant };
                       }
                   }
               }).result.then(function(orderItem){
                    $scope.order.orderItems.push(orderItem);
               });
           }

           $scope.removeItem = function(orderItem){
               var index = $scope.order.orderItems.indexOf(orderItem);
               $scope.order.orderItems.splice(index, 1);
           }

           $scope.finish = function(){
               var selectedTables = [];
               for(int i=0; i<$scope.tables.length; i++){
                   if($scope.tables[i].selected)
                       selectedTables.push($scope.tables[i]);
               }
               $scope.reservation = {
                   date: $scope.reservationHelper.date,
                   timeH: $scope.reservationHelper.timeH,
                   timeM: $scope.reservationHelper.timeM,
                   durationH: $scope.reservationHelper.durationH,
                   durationM: $scope.reservationHelper.durationM,
                   restaurant: $scope.restaurant,
                   order: $scope.order,
                   host: $localStorage.logged,
                   pendingGuests: $scope.invitedFriends,
                   acceptedGuests: [],
                   tables:  selectedTables
               };
               toastr.info('Tables reserved and invitation mails sent!');
           }

           $scope.close = function(){
               subscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               })
               $uibModalInstance.dismiss('cancel');
           };
       })
       .controller('NewOrderItemController', function ($localStorage, $scope, $stomp, $uibModalInstance, param, $log, toastr, MenuService){
           $scope.newOrderItem = new Object();
           $scope.newOrderItem.oiStatus = "Waiting";
           $scope.newOrderItem.user = $localStorage.logged;
           $scope.newOrderItem.order = null;
           $scope.newOrderItem.hourOfArrival = 0;
           $scope.newOrderItem.minuteOfArrival = 0;
           $scope.newOrderItem.oiReadyByArrival = false;

           $scope.restaurant = param.restaurant;

           MenuService.getMenusByMRestaurant($scope.restaurant.id).success(function(data){
               $scope.menus = data;
               $scope.newOrderItem.menu = $scope.menus[0];
           });

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           }

           $scope.addOrderItem = function(orderItem){
               $uibModalInstance.close(orderItem);
           }
       })
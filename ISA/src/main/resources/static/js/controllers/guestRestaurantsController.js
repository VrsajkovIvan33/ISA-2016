/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestRestaurantsController', [])
       .controller('GuestRestaurantsController', function($localStorage, $scope, $uibModal, $stomp,$location, $log, toastr, NgMap, GuestRestaurantsFactory){
           function init(){
               if($localStorage.logged == null)
                   $location.path("/");
               else {
                   if ($localStorage.logged.type != 'GUEST')
                       $location.path("/")
                   else {
                       $scope.loggedUser = $localStorage.logged;
                       $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];
                       $scope.friendRequestsNumber = 0;
                       $scope.showRequests = false;
                       $scope.restaurants = [];
                       $scope.sorted = 'not';
                       $scope.averageReviews = {};
                       $scope.friendsReviews = {};
                       GuestRestaurantsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function (data) {
                           $scope.friendRequestsNumber = data;
                           if (data > 0)
                               $scope.showRequests = true;
                       });

                       GuestRestaurantsFactory.getRestaurants().success(function (data) {
                           $scope.restaurants = data;
                       })

                       GuestRestaurantsFactory.getReviews().success(function (data) {
                           for (var i in data) {
                               if (isNaN(data[i]))
                                   $scope.averageReviews[i] = 'no reviews';
                               else
                                   $scope.averageReviews[i] = data[i];
                           }
                       });

                       GuestRestaurantsFactory.getFriendsReviews($scope.loggedUser.id).success(function (data) {
                           for (var i in data) {
                               if (isNaN(data[i]))
                                   $scope.friendsReviews[i] = 'no reviews';
                               else
                                   $scope.friendsReviews[i] = data[i];
                           }
                       });
                   }
               }
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

           $scope.openMapModal = function(restaurant){
                $uibModal.open({
                    templateUrl : 'html/guest/mapModal.html',
                    controller : 'RestaurantMapController',
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

           $scope.logOut = function(){
               $scope.disconnect();
               $localStorage.logged = null;
               $location.path("/");
           };

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
               $scope.sorted = 'not';
               GuestRestaurantsFactory.getRestaurants().success(function (data) {
                   $scope.restaurants = data;
               })
           }

           $scope.byName = function(){
               $scope.sorted = 'byName';
           }

           $scope.byType = function(){
               $scope.sorted = 'byType';
           }
           
           $scope.byDistance = function(){
               $scope.sorted = 'not';
               navigator.geolocation.getCurrentPosition(function(pos){
                   $scope.currentLatitude = pos.coords.latitude;
                   $scope.currentLongitude = pos.coords.longitude;
               });
               var temp = {};
               for(i = 0; i<$scope.restaurants.length; i++){
                    var k = Math.sqrt(($scope.restaurants[i].latitude - $scope.currentLatitude)*($scope.restaurants[i].latitude - $scope.currentLatitude) + ($scope.restaurants[i].longitude - $scope.currentLongitude)*($scope.restaurants[i].longitude - $scope.currentLongitude));
                    temp[k] = i;
               }
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
                   orderItems : new Array(),
                   restaurantTable : null,
                   oStatus : "Waiting for waiter",
                   oAssigned : false,
                   waiters : new Array(),
                   currentWaiter : null,
                   year : 0,
                   month : 0,
                   day : 0,
                   hourOfArrival : 0,
                   minuteOfArrival : 0,
                   billCreated : false
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
                         $scope.$apply(function(){
                             $scope.tables = tables;
                         })

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
               if(validate(reservationHelper)) {
                   $scope.modalMode += 1;
                   $stomp.send('/app/getTables/' + $scope.restaurant.id + '/' + $localStorage.logged.id, reservationHelper);
               }
           }

           $scope.selectTable = function(table) {
               if (!table.table.rtActive || table.reserved) {
                   toastr.warning('You can not select unactive and reserved table!')
               } else{
                    table.selected = true;
                    if ($scope.order.restaurantTable == null)
                        $scope.order.restaurantTable = table.table;
                }
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
               if($scope.modalMode == 2){
                   if($scope.order.restaurantTable == null){
                       toastr.error('You have to select table(s)!');
                   }else{
                       $scope.modalMode += 1;
                   }
               }else
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
               for(i=0; i<$scope.tables.length; i++){
                   if($scope.tables[i].selected)
                       selectedTables.push($scope.tables[i].table);
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
                   acceptedGuests: new Array(),
                   tables:  selectedTables
               };
               GuestRestaurantsFactory.addReservation($scope.reservation).success(function(data){
                   toastr.info('Tables reserved and invitation mails sent!');
               }).error(function () {
                   toastr.error('You can not reserve this tables!');
               });

               $uibModalInstance.dismiss('cancel');
           }

           $scope.close = function(){
               subscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               })
               $uibModalInstance.dismiss('cancel');
           };

           function validate(reservationHelper) {
               if(reservationHelper.date == null){
                   alert('Please enter reservation date');
                   return false;
               }

               var date = new Date();
               date.setHours(0,0,0,0);
               if(date > reservationHelper.date){
                   alert('Reservation date must be one or more days after today');
                   return false;
               }

               if(reservationHelper.timeH == null){
                   alert('Reservation time hours must be between 0 and 23');
                   return false;
               }

               if(reservationHelper.timeM == null){
                   alert('Reservation time minutes must be between 0 and 59');
                   return false;
               }

               if(reservationHelper.durationH == null){
                   alert('Reservation duration hours must be between 0 and 23');
                   return false;
               }

               if(reservationHelper.durationM == null){
                   alert('Reservation duration minutes must be between 0 and 59');
                   return false;
               }

               return true;
           }
       })
       .controller('NewOrderItemController', function ($localStorage, $scope, $stomp, $uibModalInstance, param, $log, toastr,  MenuService){
           $scope.newOrderItem = new Object();
           $scope.newOrderItem.oiStatus = "Waiting for waiter";
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
       .controller('RestaurantMapController', function ($localStorage, $scope, $stomp, $uibModalInstance, param, NgMap, $log, toastr) {
           $scope.restaurant = param.restaurant;
           NgMap.getMap().then(function(map){
               console.log(map.getCenter());
           });

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           }
       })
/**
 * Created by Nole on 2/26/2017.
 */

angular.module('restaurantApp.GuestReservationsController', [])
       .controller('GuestReservationsController', function($localStorage, $scope, $uibModal, $stomp,$location, $log, toastr, GuestReservationsFactory){
            function init(){
                if($localStorage.logged == null)
                    $location.path("/");
                else {
                    if ($localStorage.logged.type != 'GUEST')
                        $location.path("/")
                    else {
                        $scope.loggedUser = $localStorage.logged;
                        $scope.friendRequestsNumber = 0;
                        $scope.showRequests = false;
                        $scope.reservations = [];
                        $scope.dates = {};
                        GuestReservationsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function (data) {
                            $scope.friendRequestsNumber = data;
                            if (data > 0)
                                $scope.showRequests = true;
                        });

                        GuestReservationsFactory.getReservations($localStorage.logged.id).success(function (data) {
                            $scope.reservations = data;
                            for (i = 0; i < $scope.reservations.length; i++) {
                                var date = new Date($scope.reservations[i].date);
                                $scope.dates[$scope.reservations[i].id] = {
                                    "date": date.getDate(),
                                    "month": date.getMonth() + 1,
                                    "year": date.getFullYear()
                                };
                            }
                        });
                    }
                }
            }

            var friendRequestSubscription = null;
            var acceptedFriendRequestSubscription = null;
            init();

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
               });

           $scope.getOrders = function(reservation){
                $uibModal.open({
                    templateUrl : 'html/guest/ordersModal.html',
                    controller : 'OrdersModalController',
                    resolve : {
                        param : function(){
                            return { 'reservation' : reservation };
                        }
                    }
                }).result.then(function(){
                    GuestReservationsFactory.getReservations($localStorage.logged.id).success(function (data) {
                        $scope.reservations = data;
                        for (i = 0; i < $scope.reservations.length; i++) {
                            var date = new Date($scope.reservations[i].date);
                            $scope.dates[$scope.reservations[i].id] = {
                                "date": date.getDate(),
                                "month": date.getMonth() + 1,
                                "year": date.getFullYear()
                            };
                        }
                    });
                });
           };

           $scope.cancelReservation = function(reservation){
                GuestReservationsFactory.cancelReservation(reservation.id).success(function(data){
                    var temp = [];
                    for(i=0; i<$scope.reservations.length; i++){
                        if($scope.reservations[i].id != reservation.id)
                            temp.push($scope.reservations[i]);
                    }
                    $scope.reservations = temp;
                }).error(function(){
                    toastr.error('You can not cancel reservation!');
                });
           }

           $scope.logOut = function(){
               $scope.disconnect();
               $localStorage.logged = null;
               $location.path("/");
           };

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };
       })
       .controller('OrdersModalController', function ($localStorage, $scope, $stomp, $uibModalInstance, $uibModal, param, $log, toastr, GuestReservationsFactory){
            $scope.reservation = param.reservation;
            $scope.loggedUser = $localStorage.logged;

           $scope.openNewItemModal = function(){
               $uibModal.open({
                   templateUrl : 'html/guest/newOrderItemModal.html',
                   controller : 'NewOrderItemController',
                   resolve: {
                       param : function(){
                           return {'restaurant' : $scope.reservation.restaurant };
                       }
                   }
               }).result.then(function(orderItem){
                   //$scope.reservation.order.orderItems.push(orderItem);
                   GuestReservationsFactory.updateReservation($scope.reservation.id, orderItem).success(function(data){
                       GuestReservationsFactory.getReservation($scope.reservation.id).success(function (data) {
                           $scope.reservation = data;
                       })
                       toastr.success('Order added!');
                   });
               });
           }

           $scope.removeItem = function(reservationId, oiId){
                GuestReservationsFactory.removeItem(reservationId, oiId).success(function(data){
                    var temp = [];
                    for(i=0; i<$scope.reservation.order.orderItems.length; i++){
                        if(oiId != $scope.reservation.order.orderItems[i].id)
                            temp.push($scope.reservation.order.orderItems[i]);
                    }
                    $scope.reservation.order.orderItems = temp;
                }).error(function(){
                    toastr.error('You can not remove this order item');
                });
           }

           $scope.close = function(){
               $uibModalInstance.close();
           }
       })
       .controller('NewOrderItemController', function ($localStorage, $scope, $stomp, $uibModalInstance, param, $log, toastr, MenuService){
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
       });


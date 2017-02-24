/**
 * Created by Verpsychoff on 2/18/2017.
 */

angular.module('restaurantApp.WaiterFunctionsController',[])
    .controller('WaiterFunctionsController', function ($localStorage, $scope, $location, $uibModal, $rootScope, uiCalendarConfig, WaiterService, RestaurantTableFactory, CalendarEventFactory, OrderFactory) {
        function init() {
            $scope.alertOnEventClick = function( date, jsEvent, view){
                console.log("Kliknut dogadjaj!");
            };

            $scope.uiConfig = {
                calendar: {
                    height: 450,
                    editable: true,
                    header: {
                        left: 'month basicWeek basicDay agendaWeek agendaDay',
                        center: 'title',
                        right: 'today prev, next'
                    },
                    eventClick: $scope.alertOnEventClick
                }
            }

            $scope.changeView = function(view,calendar) {
                uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
            };

            $rootScope.$on('updateCalendar', function () {
                window.calendar.fullCalendar('refetchEvents');
            });

            $scope.combineNameAndSurname = function(name, surname) {
                var blankSpace = " ";
                return name.concat(blankSpace.concat(surname));
            }

            WaiterService.getWaiter($localStorage.logged.id).success(function(data) {
                $scope.waiter = data;
                RestaurantTableFactory.getTablesByRestaurant($scope.waiter.restaurant).success(function(data) {
                    $scope.tables = data;
                });
                CalendarEventFactory.getEventsByUser($scope.waiter).success(function(data) {
                    $scope.myEvents = [];
                    var i;
                    for (i = 0; i < data.length; i++) {
                        $scope.myEvents.push({
                            title: data[i].user.name.concat(" - ".concat(data[i].tableRegion.trMark)),
                            start: new Date(data[i].year, data[i].month, data[i].day, data[i].startHour, data[i].startMinute),
                            end: new Date(data[i].year, data[i].month, data[i].day, data[i].endHour, data[i].endMinute),
                            allDay: false
                        })
                    }
                    $scope.eventSources = [$scope.myEvents];
                    //JEBEM TI MAMU U PICKU DA TI JEBEM!
                    uiCalendarConfig.calendars.myCalendar.fullCalendar('addEventSource', $scope.myEvents);
                });
                CalendarEventFactory.getEventByUserAndShift($scope.waiter).success(function(data) {
                    $scope.currentEvent = data;
                });
                OrderFactory.getUnassignedByUser($scope.waiter).success(function(data) {
                    $scope.unassigned = data;
                });
                OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                    $scope.orders = data;
                });
            })

            $scope.openShowOrderItemsModal = function(order) {
                $rootScope.orderToShowUnassigned = order;
                $uibModal.open({
                    templateUrl : 'html/waiter/showOrderItemsModal.html',
                    controller : 'ShowOrderItemsForUnassignedController'
                });
            }

            $scope.openShowUpdateOrderItemsModal = function(order) {
                $rootScope.waiterForOrder = $scope.waiter;
                $rootScope.orderToShowUpdate = order;
                $uibModal.open({
                    templateUrl : 'html/waiter/showUpdateOrderItemsModal.html',
                    controller : 'ShowUpdateOrderItemsController'
                }).result.then(function(){
                    OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                        $scope.orders = data;
                    });
                });
            }

            $scope.openAddNewOrderModal = function() {
                $rootScope.waiterForOrder = $scope.waiter;
                $uibModal.open({
                    templateUrl : 'html/waiter/addNewOrder.html',
                    controller : 'AddNewOrderController'
                }).result.then(function(){
                    OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                        $scope.orders = data;
                    });
                });
            }

            $scope.takeOrder = function(order) {
                order.oAssigned = true;
                order.currentWaiter = $scope.waiter;
                order.waiters.push($scope.waiter);
                order.oStatus = "Waiting";
                var i = 0;
                for (i = 0; i < order.orderItems.length; i++) {
                    order.orderItems[i].oiStatus = "Waiting";
                }
                OrderFactory.updateOrder(order).success(function(data) {
                    OrderFactory.getUnassignedByUser($scope.waiter).success(function(data) {
                        $scope.unassigned = data;
                    });
                    OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                        $scope.orders = data;
                    });
                });
            }

            $scope.unassignOrder = function(order) {
                order.oAssigned = false;
                order.currentWaiter = null;
                OrderFactory.updateOrder(order).success(function(data) {
                    OrderFactory.getUnassignedByUser($scope.waiter).success(function(data) {
                        $scope.unassigned = data;
                    });
                    OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                        $scope.orders = data;
                    });
                });
            }

            $scope.openCreateBillModal = function(order) {
                $rootScope.orderToBill = order;
                $uibModal.open({
                    templateUrl : 'html/waiter/createBillModal.html',
                    controller : 'CreateBillController'
                }).result.then(function(){
                    OrderFactory.getOrdersByWaiter($scope.waiter).success(function(data) {
                        $scope.orders = data;
                    });
                });
            }

        }

        init();
    })
    .controller('ShowOrderItemsForUnassignedController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope) {

        $scope.orderToShow = $rootScope.orderToShowUnassigned;

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    })
    .controller('ShowUpdateOrderItemsController', function ($localStorage, $scope, $location, $uibModal, $uibModalInstance, $rootScope, OrderFactory, OrderItemFactory)  {

        $scope.orderToShow = $rootScope.orderToShowUpdate;

        $scope.openAddOrderItemsModal = function() {
            $rootScope.orderToShowUpdate = $scope.orderToShow;
            $uibModal.open({
                templateUrl : 'html/waiter/addNewOrderItem.html',
                controller : 'AddOrderItemsController'
            }).result.then(function(){
                $scope.orderToShow = $rootScope.orderToShowUpdate;
            });
        }

        $scope.removeItem = function(orderItem) {
            OrderItemFactory.deleteOrderItem(orderItem).success(function(data) {
                var index = $scope.orderToShow.orderItems.indexOf(orderItem);
                $scope.orderToShow.orderItems.splice(index, 1);
                OrderFactory.updateOrder($scope.orderToShow).success(function(data) {
                   $scope.orderToShow = data;
                   $rootScope.orderToShowUpdate = data;
                });
            });
        }

        $scope.close = function(){
            //$uibModalInstance.dismiss('cancel');
            $uibModalInstance.close();
        }

        $scope.updateOrder = function(){
            OrderFactory.updateOrder($scope.orderToShow).success(function(data) {
                if (data == null) {
                    alert("Change not possible!")
                }
                $uibModalInstance.close();
            });
        };
    })
    .controller('AddOrderItemsController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, MenuService, OrderItemFactory) {

        $scope.orderToShow = $rootScope.orderToShowUpdate;
        $scope.waiter = $rootScope.waiterForOrder;

        $scope.newOrderItem = new Object();
        $scope.newOrderItem.oiStatus = "Waiting";
        $scope.newOrderItem.oiReadyByArrival = false;
        $scope.newOrderItem.user = null;
        $scope.newOrderItem.order = null;
        $scope.newOrderItem.hourOfArrival = 0;
        $scope.newOrderItem.minuteOfArrival = 0;

        MenuService.getMenusByMRestaurant($scope.waiter.restaurant.id).success(function(data) {
           $scope.menus = data;
           $scope.newOrderItem.menu = $scope.menus[0];
        });

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.addOrderItem = function() {
            $rootScope.orderToShowUpdate.orderItems.push($scope.newOrderItem);
            $uibModalInstance.close();
        }
    })
    .controller('AddNewOrderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, RestaurantTableFactory, OrderFactory) {

        $scope.newOrder = new Object();
        $scope.newOrder.orderItems = new Array();
        $scope.newOrder.oStatus = "Waiting";
        $scope.newOrder.oAssigned = true;
        $scope.newOrder.currentWaiter = $rootScope.waiterForOrder;
        $scope.newOrder.waiters = new Array();
        $scope.newOrder.waiters.push($rootScope.waiterForOrder);
        $scope.newOrder.year = 0;
        $scope.newOrder.month = 0;
        $scope.newOrder.day = 0;
        $scope.newOrder.hourOfArrival = 0;
        $scope.newOrder.minuteOfArrival = 0;
        $scope.newOrder.billCreated = false;

        RestaurantTableFactory.getActiveTablesByRestaurant($rootScope.waiterForOrder.restaurant).success(function(data) {
           $scope.tables = data;
           $scope.newOrder.restaurantTable = data[0];
        });

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.addOrder = function() {
            OrderFactory.addOrder($scope.newOrder).success(function(data) {
                $uibModalInstance.close();
            });
        }

    })
    .controller('CreateBillController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, OrderFactory) {

        $scope.orderToBill = $rootScope.orderToBill;

        $scope.total = 0;
        var i = 0;
        for (i = 0; i < $scope.orderToBill.orderItems.length; i++) {
            $scope.total += $scope.orderToBill.orderItems[i].menu.mPrice;
        }

        $scope.createBill = function() {
            $scope.orderToBill.billCreated = true;
            OrderFactory.finalizeOrder($scope.orderToBill).success(function(data) {
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });



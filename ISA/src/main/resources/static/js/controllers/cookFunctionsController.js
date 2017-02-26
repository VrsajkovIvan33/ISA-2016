/**
 * Created by Verpsychoff on 2/18/2017.
 */

angular.module('restaurantApp.CookFunctionsController',[])
    .controller('CookFunctionsController', function ($localStorage, $scope, $location, $uibModal, $rootScope, $stomp, $log, uiCalendarConfig, $uibModal, CookService, CalendarEventFactory, OrderItemFactory) {

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

            var ordersSubscription = null;
            var orderItemsSubscription = null;

            CookService.getCook($localStorage.logged.id).success(function(data) {
                $scope.cook = data;
                $scope.checkPasswordChanged();
                CalendarEventFactory.getEventsByUser($scope.cook).success(function(data) {
                    $scope.myEvents = [];
                    var i;
                    for (i = 0; i < data.length; i++) {
                        $scope.myEvents.push({
                            title: data[i].user.name,
                            start: new Date(data[i].year, data[i].month, data[i].day, data[i].startHour, data[i].startMinute),
                            end: new Date(data[i].year, data[i].month, data[i].day, data[i].endHour, data[i].endMinute),
                            allDay: false
                        })
                    }

                    $scope.eventSources = [$scope.myEvents];
                    //JEBEM TI MAMU U PICKU DA TI JEBEM!
                    uiCalendarConfig.calendars.myCalendar.fullCalendar('addEventSource', $scope.myEvents);
                });
                OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                    $scope.inWaiting = data;
                });
                OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                    $scope.making = data;
                });

                $stomp.connect('/stomp', {})
                    .then(function(frame){
                        ordersSubscription = $stomp.subscribe('/topic/orders/' + $scope.cook.restaurant.id, function(unimportantBoolean, headers, res){
                            OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                                $scope.inWaiting = data;
                            });
                            OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                                $scope.making = data;
                            });
                        });
                        orderItemsSubscription = $stomp.subscribe('/topic/orderItems/' + $scope.cook.restaurant.id, function(unimportantBoolean, headers, res){
                            OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                                $scope.inWaiting = data;
                            });
                            OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                                $scope.making = data;
                            });
                        });

                    });

            });

            $stomp.setDebug(function(args){
                $log.debug(args);
            });

            $scope.disconnect = function(){
                ordersSubscription.unsubscribe();
                orderItemsSubscription.unsubscribe();
                $stomp.disconnect().then(function(){
                    $log.info('disconnected');
                });
            };

            $scope.takeOrderItem = function(orderItem) {
                orderItem.oiStatus = "Currently making";
                orderItem.staff = $localStorage.logged;
                $stomp.send('/app/updateOrderItem/' + $scope.cook.restaurant.id, orderItem);
                // OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                //     OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                //         $scope.inWaiting = data;
                //     });
                //     OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                //         $scope.making = data;
                //     });
                // });
            }

            $scope.markAsFinished = function(orderItem) {
                orderItem.oiStatus = "Ready";
                $stomp.send('/app/updateOrderItem/' + $scope.cook.restaurant.id, orderItem);
                // OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                //     OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                //         $scope.inWaiting = data;
                //     });
                //     OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                //         $scope.making = data;
                //     });
                // });
            }

            $scope.openUpdateProfileModal = function() {
                $rootScope.updateCook = $scope.cook;
                $uibModal.open({
                    templateUrl : 'html/cook/updateCook.html',
                    controller : 'UpdateCookProfileController'
                }).result.then(function(){
                    CookService.getCook($localStorage.logged.id).success(function(data) {
                        $scope.cook = data;
                    });
                });
            }

            $scope.checkPasswordChanged = function () {
                if($scope.cook.passwordChanged == false){
                    $uibModal.open({
                        templateUrl : 'html/cook/cookChangePassword.html',
                        controller : 'ChangeCookPasswordController',
                        backdrop: 'static',
                        keyboard: false
                    }).result.then(function(){
                        CookService.getCook($localStorage.logged.id).success(function(data) {
                            $scope.cook = data;
                        })
                    });
                }
            }

        }

        init();

    })
    .controller('UpdateCookProfileController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, CookService) {

        function getCook(){
            $scope.cookToUpdate = jQuery.extend(true, {}, $rootScope.updateCook);
            $scope.cookToUpdate.date_of_birth = new Date($scope.cookToUpdate.date_of_birth);
        }
        getCook();

        $scope.updateCook = function (cook) {
            CookService.updateCook(cook).success(function (data) {
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.cookTypes  = ["Salad", "Cooked Meal", "Grilled Dish", "All"];

    })
    .controller('ChangeCookPasswordController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, CookService) {

        CookService.getCook($localStorage.logged.id).success(function(data) {
            $scope.cook = data;
        })

        $scope.newPassword = "";
        $scope.repeatPassword = "";


        $scope.updateCookPassword = function() {
            if($scope.repeatPassword != $scope.newPassword){
                alert("Passwords do not match!");
            }else {
                $scope.cook.password = $scope.newPassword;
                $scope.cook.passwordChanged = true;
                CookService.updateCook($scope.cook).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

    });
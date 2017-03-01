/**
 * Created by Verpsychoff on 2/18/2017.
 */

angular.module('restaurantApp.BartenderFunctionsController',[])
    .controller('BartenderFunctionsController', function ($localStorage, $scope, $location, $uibModal, $rootScope, $stomp, $log, uiCalendarConfig, $uibModal, BartenderService, CalendarEventFactory, OrderItemFactory) {

        function init() {
            if($localStorage.logged == null)
                $location.path("/");
            else {
                if ($localStorage.logged.type != 'BARTENDER')
                    $location.path("/");
                else {

                    $scope.alertOnEventClick = function (date, jsEvent, view) {
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

                    $scope.changeView = function (view, calendar) {
                        uiCalendarConfig.calendars[calendar].fullCalendar('changeView', view);
                    };

                    $rootScope.$on('updateCalendar', function () {
                        window.calendar.fullCalendar('refetchEvents');
                    });

                    $scope.combineNameAndSurname = function (name, surname) {
                        var blankSpace = " ";
                        return name.concat(blankSpace.concat(surname));
                    }

                    var ordersSubscription = null;
                    var orderItemsSubscription = null;

                    BartenderService.getBartender($localStorage.logged.id).success(function (data) {
                        $scope.bartender = data;
                        $scope.checkPasswordChanged();
                        CalendarEventFactory.getEventsByUser($scope.bartender).success(function (data) {
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
                        OrderItemFactory.getOrderItemsInWaitingByStaff($scope.bartender).success(function (data) {
                            $scope.inWaiting = data;
                        });
                        OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.bartender).success(function (data) {
                            $scope.making = data;
                        });

                        $stomp.connect('/stomp', {})
                            .then(function (frame) {
                                ordersSubscription = $stomp.subscribe('/topic/orders/' + $scope.bartender.restaurant.id, function (unimportantBoolean, headers, res) {
                                    OrderItemFactory.getOrderItemsInWaitingByStaff($scope.bartender).success(function (data) {
                                        $scope.inWaiting = data;
                                    });
                                    OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.bartender).success(function (data) {
                                        $scope.making = data;
                                    });
                                });
                                orderItemsSubscription = $stomp.subscribe('/topic/orderItems/' + $scope.bartender.restaurant.id, function (unimportantBoolean, headers, res) {
                                    OrderItemFactory.getOrderItemsInWaitingByStaff($scope.bartender).success(function (data) {
                                        $scope.inWaiting = data;
                                    });
                                    OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.bartender).success(function (data) {
                                        $scope.making = data;
                                    });
                                });

                            });

                    });

                    $stomp.setDebug(function (args) {
                        $log.debug(args);
                    });

                    $scope.disconnect = function () {
                        ordersSubscription.unsubscribe();
                        orderItemsSubscription.unsubscribe();
                        $stomp.disconnect().then(function () {
                            $log.info('disconnected');
                        });
                    };

                    $scope.takeOrderItem = function (orderItem) {
                        orderItem.oiStatus = "Currently making";
                        orderItem.staff = $localStorage.logged;
                        $stomp.send('/app/updateOrderItem/' + $scope.bartender.restaurant.id, orderItem);
                        // OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                        //     OrderItemFactory.getOrderItemsInWaitingByStaff($scope.bartender).success(function(data) {
                        //         $scope.inWaiting = data;
                        //     });
                        //     OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.bartender).success(function(data) {
                        //         $scope.making = data;
                        //     });
                        // });
                    }

                    $scope.markAsFinished = function (orderItem) {
                        orderItem.oiStatus = "Ready";
                        $stomp.send('/app/updateOrderItem/' + $scope.bartender.restaurant.id, orderItem);
                        // OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                        //     OrderItemFactory.getOrderItemsInWaitingByStaff($scope.bartender).success(function(data) {
                        //         $scope.inWaiting = data;
                        //     });
                        //     OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.bartender).success(function(data) {
                        //         $scope.making = data;
                        //     });
                        // });
                    }

                    $scope.openUpdateProfileModal = function () {
                        $rootScope.updateBartender = $scope.bartender;
                        $uibModal.open({
                            templateUrl: 'html/bartender/updateBartender.html',
                            controller: 'UpdateBartenderProfileController'
                        }).result.then(function () {
                            BartenderService.getBartender($localStorage.logged.id).success(function (data) {
                                $scope.bartender = data;
                            });
                        });
                    }

                    $scope.checkPasswordChanged = function () {
                        if ($scope.bartender.passwordChanged == false) {
                            $uibModal.open({
                                templateUrl: 'html/bartender/bartenderChangePassword.html',
                                controller: 'ChangeBartenderPasswordController',
                                backdrop: 'static',
                                keyboard: false
                            }).result.then(function () {
                                BartenderService.getBartender($localStorage.logged.id).success(function (data) {
                                    $scope.bartender = data;
                                });
                            });
                        }
                    }
                }
            }

        }

        $scope.logOut = function(){
            $scope.disconnect();
            $localStorage.logged = null;
            $location.path("/");
        };

        init();

    })
    .controller('UpdateBartenderProfileController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, BartenderService, RestaurantService) {

        function getBartender(){
            $scope.bartenderToUpdate = jQuery.extend(true, {}, $rootScope.updateBartender);
            $scope.bartenderToUpdate.date_of_birth = new Date($scope.bartenderToUpdate.date_of_birth);
        }
        getBartender();

        $scope.updateBartender = function (bartender) {
            if ($scope.validateBartenderProfile(bartender) == true) {
                BartenderService.updateBartender(bartender).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.validateBartenderProfile = function(bartender) {
            if (bartender.name == "") {
                alert("Name empty!");
                return false;
            }
            if (bartender.surname == "") {
                alert("Surname empty!");
                return false;
            }
            if (bartender.email == "") {
                alert("Email empty!");
                return false;
            }
            if (bartender.email == undefined || bartender.email == null) {
                alert("Incorrect email!");
                return false;
            }
            if (bartender.password == "") {
                alert("Password empty!");
                return false;
            }
            if (bartender.date_of_birth == undefined) {
                alert("Incorrect date!");
                return false;
            }
            if (bartender.dress_size == null) {
                alert("Incorrect dress size!");
                return false;
            }
            if (bartender.dress_size <= 0) {
                alert("Incorrect dress size!");
                return false;
            }
            if (bartender.shoe_size == null) {
                alert("Incorrect shoe size!");
                return false;
            }
            if (bartender.shoe_size <= 0) {
                alert("Incorrect shoe size!");
                return false;
            }
            return true;
        }

    })
    .controller('ChangeBartenderPasswordController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, BartenderService) {

        BartenderService.getBartender($localStorage.logged.id).success(function(data) {
            $scope.bartender = data;
        });

        $scope.newPassword = "";
        $scope.repeatPassword = "";

        $scope.updateBartenderPassword = function() {
            if ($scope.repeatPassword == "" || $scope.newPassword == "") {
                alert("Password cannot be empty!");
            }
            else if($scope.repeatPassword != $scope.newPassword){
                alert("Passwords do not match!");
            }
            else {
                $scope.bartender.password = $scope.newPassword;
                $scope.bartender.passwordChanged = true;
                BartenderService.updateBartender($scope.bartender).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

    });
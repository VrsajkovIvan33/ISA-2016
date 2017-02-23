/**
 * Created by Verpsychoff on 2/18/2017.
 */

angular.module('restaurantApp.CookFunctionsController',[])
    .controller('CookFunctionsController', function ($localStorage, $scope, $location, $uibModal, $rootScope, uiCalendarConfig, $uibModal, CookService, CalendarEventFactory, OrderItemFactory) {

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

            CookService.getCook($localStorage.logged.id).success(function(data) {
                $scope.cook = data;
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
            });

            $scope.takeOrderItem = function(orderItem) {
                orderItem.oiStatus = "Currently making";
                orderItem.staff = $localStorage.logged;
                OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                    OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                        $scope.inWaiting = data;
                    });
                    OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                        $scope.making = data;
                    });
                });
            }

            $scope.markAsFinished = function(orderItem) {
                orderItem.oiStatus = "Ready";
                OrderItemFactory.updateOrderItem(orderItem).success(function(data) {
                    OrderItemFactory.getOrderItemsInWaitingByStaff($scope.cook).success(function(data) {
                        $scope.inWaiting = data;
                    });
                    OrderItemFactory.getOrderItemsCurrentlyMakingByStaff($scope.cook).success(function(data) {
                        $scope.making = data;
                    });
                });
            }
        }

        init();

    });
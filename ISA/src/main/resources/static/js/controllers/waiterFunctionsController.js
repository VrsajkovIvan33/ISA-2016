/**
 * Created by Verpsychoff on 2/18/2017.
 */

angular.module('restaurantApp.WaiterFunctionsController',[])
    .controller('WaiterFunctionsController', function ($localStorage, $scope, $location, $uibModal, $rootScope, uiCalendarConfig, WaiterService, RestaurantTableFactory, CalendarEventFactory) {
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
            })

        }

        init();
    });
/**
 * Created by Verpsychoff on 12/21/2016.
 */

angular.module('restaurantApp.RestaurantManagerFunctionsController',[])
    .controller('RestaurantManagerFunctionsController', function ($scope, $rootScope, $localStorage, $uibModal, uiCalendarConfig, RestaurantTableFactory, RestaurantmanagerService, RestaurantSegmentFactory, TableRegionFactory, RestaurantUsersFactory, CalendarEventFactory) {
        function init() {
            console.log("Restaurant Manager init()");
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            // $scope.myEvents = [
            //     {
            //         title: 'All Day Test Event',
            //         start: new Date(y, m, 1)
            //     },
            //     {
            //         title: 'Long Test Event',
            //         start: new Date(y, m, d - 5),
            //         end: new Date(y, m, d - 2)
            //     },
            //     {
            //         title: 'Test Birthday Party',
            //         start: new Date(y, m, d + 1, 19, 0),
            //         end: new Date(y, m, d + 1, 22, 30),
            //         allDay: false
            //     }
            // ];
            //
            // $scope.eventSources = [$scope.myEvents];

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

            RestaurantmanagerService.getRestaurantManager($localStorage.logged.id).success(function(data) {
                $scope.restaurantManager = data;
                RestaurantTableFactory.getTablesByRestaurant($scope.restaurantManager.restaurant).success(function(data) {
                    $scope.tables = data;
                    $scope.currentlySelectedTable = data[0];
                });
                CalendarEventFactory.getEventsByRestaurant($scope.restaurantManager.restaurant).success(function(data) {
                    $scope.myEvents = [];
                    var i;
                    for (i = 0; i < data.length; i++) {
                        console.log(new Date(data[i].start).toUTCString());
                        $scope.myEvents.push({
                            title: $scope.combineNameAndSurname(data[i].user.name, data[i].user.surname),
                            start: data[i].start,
                            end: data[i].end,
                            allDay: false
                        })
                    }

                    $scope.eventSources = [$scope.myEvents];
                    //JEBEM TI MAMU U PICKU DA TI JEBEM!
                    uiCalendarConfig.calendars.myCalendar.fullCalendar('addEventSource', $scope.myEvents);
                });
            })

            $scope.tableClick = function(table) {
                $scope.currentlySelectedTable = table;
            }

            $scope.saveChangesOnTables = function() {
                RestaurantTableFactory.setTablesByRestaurant($scope.tables);
            }

            RestaurantSegmentFactory.getRestaurantSegments().success(function(data) {
                $scope.segments = data;
            })

            TableRegionFactory.getTableRegions().success(function(data) {
                $scope.regions = data;
            })

            $scope.openEventModal = function () {
                $uibModal.open({
                    templateUrl : 'html/restaurantManager/createEventModal.html',
                    controller : 'CreateEventController'
                });
            }

        }

        init();

        $scope.loggedManager = $localStorage.logged;

    })
    .controller('CreateEventController', function ($localStorage, $scope, $uibModalInstance, $location, RestaurantmanagerService, RestaurantUsersFactory, TableRegionFactory, CalendarEventFactory) {
        function init(){
            $scope.userToUpdate = $localStorage.logged;

            $scope.unprocessedEvent = new Object();
            $scope.unprocessedEvent.startDate = new Date();
            $scope.unprocessedEvent.endDate = new Date();
            $scope.unprocessedEvent.shiftStart = "12:00";
            $scope.unprocessedEvent.shiftEnd = "20:00";

            $scope.daysOfWeek = new Array();
            $scope.daysOfWeek.push({num: 2, day: 'Monday'});
            $scope.daysOfWeek.push({num: 3, day: 'Tuesday'});
            $scope.daysOfWeek.push({num: 4, day: 'Wednesday'});
            $scope.daysOfWeek.push({num: 5, day: 'Thursday'});
            $scope.daysOfWeek.push({num: 6, day: 'Friday'});
            $scope.daysOfWeek.push({num: 7, day: 'Saturday'});
            $scope.daysOfWeek.push({num: 1, day: 'Sunday'});

            $scope.selectedDayOfWeek = $scope.daysOfWeek[0];

            RestaurantmanagerService.getRestaurantManager($localStorage.logged.id).success(function(data) {
                $scope.restaurantManager = data;
                RestaurantUsersFactory.getUsersByRestaurant($scope.restaurantManager.restaurant).success(function(data) {
                   $scope.restaurantEmployees = data;
                   $scope.unprocessedEvent.user = $scope.restaurantEmployees[0];
                });
            });

            TableRegionFactory.getTableRegions().success(function(data) {
                $scope.regions = data;
                $scope.unprocessedEvent.tableRegion = $scope.regions[0];
            })

            $scope.combineNameAndSurname = function(name, surname) {
                var blankSpace = " ";
                blankSpace.concat(surname);
                return name.concat(blankSpace);
            }
        };

        init();

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        };

        $scope.addEvent = function(){
            $scope.unprocessedEvent.dayInWeek = $scope.selectedDayOfWeek.num;
            CalendarEventFactory.addCalendarEvent($scope.unprocessedEvent).success(function(data){
                if(data != null) {
                    $uibModalInstance.close();
                    window.location.reload();
                }else{
                    alert("An error occurred while creating an event");
                }
            });
        };
    });


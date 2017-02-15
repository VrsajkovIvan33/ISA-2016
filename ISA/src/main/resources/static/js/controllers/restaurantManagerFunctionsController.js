/**
 * Created by Verpsychoff on 12/21/2016.
 */

angular.module('restaurantApp.RestaurantManagerFunctionsController',[])
    .controller('RestaurantManagerFunctionsController', function ($scope, $localStorage, RestaurantTableFactory, RestaurantmanagerService) {
        function init() {
            console.log("Restaurant Manager init()");
            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            $scope.myEvents = [
                {
                    title: 'All Day Test Event',
                    start: new Date(y, m, 1)
                },
                {
                    title: 'Long Test Event',
                    start: new Date(y, m, d - 5),
                    end: new Date(y, m, d - 2)
                },
                {
                    title: 'Test Birthday Party',
                    start: new Date(y, m, d + 1, 19, 0),
                    end: new Date(y, m, d + 1, 22, 30),
                    allDay: false
                }
            ];

            $scope.eventSources = [$scope.myEvents];

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

            RestaurantmanagerService.getRestaurantManager($localStorage.logged.id).success(function(data) {
                $scope.restaurantManager = data;
                RestaurantTableFactory.getTablesByRestaurant($scope.restaurantManager.restaurant).success(function(data) {
                    $scope.tables = data;
                    $scope.currentlySelectedTable = data[0];
                })
            })

            $scope.tableClick = function(table) {
                $scope.currentlySelectedTable = table;
            }

            $scope.saveChangesOnTables = function() {
                RestaurantTableFactory.setTablesByRestaurant($scope.tables);
            }

            //$scope.$on('$locationChangeStart', function(event) {
            //    var answer = confirm("Save changes?")
            //    if (answer) {
            //        RestaurantTableFactory.setTablesByRestaurant($scope.tables);
            //    }
            //});

            // $scope.one = {title: "X"};
            // $scope.places = [ $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
            //     $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one
            // ];
            //
            // $scope.currentlyDragging = {title: "Unused"};
            //
            // $scope.tables = [{title: "0"}, {title: "1"}, {title: "2"}, {title: "3"}, {title: "4"},
            //     {title: "5"}, {title: "6"}, {title: "7"}, {title: "8"}, {title: "9"}];
            //
            // $scope.parrotDraggings = function (item) {
            //     $scope.currentlyDragging.title = item.title;
            // }
            //
            // $scope.parrotDroppings = function (item) {
            //     item.title = $scope.currentlyDragging.title;
            // }

        }

        init();



    });
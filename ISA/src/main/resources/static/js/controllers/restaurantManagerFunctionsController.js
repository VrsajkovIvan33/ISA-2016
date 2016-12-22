/**
 * Created by Verpsychoff on 12/21/2016.
 */

angular.module('restaurantApp.RestaurantManagerFunctionsController',[])
    .controller('RestaurantManagerFunctionsController', function ($scope) {
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

            $scope.one = {title: "X"};
            $scope.places = [ $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one,
                $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one, $scope.one
            ];

            $scope.tables = [{title: "0"}, {title: "1"}, {title: "2"}, {title: "3"}, {title: "4"},
                {title: "5"}, {title: "6"}, {title: "7"}, {title: "8"}, {title: "9"}];



        }

        init();



    });
/**
 * Created by Verpsychoff on 12/18/2016.
 */

angular.module('restaurantApp.WaiterController',[])
    .controller('WaiterController', function ($localStorage, $scope, $location, $uibModal, $rootScope, WaiterService) {
        function init() {
            console.log("Kao neko registrovanje");
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

            $rootScope.$on('updateCalendar', function () {
                window.calendar.fullCalendar('refetchEvents');
            });

            $scope.combineNameAndSurname = function(name, surname) {
                var blankSpace = " ";
                return name.concat(blankSpace.concat(surname));
            }

            $scope.changeView = function(view,calendar) {
                uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
            };

            $scope.list1 = {title: 'AngularJS - Drag Me'};
            $scope.list2 = {};

        }

        init();


        $scope.waiters = [];
        function getWaitersByRestaurant(){
            WaiterService.getWaitersByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.waiters = data;
            });
        }

        $scope.removeWaiter = function (waiter) {
            var index = $scope.waiters.indexOf(waiter);
            WaiterService.removeWaiter(waiter).success(function (data) {
                if(index > -1)
                    $scope.waiters.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/waiter/addNewWaiter.html',
                controller : 'NewWaiterController'
            });
        }

        $scope.openUpdateModal = function (waiter) {
            $rootScope.updateWaiter = waiter;
            $uibModal.open({
                templateUrl : 'html/waiter/updateWaiter.html',
                controller : 'UpdateWaiterController'
            });
        }

        getWaitersByRestaurant();
    })
    .controller('NewWaiterController', function ($localStorage, $scope, $location, $uibModalInstance, WaiterService, RestaurantService) {

        $scope.newWaiter = {id:null, name:'', surname:'', email:'', password:'', type:'WAITER', version:0, date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, review:0};
        $scope.addWaiter = function (waiter) {
            WaiterService.addWaiter(waiter).success(function (data) {
                $scope.newWaiter = {id:null, name:'', surname:'', email:'', password:'', type:'WAITER', version:0, date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, review:0};
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurants = [];
        function getRestaurants(){
            RestaurantService.getRestaurants().success(function (data) {
                $scope.restaurants = data;
            });
        }

        getRestaurants();
    })
    .controller('UpdateWaiterController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, WaiterService, RestaurantService) {

        function getWaiter(){
            $scope.waiterToUpdate = jQuery.extend(true, {}, $rootScope.updateWaiter);
            $scope.waiterToUpdate.date_of_birth = new Date($scope.waiterToUpdate.date_of_birth);
        }
        getWaiter();

        $scope.updateWaiter = function (waiter) {
            WaiterService.updateWaiter(waiter).success(function (data) {
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurants = [];
        function getRestaurants(){
            RestaurantService.getRestaurants().success(function (data) {
                $scope.restaurants = data;
            });
        }

        getRestaurants();
    });

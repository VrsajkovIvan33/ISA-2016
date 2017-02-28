/**
 * Created by Verpsychoff on 12/18/2016.
 */

angular.module('restaurantApp.WaiterController',[])
    .controller('WaiterController', function ($localStorage, $scope, $location, $uibModal, $rootScope, WaiterService) {
        function init() {
            if($localStorage.logged == null)
                $location.path("/");
            else {
                if ($localStorage.logged.type != 'RESTAURANTMANAGER')
                    $location.path("/");
            }

            $scope.logOut = function(){
                $localStorage.logged = null;
                $location.path("/");
            };

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
            }).result.then(function(){
                getWaitersByRestaurant();
            });
        }

        $scope.openUpdateModal = function (waiter) {
            $rootScope.updateWaiter = waiter;
            $uibModal.open({
                templateUrl : 'html/waiter/updateWaiter.html',
                controller : 'UpdateWaiterController'
            }).result.then(function(){
                getWaitersByRestaurant();
            });
        }

        getWaitersByRestaurant();
    })
    .controller('NewWaiterController', function ($localStorage, $scope, $location, $uibModalInstance, WaiterService, RestaurantService) {

        $scope.newWaiter = {id:null, name:'', surname:'', email:'', password:'', type:'WAITER', version:0, date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, review:0, passwordChanged: false};
        $scope.addWaiter = function (waiter) {
            if(validate(waiter)) {
                WaiterService.addWaiter(waiter).success(function (data) {
                    $scope.newWaiter = {
                        id: null,
                        name: '',
                        surname: '',
                        email: '',
                        password: '',
                        type: 'WAITER',
                        version: 0,
                        date_of_birth: null,
                        dress_size: 0,
                        shoe_size: 0,
                        restaurant: $localStorage.logged.restaurant,
                        review: 0,
                        passwordChanged: false
                    };
                    $uibModalInstance.close();
                });
            }
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


        $scope.repeatPassword = '';

        function validate(waiter) {
            if(waiter.name == '' || waiter.surname == '' || waiter.email == '' ||
                waiter.password == '' || waiter.date_of_birth == null){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(waiter.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(waiter.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if(waiter.dress_size == undefined || waiter.dress_size == 0 || waiter.shoe_size == undefined || waiter.shoe_size == 0){
                alert('Dress size and shoe size must be above 0 and below 100');
                return false;
            }

            if($scope.repeatPassword != waiter.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateWaiterController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, WaiterService, RestaurantService) {

        function getWaiter(){
            $scope.waiterToUpdate = jQuery.extend(true, {}, $rootScope.updateWaiter);
            $scope.waiterToUpdate.date_of_birth = new Date($scope.waiterToUpdate.date_of_birth);
        }
        getWaiter();

        $scope.updateWaiter = function (waiter) {
            if(validate(waiter)) {
                WaiterService.updateWaiter(waiter).success(function (data) {
                    $uibModalInstance.close();
                });
            }
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



        $scope.repeatPassword = $scope.waiterToUpdate.password;

        function validate(waiter) {
            if(waiter.name == '' || waiter.surname == '' || waiter.email == '' ||
                waiter.password == '' || waiter.date_of_birth == null){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(waiter.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(waiter.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if(waiter.dress_size == undefined || waiter.dress_size == 0 || waiter.shoe_size == undefined || waiter.shoe_size == 0){
                alert('Dress size and shoe size must be above 0 and below 100');
                return false;
            }

            if($scope.repeatPassword != waiter.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });

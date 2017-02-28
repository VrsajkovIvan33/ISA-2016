/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.BartenderController',[])
    .controller('BartenderController', function ($localStorage, $scope, $location, $uibModal, $rootScope, BartenderService) {

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

        $scope.bartenders = [];
        function getBartendersByRestaurant(){
            BartenderService.getBartendersByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.bartenders = data;
            });
        }

        $scope.removeBartender = function (bartender) {
            var index = $scope.bartenders.indexOf(bartender);
            BartenderService.removeBartender(bartender).success(function (data) {
                if(index > -1)
                    $scope.bartenders.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/bartender/addNewBartender.html',
                controller : 'NewBartenderController'
            }).result.then(function(){
                getBartendersByRestaurant();
            });
        }

        $scope.openUpdateModal = function (bartender) {
            $rootScope.updateBartender = bartender;
            $uibModal.open({
                templateUrl : 'html/bartender/updateBartender.html',
                controller : 'UpdateBartenderController'
            }).result.then(function(){
                getBartendersByRestaurant();
            });
        }

        getBartendersByRestaurant();
    })
    .controller('NewBartenderController', function ($localStorage, $scope, $location, $uibModalInstance, BartenderService, RestaurantService) {

        $scope.newBartender = {id:null, name:'', surname:'', email:'', password:'', type:'BARTENDER', date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, version:0, passwordChanged:false};
        $scope.addBartender = function (bartender) {
            if(validate(bartender)) {
                BartenderService.addBartender(bartender).success(function (data) {
                    $scope.newBartender = {
                        id: null,
                        name: '',
                        surname: '',
                        email: '',
                        password: '',
                        type: 'BARTENDER',
                        date_of_birth: null,
                        dress_size: 0,
                        shoe_size: 0,
                        restaurant: $localStorage.logged.restaurant,
                        version: 0,
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

        function validate(bartender) {
            if(bartender.name == '' || bartender.surname == '' || bartender.email == '' ||
                bartender.password == '' || bartender.date_of_birth == null){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(bartender.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(bartender.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if(bartender.dress_size == undefined || bartender.dress_size == 0 || bartender.shoe_size == undefined || bartender.shoe_size == 0){
                alert('Dress size and shoe size must be above 0 and below 100');
                return false;
            }

            if($scope.repeatPassword != bartender.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateBartenderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, BartenderService, RestaurantService) {

        function getBartender(){
            $scope.bartenderToUpdate = jQuery.extend(true, {}, $rootScope.updateBartender);
            $scope.bartenderToUpdate.date_of_birth = new Date($scope.bartenderToUpdate.date_of_birth);
        }
        getBartender();

        $scope.updateBartender = function (bartender) {
            if(validate(bartender)) {
                BartenderService.updateBartender(bartender).success(function (data) {
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



        $scope.repeatPassword = $scope.bartenderToUpdate.password;

        function validate(bartender) {
            if(bartender.name == '' || bartender.surname == '' || bartender.email == '' ||
                bartender.password == '' || bartender.date_of_birth == null){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(bartender.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(bartender.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if(bartender.dress_size == undefined || bartender.dress_size == 0 || bartender.shoe_size == undefined || bartender.shoe_size == 0){
                alert('Dress size and shoe size must be above 0 and below 100');
                return false;
            }

            if($scope.repeatPassword != bartender.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });
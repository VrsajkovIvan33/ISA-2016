/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantmanagerController',[])
    .controller('RestaurantmanagerController', function ($localStorage, $scope, $location, $uibModal, $rootScope, RestaurantmanagerService) {

        $scope.restaurantManagers = [];
        function getRestaurantManagers(){
            RestaurantmanagerService.getRestaurantManagers().success(function (data) {
                $scope.restaurantManagers = data;
            });
        }

        $scope.removeRestaurantManager = function (restaurantManager) {
            var index = $scope.restaurantManagers.indexOf(restaurantManager);
            RestaurantmanagerService.removeRestaurantManager(restaurantManager).success(function (data) {
                if(index > -1)
                    $scope.restaurantManagers.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/systemManager/addNewRestaurantManager.html',
                controller : 'NewRestaurantmanagerController'
            }).result.then(function(){
                getRestaurantManagers();
            });
        }

        $scope.openUpdateModal = function (restaurantManager) {
            $rootScope.updateRestaurantManager = restaurantManager;
            $uibModal.open({
                templateUrl : 'html/systemManager/updateRestaurantManager.html',
                controller : 'UpdateRestaurantmanagerController'
            }).result.then(function(){
                getRestaurantManagers();
            });
        }

        getRestaurantManagers();


    })
    .controller('NewRestaurantmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, RestaurantmanagerService, RestaurantService) {

        $scope.newRestaurantManager = {id:null, name:'', surname:'', email:'', password:'', type:'RESTAURANTMANAGER', date_of_birth:null, restaurant:null, version:0};
        $scope.addRestaurantManager = function (restaurantManager) {
            if(validate(restaurantManager)) {
                RestaurantmanagerService.addRestaurantManager(restaurantManager).success(function (data) {
                    $scope.newRestaurantManager = {
                        id: null,
                        name: '',
                        surname: '',
                        email: '',
                        password: '',
                        type: 'RESTAURANTMANAGER',
                        date_of_birth: null,
                        restaurant: null,
                        version: 0
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

        function validate(restaurantManager) {
            if(restaurantManager.name == '' || restaurantManager.surname == '' || restaurantManager.email == '' ||
                restaurantManager.password == '' || restaurantManager.restaurant == null || restaurantManager.date_of_birth == null){
                alert('There are empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(restaurantManager.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(restaurantManager.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if($scope.repeatPassword != restaurantManager.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateRestaurantmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, RestaurantmanagerService, RestaurantService) {

        function getRestaurantManager(){
            $scope.restaurantManagerToUpdate = jQuery.extend(true, {}, $rootScope.updateRestaurantManager);
            $scope.restaurantManagerToUpdate.date_of_birth = new Date($scope.restaurantManagerToUpdate.date_of_birth);
        }
        getRestaurantManager();

        $scope.updateRestaurantManager = function (restaurantManager) {
            if(validate(restaurantManager)) {
                RestaurantmanagerService.updateRestaurantManager(restaurantManager).success(function (data) {
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


        $scope.repeatPassword = $scope.restaurantManagerToUpdate.password;

        function validate(restaurantManager) {
            if(restaurantManager.name == '' || restaurantManager.surname == '' || restaurantManager.email == '' ||
                restaurantManager.password == '' || restaurantManager.restaurant == null || restaurantManager.date_of_birth == null){
                alert('There are empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(restaurantManager.email)){
                alert('Wrong email address');
                return false;
            }

            var date = new Date();
            if(restaurantManager.date_of_birth > date){
                alert('Date of birth must be before today');
                return false;
            }

            if($scope.repeatPassword != restaurantManager.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });
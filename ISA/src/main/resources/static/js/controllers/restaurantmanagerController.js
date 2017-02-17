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
            });
        }

        $scope.openUpdateModal = function (restaurantManager) {
            $rootScope.updateRestaurantManager = restaurantManager;
            $uibModal.open({
                templateUrl : 'html/systemManager/updateRestaurantManager.html',
                controller : 'UpdateRestaurantmanagerController'
            });
        }

        getRestaurantManagers();


    })
    .controller('NewRestaurantmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, RestaurantmanagerService, RestaurantService) {

        $scope.newRestaurantManager = {id:null, name:'', surname:'', email:'', password:'', type:'RESTAURANTMANAGER', date_of_birth:null, restaurant:null, version:0};
        $scope.addRestaurantManager = function (restaurantManager) {
            RestaurantmanagerService.addRestaurantManager(restaurantManager).success(function (data) {
                $scope.newRestaurantManager = {id:null, name:'', surname:'', email:'', password:'', type:'RESTAURANTMANAGER', date_of_birth:null, restaurant:null, version:0};
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
    .controller('UpdateRestaurantmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, RestaurantmanagerService, RestaurantService) {

        function getRestaurantManager(){
            $scope.restaurantManagerToUpdate = jQuery.extend(true, {}, $rootScope.updateRestaurantManager);
            $scope.restaurantManagerToUpdate.date_of_birth = new Date($scope.restaurantManagerToUpdate.date_of_birth);
        }
        getRestaurantManager();

        $scope.updateRestaurantManager = function (restaurantManager) {
            RestaurantmanagerService.updateRestaurantManager(restaurantManager).success(function (data) {
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
/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.CookController',[])
    .controller('CookController', function ($localStorage, $scope, $location, $uibModal, $rootScope, CookService) {

        $scope.cooks = [];
        function getCooksByRestaurant(){
            CookService.getCooksByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.cooks = data;
            });
        }

        $scope.removeCook = function (cook) {
            var index = $scope.cooks.indexOf(cook);
            CookService.removeCook(cook).success(function (data) {
                if(index > -1)
                    $scope.cooks.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/cook/addNewCook.html',
                controller : 'NewCookController'
            }).result.then(function(){
                getCooksByRestaurant();
            });
        }

        $scope.openUpdateModal = function (cook) {
            $rootScope.updateCook = cook;
            $uibModal.open({
                templateUrl : 'html/cook/updateCook.html',
                controller : 'UpdateCookController'
            }).result.then(function(){
                getCooksByRestaurant();
            });
        }

        getCooksByRestaurant();

        $scope.cookTypes  = ["Salad", "Cooked Meal", "Grilled Dish", "All"];
    })
    .controller('NewCookController', function ($localStorage, $scope, $location, $uibModalInstance, CookService, RestaurantService) {

        $scope.newCook = {id:null, name:'', surname:'', email:'', password:'', type:'COOK', version:0, date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, typeCook:null, passwordChanged:false};
        $scope.addCook = function (cook) {
            CookService.addCook(cook).success(function (data) {
                $scope.newCook = {id:null, name:'', surname:'', email:'', password:'', type:'COOK', version:0, date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, typeCook:null};
                $uibModalInstance.close();
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

        $scope.cookTypes  = ["Salad", "Cooked Meal", "Grilled Dish", "All"];
    })
    .controller('UpdateCookController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, CookService, RestaurantService) {

        function getCook(){
            $scope.cookToUpdate = jQuery.extend(true, {}, $rootScope.updateCook);
            $scope.cookToUpdate.date_of_birth = new Date($scope.cookToUpdate.date_of_birth);
        }
        getCook();

        $scope.updateCook = function (cook) {
            CookService.updateCook(cook).success(function (data) {
                $uibModalInstance.close();
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

        $scope.cookTypes  = ["Salad", "Cooked Meal", "Grilled Dish", "All"];
    });
/**
 * Created by Marko on 2/18/2017.
 */
angular.module('restaurantApp.BeverageController',[])
    .controller('BeverageController', function ($localStorage, $scope, $location, $uibModal, $rootScope, MenuService) {

        $scope.beverages = [];
        function getBeverages(){
            MenuService.getMenusByMRestaurantAndMType($localStorage.logged.restaurant.id, "Drink").success(function (data) {
                $scope.beverages = data;
            });
        }

        getBeverages();


        $scope.removeMenu = function (menu) {
            var index = $scope.beverages.indexOf(menu);
            MenuService.removeMenu(menu).success(function (data) {
                if(index > -1)
                    $scope.beverages.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/menu/addNewBeverage.html',
                controller : 'NewBeverageController'
            }).result.then(function(){
                getBeverages();
            });
        }

        $scope.openUpdateModal = function (beverage) {
            $rootScope.updateBeverage = beverage;
            $uibModal.open({
                templateUrl : 'html/menu/updateBeverage.html',
                controller : 'UpdateBeverageController'
            }).result.then(function(){
                getBeverages();
            });
        }

        //$scope.menuTypes = ["Drink", "Salad", "Cooked Meal", "Grilled Dish"];
    })
    .controller('NewBeverageController', function ($localStorage, $scope, $location, $uibModalInstance, MenuService, RestaurantService) {

        $scope.newBeverage = {mId:null, mName:'', mType:'Drink' , mDescription:'', mPrice:0, mReview:0, mRestaurant:$localStorage.logged.restaurant, version:0};
        $scope.addMenu = function (menu) {
            if(validate(menu)) {
                MenuService.addMenu(menu).success(function (data) {
                    $scope.newBeverage = {
                        mId: null,
                        mName: '',
                        mType: 'Drink',
                        mDescription: '',
                        mPrice: 0,
                        mReview: 0,
                        mRestaurant: $localStorage.logged.restaurant,
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

        //$scope.menuTypes = ["Drink", "Salad", "Cooked Meal", "Grilled Dish"];


        function validate(beverage) {
            if(beverage.mName == ''){
                alert('There is empty field');
                return false;
            }

            if(beverage.mPrice == undefined || beverage.mPrice == 0){
                alert('Price must be above 0 and below 10000000');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateBeverageController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, MenuService, RestaurantService) {

        $scope.beverageToUpdate = jQuery.extend(true, {}, $rootScope.updateBeverage);

        $scope.updateMenu = function (menu) {
            if(validate(menu)) {
                MenuService.updateMenu(menu).success(function (data) {
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

        //$scope.menuTypes = ["Drink", "Salad", "Cooked Meal", "Grilled Dish"];


        function validate(beverage) {
            if(beverage.mName == ''){
                alert('There is empty field');
                return false;
            }

            if(beverage.mPrice == undefined || beverage.mPrice == 0){
                alert('Price must be above 0 and below 10000000');
                return false;
            }

            return true;
        }
    });
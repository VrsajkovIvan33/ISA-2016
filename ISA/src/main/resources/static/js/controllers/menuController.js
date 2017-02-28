/**
 * Created by Marko on 2/18/2017.
 */
angular.module('restaurantApp.MenuController',[])
    .controller('MenuController', function ($localStorage, $scope, $location, $uibModal, $rootScope, MenuService) {

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

        $scope.menus = [];
        function getMenus(){
            MenuService.getMenusByMRestaurantAndMType($localStorage.logged.restaurant.id, "Menu").success(function (data) {
                $scope.menus = data;
            });
        }

        getMenus();


        $scope.removeMenu = function (menu) {
            var index = $scope.menus.indexOf(menu);
            MenuService.removeMenu(menu).success(function (data) {
                if(index > -1)
                    $scope.menus.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/menu/addNewMenu.html',
                controller : 'NewMenuController'
            }).result.then(function(){
                getMenus();
            });
        }

        $scope.openUpdateModal = function (menu) {
            $rootScope.updateMenu = menu;
            $uibModal.open({
                templateUrl : 'html/menu/updateMenu.html',
                controller : 'UpdateMenuController'
            }).result.then(function(){
                getMenus();
            });
        }

        $scope.menuTypes = ["Salad", "Cooked Meal", "Grilled Dish"];
    })
    .controller('NewMenuController', function ($localStorage, $scope, $location, $uibModalInstance, MenuService, RestaurantService) {

        $scope.newMenu = {mId:null, mName:'', mType:'' , mDescription:'', mPrice:0, mReview:0, mRestaurant:$localStorage.logged.restaurant, version:0};
        $scope.addMenu = function (menu) {
            if(validate(menu)) {
                MenuService.addMenu(menu).success(function (data) {
                    $scope.newMenu = {
                        mId: null,
                        mName: '',
                        mType: '',
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

        $scope.menuTypes = ["Salad", "Cooked Meal", "Grilled Dish"];


        function validate(menu) {
            if(menu.mName == '' || menu.mType == ''){
                alert('There is empty field');
                return false;
            }

            if(menu.mPrice == undefined || menu.mPrice == 0){
                alert('Price must be above 0 and below 10000000');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateMenuController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, MenuService, RestaurantService) {

        $scope.menuToUpdate = jQuery.extend(true, {}, $rootScope.updateMenu);

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

        $scope.menuTypes = ["Salad", "Cooked Meal", "Grilled Dish"];



        function validate(menu) {
            if(menu.mName == '' || menu.mType == ''){
                alert('There is empty field');
                return false;
            }

            if(menu.mPrice == undefined || menu.mPrice == 0){
                alert('Price must be above 0 and below 10000000');
                return false;
            }

            return true;
        }
    });
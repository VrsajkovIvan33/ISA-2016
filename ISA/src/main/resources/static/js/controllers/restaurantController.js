/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantController',[])
    .controller('RestaurantController', function ($localStorage, $scope, $location, $uibModal, $rootScope, RestaurantService) {

        $scope.restaurants = [];
        function getRestaurants(){
            RestaurantService.getRestaurants().success(function (data) {
                $scope.restaurants = data;
            });
        }
        
        $scope.removeRestaurant = function (restaurant) {
            var index = $scope.restaurants.indexOf(restaurant);
            RestaurantService.removeRestaurant(restaurant).success(function (data) {
                if(index > -1)
                    $scope.restaurants.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/systemManager/addNewRestaurant.html',
                controller : 'NewRestaurantController'
            }).result.then(function(){
                getRestaurants();
            });
        }

        $scope.openUpdateModal = function (restaurant) {
            $rootScope.updateRestaurant = restaurant;
            $uibModal.open({
                templateUrl : 'html/systemManager/updateRestaurant.html',
                controller : 'UpdateRestaurantController'
            }).result.then(function(){
                getRestaurants();
            });
        }

        getRestaurants();

        $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];
    })
    .controller('NewRestaurantController', function ($localStorage, $scope, $location, $uibModalInstance, RestaurantService) {

        $scope.newRestaurant = {id:null, rName:'', rType:'Localcuisine', providers:null, version:0};
        $scope.addRestaurant = function (restaurant) {
            RestaurantService.addRestaurant(restaurant).success(function (data) {
                $scope.newRestaurant = {id:null, rName:'', rType:'Localcuisine', providers:null, version:0};
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];
    })
    .controller('UpdateRestaurantController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, RestaurantService) {

        $scope.restaurantToUpdate = jQuery.extend(true, {}, $rootScope.updateRestaurant);
        $scope.updateRestaurant = function (restaurant) {
            RestaurantService.updateRestaurant(restaurant).success(function (data) {
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];
    });
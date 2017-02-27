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
    .controller('NewRestaurantController', function ($localStorage, $scope, $location, $uibModalInstance, NgMap, RestaurantService) {
        $scope.modalMode = 1;
        $scope.newRestaurant = {id:null, rName:'', rType:'Localcuisine', providers:null, version:0, latitude:0, longitude:0};
        $scope.addRestaurant = function (restaurant) {
            if(validate(restaurant)) {
                RestaurantService.addRestaurant(restaurant).success(function (data) {
                    $scope.newRestaurant = {id: null, rName: '', rType: 'Localcuisine', providers: null, version: 0};
                    $uibModalInstance.close();
                });
            }
        }
        NgMap.getMap().then(function(map) {
            $scope.map = map;
        });
        $scope.placeMarker = function(e) {
            $scope.newRestaurant.latitude = e.latLng.lat();
            $scope.newRestaurant.longitude = e.latLng.lng();
        }

        $scope.next = function(){
            $scope.modalMode = 2;
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];



        function validate(restaurant) {
            if(restaurant.rName == ''){
                alert('There is empty field');
                return false;
            }

            for(var i = 0; i < $scope.restaurantTypes.length; i++){
                if(restaurant.rType == $scope.restaurantTypes[i])
                    return true;
            }

            return false;
        }
    })
    .controller('UpdateRestaurantController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, RestaurantService) {

        $scope.restaurantToUpdate = jQuery.extend(true, {}, $rootScope.updateRestaurant);
        $scope.updateRestaurant = function (restaurant) {
            if(validate(restaurant)) {
                RestaurantService.updateRestaurant(restaurant).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurantTypes = ["Localcuisine", "Italian", "Chinese", "Vegan", "Country"];


        function validate(restaurant) {
            if(restaurant.rName == ''){
                alert('There is empty field');
                return false;
            }

            for(var i = 0; i < $scope.restaurantTypes.length; i++){
                if(restaurant.rType == $scope.restaurantTypes[i])
                    return true;
            }

            return false;
        }
    });
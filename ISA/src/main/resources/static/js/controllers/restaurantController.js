/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantController',[])
    .controller('RestaurantController', function ($localStorage, $scope, $location, RestaurantService) {

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

        $scope.newRestaurant = {id:null, rName:'', rType:'', version:1};
        $scope.addRestaurant = function (restaurant) {
            RestaurantService.addRestaurant(restaurant).success(function (data) {
                $scope.restaurants.push(data);
                $location.path('/restaurants');
            });
        }

        $scope.updateRestaurant = function (restaurant) {
            RestaurantService.updateRestaurant(restaurant).success(function (data) {

            });
        }

        getRestaurants();
    });
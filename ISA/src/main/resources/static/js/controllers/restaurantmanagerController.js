/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantmanagerController',[])
    .controller('RestaurantmanagerController', function ($localStorage, $scope, $location, RestaurantmanagerService) {

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

        $scope.newRestaurantManager = {id:null, name:'', surname:'', email:'', password:'', type:'RESTAURANTMANAGER', version:1};
        $scope.addRestaurantManager = function (restaurantManager) {
            RestaurantmanagerService.addRestaurantManager(restaurantManager).success(function (data) {
                $scope.restaurantManagers.push(data);
                $location.path('/restaurantmanagers');
            });
        }

        $scope.updateRestaurantManager = function (restaurantManager) {
            RestaurantmanagerService.updateRestaurantManager(restaurantManager).success(function (data) {

            });
        }

        getRestaurantManagers();
    });
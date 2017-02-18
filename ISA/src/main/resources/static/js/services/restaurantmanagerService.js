/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantmanagerService', [])
    .factory('RestaurantmanagerService', function($http){
        var factory = {};

        factory.getRestaurantManagers = function () {
            return $http.get('/getRestaurantManagers');
        }

        factory.getRestaurantManager = function(id) {
            return $http.get('/getRestaurantManager/' + id);
        }

        factory.removeRestaurantManager = function (restaurantManager) {
            return $http.delete('/removeRestaurantManager/' + restaurantManager.id, {"id":restaurantManager.id});
        }

        factory.addRestaurantManager = function (restaurantManager) {
            return $http.post('/addRestaurantManager', restaurantManager);
        }

        factory.updateRestaurantManager = function (restaurantManager) {
            return $http.post('/updateRestaurantManager', restaurantManager);
        }

        return factory;
    });
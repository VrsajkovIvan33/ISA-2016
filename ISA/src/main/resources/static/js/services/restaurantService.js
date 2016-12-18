/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.RestaurantService', [])
    .factory('RestaurantService', function($http){
        var factory = {};

        factory.getRestaurants = function () {
            return $http.get('/getRestaurants');
        }

        factory.removeRestaurant = function (restaurant) {
            return $http.delete('/removeRestaurant/' + restaurant.id, {"id":restaurant.id});
        }

        factory.addRestaurant = function (restaurant) {
            return $http.post('/addRestaurant', restaurant);
        }

        factory.updateRestaurant = function (restaurant) {
            return $http.post('/updateRestaurant', restaurant);
        }
        
        return factory;
    });
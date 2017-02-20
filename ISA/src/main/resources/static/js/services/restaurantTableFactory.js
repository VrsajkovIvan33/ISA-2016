/**
 * Created by Verpsychoff on 2/14/2017.
 */

angular.module('restaurantApp.RestaurantTableFactory',[])
    .factory('RestaurantTableFactory', function ($http) {

        var factory = {};

        factory.getTablesByRestaurant = function(restaurant) {
            return $http.get('/RestaurantTablesByRestaurant/' + restaurant.id);
        }

        factory.setTablesByRestaurant = function(restaurantTables) {
            return $http.put('/RestaurantTables', restaurantTables);
        }

        return factory;

    });

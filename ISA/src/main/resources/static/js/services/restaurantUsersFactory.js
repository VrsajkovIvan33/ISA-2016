/**
 * Created by Verpsychoff on 2/17/2017.
 */

angular.module('restaurantApp.RestaurantUsersFactory',[])
    .factory('RestaurantUsersFactory', function ($http) {

        var factory = {};

        factory.getUsersByRestaurant = function(restaurant) {
            return $http.get('/UsersByRestaurant/' + restaurant.id);
        }

        return factory;

    });
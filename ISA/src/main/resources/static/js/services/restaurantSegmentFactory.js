/**
 * Created by Verpsychoff on 2/16/2017.
 */

angular.module('restaurantApp.RestaurantSegmentFactory',[])
    .factory('RestaurantSegmentFactory', function ($http) {

        var factory = {};

        factory.getRestaurantSegments = function() {
            return $http.get('/RestaurantSegments');
        }

        return factory;

    });

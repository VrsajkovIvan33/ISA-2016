/**
 * Created by Verpsychoff on 2/16/2017.
 */

angular.module('restaurantApp.TableRegionFactory',[])
    .factory('TableRegionFactory', function ($http) {

        var factory = {};

        factory.getTableRegions = function() {
            return $http.get('/TableRegions');
        }

        return factory;

    });
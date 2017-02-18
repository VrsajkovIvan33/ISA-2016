/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.CookService', [])
    .factory('CookService', function($http){
        var factory = {};

        factory.getCooks = function () {
            return $http.get('/getCooks');
        }

        factory.getCooksByRestaurant = function (id) {
            return $http.get('/getCooksByRestaurant/' + id, {"id":id});
        }

        factory.removeCook = function (cook) {
            return $http.delete('/removeCook/' + cook.id, {"id":cook.id});
        }

        factory.addCook = function (cook) {
            return $http.post('/addCook', cook);
        }

        factory.updateCook = function (cook) {
            return $http.post('/updateCook', cook);
        }

        return factory;
    });
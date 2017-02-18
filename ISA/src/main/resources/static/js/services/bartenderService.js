/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.BartenderService', [])
    .factory('BartenderService', function($http){
        var factory = {};

        factory.getBartenders = function () {
            return $http.get('/getBartenders');
        }

        factory.getBartendersByRestaurant = function (id) {
            return $http.get('/getBartendersByRestaurant/' + id, {"id":id});
        }

        factory.removeBartender = function (bartender) {
            return $http.delete('/removeBartender/' + bartender.id, {"id":bartender.id});
        }

        factory.addBartender = function (bartender) {
            return $http.post('/addBartender', bartender);
        }

        factory.updateBartender = function (bartender) {
            return $http.post('/updateBartender', bartender);
        }

        return factory;
    });
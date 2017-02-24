/**
 * Created by Marko on 2/24/2017.
 */
angular.module('restaurantApp.TenderService', [])
    .factory('TenderService', function($http){
        var factory = {};

        factory.getTenders = function () {
            return $http.get('/getTenders');
        }

        factory.getTender = function(id) {
            return $http.get('/getTender/' + id);
        }

        factory.getTendersByTRestaurant = function (id) {
            return $http.get('/getTendersByTRestaurant/' + id, {"id":id});
        }

        factory.getTendersByTRestaurantAndTStatus = function (id, status) {
            return $http.get('/getTendersByTRestaurantAndTStatus/' + id + '/' + type, {"id":id, "status":status});
        }

        factory.removeTender = function (tender) {
            return $http.delete('/removeTender/' + tender.tId, {"id":tender.tId});
        }

        factory.addTender = function (tender) {
            return $http.post('/addTender', tender);
        }

        factory.updateTender = function (tender) {
            return $http.post('/updateTender', tender);
        }

        return factory;
    });
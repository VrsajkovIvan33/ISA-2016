/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.WaiterService', [])
    .factory('WaiterService', function($http){
        var factory = {};

        factory.getWaiters = function () {
            return $http.get('/getWaiters');
        }

        factory.getWaitersByRestaurant = function (id) {
            return $http.get('/getWaitersByRestaurant/' + id, {"id":id});
        }

        factory.removeWaiter = function (waiter) {
            return $http.delete('/removeWaiter/' + waiter.id, {"id":waiter.id});
        }

        factory.addWaiter = function (waiter) {
            return $http.post('/addWaiter', waiter);
        }

        factory.updateWaiter = function (waiter) {
            return $http.post('/updateWaiter', waiter);
        }

        return factory;
    });
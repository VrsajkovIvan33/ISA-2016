/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.BillService', [])
    .factory('BillService', function($http){
        var factory = {};

        factory.getBillsByWaiter = function (id) {
            return $http.get('/getBillsByWaiter/' + id, {"id":id});
        }

        factory.getBillsByRestaurant = function (id) {
            return $http.get('/getBillsByRestaurant/' + id, {"id":id});
        }

        return factory;
    });
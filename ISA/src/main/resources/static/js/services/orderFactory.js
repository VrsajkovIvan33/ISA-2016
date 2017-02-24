/**
 * Created by Verpsyhcoff on 2/20/2017.
 */

angular.module('restaurantApp.OrderFactory',[])
    .factory('OrderFactory', function ($http) {

        var factory = {};

        factory.getUnassignedByUser = function(user) {
            return $http.get("/OrdersUnassignedByUser/" + user.id);
        }

        factory.getOrdersByWaiter = function(waiter) {
            return $http.get("/OrdersByWaiter/" + waiter.id);
        }

        factory.getOrderById = function(orderId) {
            return $http.get("/Orders/" + orderId);
        }

        factory.addOrder = function(order) {
            return $http.post("/Orders", order);
        }

        factory.updateOrder = function(order) {
            return $http.put("/Orders", order);
        }

        factory.deleteOrder = function(order) {
            return $http.delete("/removeOrder/" + order.id);
        }

        factory.finalizeOrder = function(order) {
            return $http.put("/FinalizeOrder", order);
        }

        return factory;

    });

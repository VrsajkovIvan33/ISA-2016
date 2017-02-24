/**
 * Created by Verpsychoff on 2/20/2017.
 */

angular.module('restaurantApp.OrderItemFactory',[])
    .factory('OrderItemFactory', function ($http) {

        var factory = {};

        factory.getOrderItemsInWaitingByStaff = function(user) {
            return $http.get("/OrderItemsInWaitingByStaff/" + user.id);
        }

        factory.getOrderItemsCurrentlyMakingByStaff = function(user) {
            return $http.get("/OrderItemsCurrentlyMakingByStaff/" + user.id);
        }

        factory.addOrderItem = function(orderItem) {
            return $http.post("/OrderItems", orderItem);
        }

        factory.updateOrderItem = function(orderItem) {
            return $http.put("/OrderItems", orderItem);
        }

        factory.deleteOrderItem = function(orderItem) {
            return $http.delete("/removeOrderItem/" + orderItem.id);
        }

        return factory;

    });

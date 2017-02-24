/**
 * Created by Verpsychoff on 2/24/2017.
 */

angular.module('restaurantApp.VisitHistoryFactory',[])
    .factory('VisitHistoryFactory', function ($http) {

        var factory = {};

        factory.getHistoriesByGuest = function(guest) {
            return $http.get("/VisitHistories/" + guest.id);
        }

        factory.updateHistory = function(history) {
            return $http.put("/UpdateHistory", history);
        }

        return factory;

    });

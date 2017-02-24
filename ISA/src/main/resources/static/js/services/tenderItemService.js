/**
 * Created by Marko on 2/24/2017.
 */
angular.module('restaurantApp.TenderItemService', [])
    .factory('TenderItemService', function($http){
        var factory = {};

        factory.getTenderItems = function () {
            return $http.get('/getTenderItems');
        }

        factory.getTenderItem = function(id) {
            return $http.get('/getTenderItem/' + id);
        }

        factory.getTenderItemsByTiTender = function (id) {
            return $http.get('/getTenderItemsByTiTender/' + id, {"id":id});
        }

        factory.removeTenderItem = function (tenderItem) {
            return $http.delete('/removeTenderItem/' + tenderItem.tiId, {"id":tenderItem.tiId});
        }

        factory.addTenderItem = function (tenderItem) {
            return $http.post('/addTenderItem', tenderItem);
        }

        factory.updateTenderItem = function (tenderItem) {
            return $http.post('/updateTenderItem', tenderItem);
        }

        return factory;
    });
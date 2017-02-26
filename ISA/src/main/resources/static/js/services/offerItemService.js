/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.OfferItemService', [])
    .factory('OfferItemService', function($http){
        var factory = {};

        factory.getOfferItems = function () {
            return $http.get('/getOfferItems');
        }

        factory.getOfferItem = function(id) {
            return $http.get('/getOfferItem/' + id);
        }

        factory.getOfferItemsByOffiOffer = function (id) {
            return $http.get('/getOfferItemsByOffiOffer/' + id, {"id":id});
        }

        factory.getOfferItemsByOffiTenderItem = function (id) {
            return $http.get('/getOfferItemsByOffiTenderItem/' + id, {"id":id});
        }

        factory.removeOfferItem = function (offerItem) {
            return $http.delete('/removeOfferItem/' + offerItem.offiId, {"id":offerItem.offiId});
        }

        factory.addOfferItem = function (offerItem) {
            return $http.post('/addOfferItem', offerItem);
        }

        factory.updateOfferItem = function (offerItem) {
            return $http.post('/updateOfferItem', offerItem);
        }

        return factory;
    });
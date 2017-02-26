/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.OfferService', [])
    .factory('OfferService', function($http){
        var factory = {};

        factory.getOffers = function () {
            return $http.get('/getOffers');
        }

        factory.getOffer = function(id) {
            return $http.get('/getOffer/' + id);
        }

        factory.getOffersByOffTender = function (id) {
            return $http.get('/getOffersByOffTender/' + id, {"id":id});
        }

        factory.getOffersByOffTenderAndOffStatus = function (id, status) {
            return $http.get('/getOffersByOffTenderAndOffStatus/' + id + '/' + status, {"id":id, "status":status});
        }

        factory.getOffersByOffProvider = function (id) {
            return $http.get('/getOffersByOffProvider/' + id, {"id":id});
        }

        factory.getOffersByOffProviderAndOffStatus = function (id, status) {
            return $http.get('/getOffersByOffProviderAndOffStatus/' + id + '/' + status, {"id":id, "status":status});
        }

        factory.getOffersByOffTenderAndOffProvider = function (tid, pid) {
            return $http.get('/getOffersByOffTenderAndOffProvider/' + tid + '/' + pid, {"tid":tid, "pid":pid});
        }

        factory.removeOffer = function (offer) {
            return $http.delete('/removeOffer/' + offer.offId, {"id":offer.offId});
        }

        factory.addOffer = function (offer) {
            return $http.post('/addOffer', offer);
        }

        factory.updateOffer = function (offer) {
            return $http.post('/updateOffer', offer);
        }

        return factory;
    });
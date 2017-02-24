/**
 * Created by Marko on 12/21/2016.
 */
angular.module('restaurantApp.ProviderService', [])
    .factory('ProviderService', function($http){
        var factory = {};

        factory.getProviders = function () {
            return $http.get('/getProviders');
        }

        factory.removeProvider = function (provider) {
            return $http.delete('/removeProvider/' + provider.id, {"id":provider.id});
        }

        factory.addProvider = function (provider) {
            return $http.post('/addProvider', provider);
        }

        factory.updateProvider = function (provider) {
            return $http.post('/updateProvider', provider);
        }

        factory.searchProviders = function (searchedNameSurname, rid) {
            return $http.get('/searchProviders/'  + searchedNameSurname + '/' + rid, {"searchedNameSurname":searchedNameSurname, "rid":rid});
        }

        return factory;
    });
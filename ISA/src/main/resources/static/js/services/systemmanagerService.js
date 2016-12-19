/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.SystemmanagerService', [])
    .factory('SystemmanagerService', function($http){
        var factory = {};

        factory.getSystemManagers = function () {
            return $http.get('/getSystemManagers');
        }

        factory.removeSystemManager = function (systemManager) {
            return $http.delete('/removeSystemManager/' + systemManager.id, {"id":systemManager.id});
        }

        factory.addSystemManager = function (systemManager) {
            return $http.post('/addSystemManager', systemManager);
        }

        factory.updateSystemManager = function (systemManager) {
            return $http.post('/updateSystemManager', systemManager);
        }

        return factory;
    });
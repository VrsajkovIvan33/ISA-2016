/**
 * Created by Marko on 2/18/2017.
 */
angular.module('restaurantApp.MenuService', [])
    .factory('MenuService', function($http){
        var factory = {};

        factory.getMenus = function () {
            return $http.get('/getMenus');
        }

        factory.getMenu = function(id) {
            return $http.get('/getMenu/' + id);
        }

        factory.getMenusByMRestaurant = function (id) {
            return $http.get('/getMenusByMRestaurant/' + id, {"id":id});
        }

        factory.getMenusByMRestaurantAndMType = function (id, type) {
            return $http.get('/getMenusByMRestaurantAndMType/' + id + '/' + type, {"id":id, "type":type});
        }

        factory.removeMenu = function (menu) {
            return $http.delete('/removeMenu/' + menu.mId, {"id":menu.mId});
        }

        factory.addMenu = function (menu) {
            return $http.post('/addMenu', menu);
        }

        factory.updateMenu = function (menu) {
            return $http.post('/updateMenu', menu);
        }

        return factory;
    });
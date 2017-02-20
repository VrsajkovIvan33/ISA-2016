/**
 * Created by Marko on 2/19/2017.
 */
angular.module('restaurantApp.MenuReviewService', [])
    .factory('MenuReviewService', function($http){
        var factory = {};

        factory.getMenuReviews = function () {
            return $http.get('/getMenuReviews');
        }

        factory.getMenuReview = function(id) {
            return $http.get('/getMenuReview/' + id);
        }

        factory.getMenuReviewsByMrRestaurant = function (id) {
            return $http.get('/getMenuReviewsByMrRestaurant/' + id, {"id":id});
        }

        factory.getMenuReviewsByMrUser = function (id) {
            return $http.get('/getMenuReviewsByMrUser/' + id, {"id":id});
        }

        factory.getMenuReviewsByMrMenu = function (id) {
            return $http.get('/getMenuReviewsByMrMenu/' + id, {"id":id});
        }

        factory.removeMenuReview = function (menuReview) {
            return $http.delete('/removeMenuReview/' + menuReview.mrId, {"id":menuReview.mrId});
        }

        factory.addMenuReview = function (menuReview) {
            return $http.post('/addMenuReview', menuReview);
        }

        factory.updateMenuReview = function (menuReview) {
            return $http.post('/updateMenuReview', menuReview);
        }

        return factory;
    });
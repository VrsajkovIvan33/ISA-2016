/**
 * Created by Marko on 2/19/2017.
 */
angular.module('restaurantApp.RestaurantReviewService', [])
    .factory('RestaurantReviewService', function($http){
        var factory = {};

        factory.getRestaurantReviews = function () {
            return $http.get('/getRestaurantReviews');
        }

        factory.getRestaurantReview = function(id) {
            return $http.get('/getRestaurantReview/' + id);
        }

        factory.getRestaurantReviewsByRrRestaurant = function (id) {
            return $http.get('/getRestaurantReviewsByRrRestaurant/' + id, {"id":id});
        }

        factory.removeRestaurantReview = function (restaurantReview) {
            return $http.delete('/removeRestaurantReview/' + restaurantReview.rrId, {"id":restaurantReview.rrId});
        }

        factory.addRestaurantReview = function (restaurantReview) {
            return $http.post('/addRestaurantReview', restaurantReview);
        }

        factory.updateRestaurantReview = function (restaurantReview) {
            return $http.post('/updateRestaurantReview', restaurantReview);
        }

        return factory;
    });
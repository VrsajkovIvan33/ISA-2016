/**
 * Created by Marko on 2/19/2017.
 */
angular.module('restaurantApp.WaiterReviewService', [])
    .factory('WaiterReviewService', function($http){
        var factory = {};

        factory.getWaiterReviews = function () {
            return $http.get('/getWaiterReviews');
        }

        factory.getWaiterReview = function(id) {
            return $http.get('/getWaiterReview/' + id);
        }

        factory.getWaiterReviewsByWrRestaurant = function (id) {
            return $http.get('/getWaiterReviewsByWrRestaurant/' + id, {"id":id});
        }

        factory.getWaiterReviewsByWrWaiter = function (id) {
            return $http.get('/getWaiterReviewsByWrWaiter/' + id, {"id":id});
        }

        factory.removeWaiterReview = function (waiterReview) {
            return $http.delete('/removeWaiterReview/' + waiterReview.wrId, {"id":waiterReview.wrId});
        }

        factory.addWaiterReview = function (waiterReview) {
            return $http.post('/addWaiterReview', waiterReview);
        }

        factory.updateWaiterReview = function (waiterReview) {
            return $http.post('/updateWaiterReview', waiterReview);
        }

        return factory;
    });
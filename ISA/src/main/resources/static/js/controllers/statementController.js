/**
 * Created by Marko on 2/19/2017.
 */
angular.module('restaurantApp.StatementController',[])
    .controller('StatementController', function ($localStorage, $scope, $location, $uibModal, $rootScope, RestaurantReviewService) {

        $scope.loggedManager = $localStorage.logged;
        $scope.restaurantReviews = [];
        $scope.avgReview = 0;
        function getRestaurantReviews(){
            RestaurantReviewService.getRestaurantReviewsByRrRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.restaurantReviews = data;
                $scope.restaurantReviewsAll = data;

                for(var i = 0; i < $scope.restaurantReviews.length; i++) {
                    $scope.avgReview += $scope.restaurantReviews[i].rrReview;
                }

                if($scope.restaurantReviews.length != 0)
                    $scope.avgReview = $scope.avgReview / $scope.restaurantReviews.length;
            });
        }

        getRestaurantReviews();


        $scope.minDate = null;
        $scope.maxDate = null;

        $scope.changeDate = function() {
            $scope.avgReview = 0;
            var n = 0;

            for(var i = 0; i < $scope.restaurantReviews.length; i++) {
                if($scope.minDate != null && $scope.maxDate != null){
                    if($scope.restaurantReviews[i].rrDate > $scope.minDate && $scope.restaurantReviews[i].rrDate < $scope.maxDate){
                        $scope.avgReview += $scope.restaurantReviews[i].rrReview;
                        n = n + 1;
                    }
                }else if($scope.minDate != null){
                    if($scope.restaurantReviews[i].rrDate > $scope.minDate){
                        $scope.avgReview += $scope.restaurantReviews[i].rrReview;
                        n = n + 1;
                    }
                }else if($scope.maxDate != null){
                    if($scope.restaurantReviews[i].rrDate < $scope.maxDate){
                        $scope.avgReview += $scope.restaurantReviews[i].rrReview;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.restaurantReviews.length; i++) {
                        $scope.avgReview += $scope.restaurantReviews[i].rrReview;
                        n = n + 1;
                    }
                }
            }

            if(n != 0)
                $scope.avgReview = $scope.avgReview / n;
        }

    });
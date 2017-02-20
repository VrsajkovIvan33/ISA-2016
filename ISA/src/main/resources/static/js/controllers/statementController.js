/**
 * Created by Marko on 2/19/2017.
 */
angular.module('restaurantApp.StatementController',[])
    .controller('StatementController', function ($localStorage, $scope, $location, $uibModal, $rootScope, RestaurantReviewService, MenuReviewService, WaiterReviewService, MenuService, WaiterService, CookService) {

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



        $scope.waiters = [];
        $scope.waiter = null;
        function getWaitersByRestaurant(){
            WaiterService.getWaitersByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.waiters = data;
            });
        }
        getWaitersByRestaurant();

        $scope.menus = [];
        $scope.menu = null;
        function getMenusByMRestaurantAndMType(){
            MenuService.getMenusByMRestaurantAndMType($localStorage.logged.restaurant.id, "Menu").success(function (data) {
                $scope.menus = data;
            });
        }
        getMenusByMRestaurantAndMType();

        $scope.cooks = [];
        $scope.cook = null;
        function getCooksByRestaurant(){
            CookService.getCooksByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.cooks = data;
            });
        }
        getCooksByRestaurant();



        $scope.avgWaiterReview = 0;
        $scope.waiterReviews = [];
        $scope.changeWaiter = function() {
            WaiterReviewService.getWaiterReviewsByWrWaiter($scope.waiter.id).success(function (data) {
                $scope.avgWaiterReview = 0;
                $scope.waiterReviews = data;

                for(var i = 0; i < $scope.waiterReviews.length; i++) {
                    $scope.avgWaiterReview += $scope.waiterReviews[i].wrReview;
                }

                if($scope.waiterReviews.length != 0)
                    $scope.avgWaiterReview = $scope.avgWaiterReview / $scope.waiterReviews.length;

                $scope.minDateWaiter = null;
                $scope.maxDateWaiter = null;
            });
        }

        $scope.minDateWaiter = null;
        $scope.maxDateWaiter = null;

        $scope.changeDateWaiter = function() {
            $scope.avgWaiterReview = 0;
            var n = 0;

            for(var i = 0; i < $scope.waiterReviews.length; i++) {
                if($scope.minDateWaiter != null && $scope.maxDateWaiter != null){
                    if($scope.waiterReviews[i].wrDate > $scope.minDateWaiter && $scope.waiterReviews[i].wrDate < $scope.maxDateWaiter){
                        $scope.avgWaiterReview += $scope.waiterReviews[i].wrReview;
                        n = n + 1;
                    }
                }else if($scope.minDateWaiter != null){
                    if($scope.waiterReviews[i].wrDate > $scope.minDateWaiter){
                        $scope.avgWaiterReview += $scope.waiterReviews[i].wrReview;
                        n = n + 1;
                    }
                }else if($scope.maxDateWaiter != null){
                    if($scope.waiterReviews[i].wrDate < $scope.maxDateWaiter){
                        $scope.avgWaiterReview += $scope.waiterReviews[i].wrReview;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.waiterReviews.length; i++) {
                        $scope.avgWaiterReview += $scope.waiterReviews[i].wrReview;
                        n = n + 1;
                    }
                }
            }

            if(n != 0)
                $scope.avgWaiterReview = $scope.avgWaiterReview / n;
        }



        $scope.avgMenuReview = 0;
        $scope.menuReviews = [];
        $scope.changeMenu = function() {
            MenuReviewService.getMenuReviewsByMrMenu($scope.menu.mId).success(function (data) {
                $scope.avgMenuReview = 0;
                $scope.menuReviews = data;

                for(var i = 0; i < $scope.menuReviews.length; i++) {
                    $scope.avgMenuReview += $scope.menuReviews[i].mrReview;
                }

                if($scope.menuReviews.length != 0)
                    $scope.avgMenuReview = $scope.avgMenuReview / $scope.menuReviews.length;

                $scope.minDateMenu = null;
                $scope.maxDateMenu = null;
            });
        }

        $scope.minDateMenu = null;
        $scope.maxDateMenu = null;

        $scope.changeDateMenu = function() {
            $scope.avgMenuReview = 0;
            var n = 0;

            for(var i = 0; i < $scope.menuReviews.length; i++) {
                if($scope.minDateMenu != null && $scope.maxDateMenu != null){
                    if($scope.menuReviews[i].mrDate > $scope.minDateMenu && $scope.menuReviews[i].mrDate < $scope.maxDateMenu){
                        $scope.avgMenuReview += $scope.menuReviews[i].mrReview;
                        n = n + 1;
                    }
                }else if($scope.minDateMenu != null){
                    if($scope.menuReviews[i].mrDate > $scope.minDateMenu){
                        $scope.avgMenuReview += $scope.menuReviews[i].mrReview;
                        n = n + 1;
                    }
                }else if($scope.maxDateMenu != null){
                    if($scope.menuReviews[i].mrDate < $scope.maxDateMenu){
                        $scope.avgMenuReview += $scope.menuReviews[i].mrReview;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.menuReviews.length; i++) {
                        $scope.avgMenuReview += $scope.menuReviews[i].mrReview;
                        n = n + 1;
                    }
                }
            }

            if(n != 0)
                $scope.avgMenuReview = $scope.avgMenuReview / n;
        }



        $scope.avgCookReview = 0;
        $scope.cookReviews = [];
        $scope.changeCook = function() {
            MenuReviewService.getMenuReviewsByMrUser($scope.cook.id).success(function (data) {
                $scope.avgCookReview = 0;
                $scope.cookReviews = data;

                for(var i = 0; i < $scope.cookReviews.length; i++) {
                    $scope.avgCookReview += $scope.cookReviews[i].mrReview;
                }

                if($scope.cookReviews.length != 0)
                    $scope.avgCookReview = $scope.avgCookReview / $scope.cookReviews.length;

                $scope.minDateCook = null;
                $scope.maxDateCook = null;
            });
        }

        $scope.minDateCook = null;
        $scope.maxDateCook = null;

        $scope.changeDateCook = function() {
            $scope.avgCookReview = 0;
            var n = 0;

            for(var i = 0; i < $scope.cookReviews.length; i++) {
                if($scope.minDateCook != null && $scope.maxDateCook != null){
                    if($scope.cookReviews[i].mrDate > $scope.minDateCook && $scope.cookReviews[i].mrDate < $scope.maxDateCook){
                        $scope.avgCookReview += $scope.cookReviews[i].mrReview;
                        n = n + 1;
                    }
                }else if($scope.minDateCook != null){
                    if($scope.cookReviews[i].mrDate > $scope.minDateCook){
                        $scope.avgCookReview += $scope.cookReviews[i].mrReview;
                        n = n + 1;
                    }
                }else if($scope.maxDateCook != null){
                    if($scope.cookReviews[i].mrDate < $scope.maxDateCook){
                        $scope.avgCookReview += $scope.cookReviews[i].mrReview;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.cookReviews.length; i++) {
                        $scope.avgCookReview += $scope.cookReviews[i].mrReview;
                        n = n + 1;
                    }
                }
            }

            if(n != 0)
                $scope.avgCookReview = $scope.avgCookReview / n;
        }

    });
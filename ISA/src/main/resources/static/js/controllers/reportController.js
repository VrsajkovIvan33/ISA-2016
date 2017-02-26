/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.ReportController',[])
    .controller('ReportController', function ($localStorage, $scope, $location, $uibModal, $rootScope, BillService, WaiterService) {

        $scope.loggedManager = $localStorage.logged;

        $scope.restaurantBills = [];
        $scope.avgBills = 0;
        function getBills(){
            BillService.getBillsByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.restaurantBills = data;

                for(var i = 0; i < $scope.restaurantBills.length; i++) {
                    $scope.avgBills += $scope.restaurantBills[i].total;
                }
            });
        }
        getBills();

        $scope.minDate = null;
        $scope.maxDate = null;

        $scope.changeDate = function() {
            $scope.avgBills = 0;
            var n = 0;

            for(var i = 0; i < $scope.restaurantBills.length; i++) {
                if($scope.minDate != null && $scope.maxDate != null){
                    if($scope.restaurantBills[i].date > $scope.minDate && $scope.restaurantBills[i].date < $scope.maxDate){
                        $scope.avgBills += $scope.restaurantBills[i].total;
                        n = n + 1;
                    }
                }else if($scope.minDate != null){
                    if($scope.restaurantBills[i].date > $scope.minDate){
                        $scope.avgBills += $scope.restaurantBills[i].total;
                        n = n + 1;
                    }
                }else if($scope.maxDate != null){
                    if($scope.restaurantBills[i].date < $scope.maxDate){
                        $scope.avgBills += $scope.restaurantBills[i].total;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.restaurantBills.length; i++) {
                        $scope.avgBills += $scope.restaurantBills[i].total;
                        n = n + 1;
                    }
                }
            }
        }



        $scope.waiters = [];
        $scope.waiter = null;
        function getWaitersByRestaurant(){
            WaiterService.getWaitersByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.waiters = data;
            });
        }
        getWaitersByRestaurant();

        $scope.avgWaiterPayment = 0;
        $scope.waiterPayments = [];
        $scope.changeWaiter = function() {
            BillService.getBillsByWaiter($scope.waiter.id).success(function (data) {
                $scope.avgWaiterPayment = 0;
                $scope.waiterPayments = data;

                for(var i = 0; i < $scope.waiterPayments.length; i++) {
                    $scope.avgWaiterPayment += $scope.waiterPayments[i].total;
                }

                $scope.minDateWaiter = null;
                $scope.maxDateWaiter = null;
            });
        }

        $scope.minDateWaiter = null;
        $scope.maxDateWaiter = null;

        $scope.changeDateWaiter = function() {
            $scope.avgWaiterPayment = 0;
            var n = 0;

            for(var i = 0; i < $scope.waiterPayments.length; i++) {
                if($scope.minDateWaiter != null && $scope.maxDateWaiter != null){
                    if($scope.waiterPayments[i].date > $scope.minDateWaiter && $scope.waiterPayments[i].date < $scope.maxDateWaiter){
                        $scope.avgWaiterPayment += $scope.waiterPayments[i].total;
                        n = n + 1;
                    }
                }else if($scope.minDateWaiter != null){
                    if($scope.waiterPayments[i].date > $scope.minDateWaiter){
                        $scope.avgWaiterPayment += $scope.waiterPayments[i].total;
                        n = n + 1;
                    }
                }else if($scope.maxDateWaiter != null){
                    if($scope.waiterPayments[i].date < $scope.maxDateWaiter){
                        $scope.avgWaiterPayment += $scope.waiterPayments[i].total;
                        n = n + 1;
                    }
                }else{
                    for(var i = 0; i < $scope.waiterPayments.length; i++) {
                        $scope.avgWaiterPayment += $scope.waiterPayments[i].total;
                        n = n + 1;
                    }
                }
            }
        }
    });
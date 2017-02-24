/**
 * Created by Verpsychoff on 2/24/2017.
 */

angular.module('restaurantApp.GuestHistoryController', [])
    .controller('GuestHistoryController', function ($localStorage, $scope, $uibModal, $stomp, $log,  toastr, VisitHistoryFactory){

        function init() {

            $scope.loggedUser = $localStorage.logged;
            VisitHistoryFactory.getHistoriesByGuest($scope.loggedUser).success(function(data) {
                $scope.histories = data;
            })


        }

        init();

    });
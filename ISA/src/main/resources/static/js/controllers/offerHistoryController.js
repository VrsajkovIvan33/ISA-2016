/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.OfferHistoryController',[])
    .controller('OfferHistoryController', function ($localStorage, $scope, $location, $uibModal, $rootScope, OfferService, OfferItemService) {

        $scope.offerHistory = [];
        function getOfferHistory(){
            OfferService.getOffersByOffProvider($localStorage.logged.id).success(function (data) {
                $scope.offerHistory = data;
            });
        }

        getOfferHistory();

        $scope.zoomOfferHistory = function (offer) {
            $rootScope.zoomOffer = offer;
            $uibModal.open({
                templateUrl : 'html/provider/zoomOfferHistory.html',
                controller : 'ZoomOfferHistoryController',
                size: 'lg'
            })
        }
    })
    .controller('ZoomOfferHistoryController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, OfferService, OfferItemService) {

        $scope.offerItemList = [];
        function getOfferItems(){
            OfferItemService.getOfferItemsByOffiOffer($rootScope.zoomOffer.offId).success(function (data) {
                $scope.offerItemList = data;
            });
        }
        getOfferItems();

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });
/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.ProviderTendersController',[])
    .controller('ProviderTendersController', function ($localStorage, $scope, $location, $uibModal, $rootScope, TenderService, RestaurantService) {

        $scope.providerTenders = [];
        function getProviderTenders(){
            RestaurantService.getRestaurants().success(function (data) {
                var restaurants = data;
                var providerRestaurants = [];
                for (var i = 0; i < restaurants.length; i++){
                    for (var j = 0; j < restaurants[i].providers.length; j++){
                        if(restaurants[i].providers[j].id == $localStorage.logged.id){
                            providerRestaurants.push(restaurants[i]);
                            break;
                        }
                    }
                }

                for (var k = 0; k < providerRestaurants.length; k++) {
                    TenderService.getTendersByTRestaurantAndTStatus(providerRestaurants[k].id, 'Active').success(function (data) {
                        for(var i = 0; i < data.length; i++)
                            $scope.providerTenders.push(data[i]);
                    });
                }
            });
        }

        getProviderTenders();

        $scope.zoomTender = function (tender) {
            $rootScope.zoomTender = tender;
            $uibModal.open({
                templateUrl : 'html/tender/zoomTender.html',
                controller : 'ZoomTenderController'
            })
        }

        $scope.updateOffer = function (tender) {
            $rootScope.tenderForOffer = tender;
            $uibModal.open({
                templateUrl : 'html/provider/updateOffer.html',
                controller : 'UpdateOfferController',
                size: 'lg'
            })
        }
    })
    .controller('UpdateOfferController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, TenderService, TenderItemService, OfferService, OfferItemService) {

        $scope.tenderItems = [];
        $scope.offer = null;
        $scope.offerItems = [];
        function init(){
            TenderItemService.getTenderItemsByTiTender($rootScope.tenderForOffer.tId).success(function (data) {
                $scope.tenderItems = data;

                OfferService.getOffersByOffTenderAndOffProvider($rootScope.tenderForOffer.tId, $localStorage.logged.id).success(function (data) {
                    $scope.offer = data;

                    if($scope.offer == ''){
                        $scope.offer = {offId:null, offStatus:'On hold', offProvider:$localStorage.logged, offTender:$rootScope.tenderForOffer, version:0};

                        for(var i = 0; i < $scope.tenderItems.length; i++){
                            var item = {offiId:null, offiPrice:0, offiDeliveryTime:'', offiGuarantee:'', offiOffer:null, offiTenderItem:$scope.tenderItems[i], version:0};
                            $scope.offerItems.push(item);
                        }
                    }else{
                        OfferItemService.getOfferItemsByOffiOffer($scope.offer.offId).success(function (data) {
                            $scope.offerItems = data;
                        });
                    }
                });
            });
        }
        init();



        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.addOffer = function () {
            if($scope.offer.offId == null) {
                OfferService.addOffer($scope.offer).success(function (data) {
                    var addedOffer = data;
                    for (var i = 0; i < $scope.offerItems.length; i++) {
                        $scope.offerItems[i].offiOffer = addedOffer;
                        OfferItemService.addOfferItem($scope.offerItems[i]).success(function () {
                            $uibModalInstance.close();
                        });
                    }
                });
            }else{
                for (var i = 0; i < $scope.offerItems.length; i++) {
                    OfferItemService.updateOfferItem($scope.offerItems[i]).success(function () {
                        $uibModalInstance.close();
                    });
                }
            }
        }
    });
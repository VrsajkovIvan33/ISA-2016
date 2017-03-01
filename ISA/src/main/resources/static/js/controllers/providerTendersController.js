/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.ProviderTendersController',[])
    .controller('ProviderTendersController', function ($localStorage, $scope, $location, $uibModal,toastr, $rootScope,$stomp,$log, OfferService, TenderService, RestaurantService) {

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
            });
        }

        $scope.offerHistory = [];
        function getOfferHistory(){
            OfferService.getOffersByOffProvider($localStorage.logged.id).success(function (data) {
                $scope.offerHistory = data;
                for(i=0; i<$scope.offerHistory.length; i++){
                    if($scope.offerHistory[i].offStatus == 'On hold')
                        subscriptions[$scope.offerHistory[i].offTender.tId] = null;
                }
            });
        }

        getOfferHistory();

        $stomp.setDebug(function(args){
            $log.debug(args);
        });

        var subscriptions = {};

        $stomp.connect('/stomp', {})
            .then(function(frame){
                for(var i in subscriptions){
                    subscriptions[i] = $stomp.subscribe('/topic/offers/' + i, function(offerId, headers, res){
                        var accepted = false;
                        for(j =0; j < $scope.offerHistory.length; j++){
                            if($scope.offerHistory[j].offId == offerId)
                                accepted = true;

                        }
                        if(accepted)
                            toastr.success('Your offer accepted');
                        else
                            toastr.error('Your offer declined');
                        getOfferHistory();
                    });
                }
            });
        $scope.disconnect = function(){
            for(var i in subscriptions){
                subscriptions[i].unsubscribe();
            }
            $stomp.disconnect().then(function(){
                $log.info('disconnected');
            });
        };
    })
    .controller('UpdateOfferController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, TenderService, TenderItemService, OfferService, OfferItemService, toastr) {

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
            if(validate($scope.offerItems)) {
                if ($scope.offer.offId == null) {
                    OfferService.addOffer($scope.offer).success(function (data) {
                        var addedOffer = data;
                        for (var i = 0; i < $scope.offerItems.length; i++) {
                            $scope.offerItems[i].offiOffer = addedOffer;
                            OfferItemService.addOfferItem($scope.offerItems[i]).success(function () {

                            });
                        }
                        $uibModalInstance.close();
                    }).error(function(){
                        toastr.error('Tender has been closed or canceled');
                        $uibModalInstance.close();
                    });
                } else {
                    for (var i = 0; i < $scope.offerItems.length; i++) {
                        OfferItemService.updateOfferItem($scope.offerItems[i]).success(function () {

                        }).error(function () {
                            if(i == $scope.offerItems.length) {
                                toastr.error('Tender has been closed or canceled');
                                i = 0;
                            }
                        });
                    }
                    $uibModalInstance.close();
                }
            }
        }


        function validate(offerItems) {
            for(var i = 0; i < offerItems.length; i++) {
                if(offerItems[i].offiPrice == undefined || offerItems[i].offiPrice == 0){
                    alert('Price must be above 0 and below 10000000');
                    return false;
                }

                if (offerItems[i].offiDeliveryTime == '') {
                    alert('There is empty field');
                    return false;
                }
            }

            return true;
        }
    });
/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.RestaurantOffersController',[])
    .controller('RestaurantOffersController', function ($localStorage, $scope, $location, $uibModal, $rootScope, $log, $stomp, OfferService, TenderService) {

        $scope.restaurantOffers = [];
        $scope.activeTender = null;
        function getRestaurantOffers(){
            TenderService.getTendersByTRestaurantAndTStatus($localStorage.logged.restaurant.id, 'Active').success(function (data) {
               if(data.length != 0) {
                   $scope.activeTender = data[0];
                   OfferService.getOffersByOffTenderAndOffStatus($scope.activeTender.tId, 'On hold').success(function (data) {
                       $scope.restaurantOffers = data;
                   });
               }
            });
        }

        getRestaurantOffers();

        $stomp.setDebug(function(args){
            $log.debug(args);
        });

        $stomp.connect('/stomp', {})
              .then(function(frame){

              });

        $scope.zoomRestaurantOffer = function (offer) {
            $rootScope.zoomROffer = offer;
            $uibModal.open({
                templateUrl : 'html/restaurantManager/zoomRestaurantOffer.html',
                controller : 'ZoomRestaurantOfferController',
                size: 'lg'
            })
        }

        $scope.acceptOffer = function (offer) {
            var offerToChange = null;
            for(var i = 0; i < $scope.restaurantOffers.length; i++){
                if($scope.restaurantOffers[i].offId == offer.offId){
                    offerToChange = $scope.restaurantOffers[i];
                    $scope.restaurantOffers[i].offStatus = 'Accepted';
                }else{
                    $scope.restaurantOffers[i].offStatus = 'Rejected';
                }
                OfferService.updateOffer($scope.restaurantOffers[i]).success(function (data) {

                });
            }
            $stomp.send('/app/acceptOffer/'+offerToChange.offTender.tId, offerToChange);

            $scope.activeTender.tStatus = 'Closed';
            TenderService.updateTender($scope.activeTender).success(function (data) {
                $scope.restaurantOffers = [];
            });
        }
    })
    .controller('ZoomRestaurantOfferController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, OfferService, OfferItemService) {

        $scope.offerItemList = [];
        $scope.totalPrice = 0;
        function getOfferItems(){
            OfferItemService.getOfferItemsByOffiOffer($rootScope.zoomROffer.offId).success(function (data) {
                $scope.offerItemList = data;

                for(var i = 0; i < $scope.offerItemList.length; i++)
                    $scope.totalPrice += $scope.offerItemList[i].offiPrice;
            });
        }
        getOfferItems();

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });
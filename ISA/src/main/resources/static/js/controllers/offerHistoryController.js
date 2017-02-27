/**
 * Created by Marko on 2/25/2017.
 */
angular.module('restaurantApp.OfferHistoryController',[])
    .controller('OfferHistoryController', function ($localStorage, $scope, $location, $uibModal, $log, $rootScope, OfferService, $stomp, toastr, OfferItemService) {

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
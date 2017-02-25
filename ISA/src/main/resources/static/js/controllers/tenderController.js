/**
 * Created by Marko on 2/24/2017.
 */
angular.module('restaurantApp.TenderController',[])
    .controller('TenderController', function ($localStorage, $scope, $location, $uibModal, $rootScope, TenderService, OfferService) {

        $scope.tenders = [];
        $scope.active = false;
        function getTenders(){
            TenderService.getTendersByTRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.tenders = data;

                for (var i = 0; i < $scope.tenders.length; i++) {
                    if($scope.tenders[i].tStatus == 'Active'){
                        $scope.active = true;
                        break;
                    }
                }
            });
        }

        getTenders();


        $scope.removeTender = function (tender) {

            var offers = [];
            OfferService.getOffersByOffTender(tender.tId).success(function (data) {
                offers = data;
                for(var i = 0; i < offers.length; i++){
                    offers[i].offStatus = 'Canceled';
                    OfferService.updateOffer(offers[i]).success(function (data) {

                    });
                }

                tender.tStatus = "Canceled";
                TenderService.updateTender(tender).success(function (data) {
                    $scope.active = false;
                });
            });
        }

        $scope.openAddModal = function () {
            if($scope.active == false) {
                $uibModal.open({
                    templateUrl: 'html/tender/addNewTender.html',
                    controller: 'NewTenderController'
                }).result.then(function () {
                    getTenders();
                });
            }else{
                alert('There is already active tender');
            }
        }

        $scope.openUpdateModal = function (tender) {
            $rootScope.updateTender = tender;
            $uibModal.open({
                templateUrl : 'html/tender/updateTender.html',
                controller : 'UpdateTenderController'
            }).result.then(function(){
                getTenders();
            });
        }

        $scope.zoomTender = function (tender) {
            $rootScope.zoomTender = tender;
            $uibModal.open({
                templateUrl : 'html/tender/zoomTender.html',
                controller : 'ZoomTenderController'
            })
        }
    })
    .controller('NewTenderController', function ($localStorage, $scope, $location, $uibModalInstance, TenderService, TenderItemService) {

        $scope.newTender = {tId:null, tStart:null, tEnd:null , tStatus:'Active', tRestaurant:$localStorage.logged.restaurant, version:0};
        $scope.addTender = function (tender) {
            if(tender.tStart != null && tender.tEnd != null) {
                if(tender.tStart < tender.tEnd) {
                    if($scope.tenderItems.length != 0) {
                        TenderService.addTender(tender).success(function (data) {
                            var addedTender = data;
                            for (var i = 0; i < $scope.tenderItems.length; i++) {
                                $scope.tenderItems[i].tiTender = addedTender;
                                TenderItemService.addTenderItem($scope.tenderItems[i]).success(function () {

                                });
                            }
                            $scope.newTender = {
                                tId: null,
                                tStart: null,
                                tEnd: null,
                                tStatus: 'Active',
                                tRestaurant: $localStorage.logged.restaurant,
                                version: 0
                            };
                            $scope.tenderItems = [];
                            $uibModalInstance.close();
                        });
                    }else{
                        alert('There must be at least one tender item');
                    }
                }else{
                    alert('Start date must be one or more days before end date');
                }
            }else{
                alert('Please enter right dates');
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }


        $scope.tenderItems = [];
        $scope.addTenderItem = function () {
            var item = {tiName:'', tiType:'', tiQuantity:'', tiTender:null, version:0};
            $scope.tenderItems.push(item);
        }

        $scope.removeTenderItem = function (tenderItem) {
            var index = $scope.tenderItems.indexOf(tenderItem);
            if(index > -1)
                $scope.tenderItems.splice(index, 1);
        }


        $scope.tenderItemTypes = ['Foodstuff', 'Drink'];
    })
    .controller('UpdateTenderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, TenderService) {

        $scope.tenderToUpdate = jQuery.extend(true, {}, $rootScope.updateTender);

        $scope.updateTender = function (tender) {
            TenderService.updateTender(tender).success(function (data) {
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    })
    .controller('ZoomTenderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, TenderService, TenderItemService) {

        $scope.tenderItemsList = [];
        function getTenderItems(){
            TenderItemService.getTenderItemsByTiTender($rootScope.zoomTender.tId).success(function (data) {
                $scope.tenderItemsList = data;
            });
        }
        getTenderItems();

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });
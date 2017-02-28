/**
 * Created by Marko on 2/23/2017.
 */
angular.module('restaurantApp.RestaurantProvidersController',[])
    .controller('RestaurantProvidersController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService, RestaurantService, OfferService, TenderService) {
        if($localStorage.logged == null)
            $location.path("/");
        else {
            if ($localStorage.logged.type != 'RESTAURANTMANAGER')
                $location.path("/");
            else {
                $scope.loggedRestaurantManager = $localStorage.logged;
                $scope.restaurantProviders = $scope.loggedRestaurantManager.restaurant.providers;
            }
        }

        $scope.logOut = function(){
            $localStorage.logged = null;
            $location.path("/");
        };

        $scope.providers = [];
        function getProviders(){
            ProviderService.getProviders().success(function (data) {
                $scope.providers = data;
            });
        }

        getProviders();


        $scope.removeProvider = function(provider){
            var index = $localStorage.logged.restaurant.providers.indexOf(provider);
            if(index > -1)
                $localStorage.logged.restaurant.providers.splice(index, 1);

            RestaurantService.updateRestaurant($localStorage.logged.restaurant).success(function (data) {
                $localStorage.logged.restaurant = data;
                $scope.restaurantProviders = $scope.loggedRestaurantManager.restaurant.providers;

                TenderService.getTendersByTRestaurantAndTStatus($localStorage.logged.restaurant.id, 'Active').success(function (data) {
                    if(data.length != 0) {
                        var activeTender = data[0];
                        OfferService.getOffersByOffTenderAndOffProvider(activeTender.tId, provider.id).success(function (data) {
                            var providerOffer = data;
                            if(providerOffer != '') {
                                providerOffer.offStatus = 'Canceled';
                                OfferService.updateOffer(providerOffer).success(function (data) {

                                });
                            }
                        });
                    }
                });
            });
        }

        $scope.openSearchModal = function(){
            $uibModal.open({
                templateUrl : 'html/restaurantManager/searchProviders.html',
                controller : 'SearchProvidersController'
            }).result.then(function(){
                $scope.restaurantProviders = $scope.loggedRestaurantManager.restaurant.providers;
            });
        }
    })
    .controller('SearchProvidersController', function($localStorage, $scope, $stomp, $uibModalInstance, $log, toastr, ProviderService, RestaurantService){

        $scope.foundProviders = [];
        $scope.providerForSearch = '';

        $scope.search = function(providerForSearch){
            ProviderService.searchProviders(providerForSearch, $localStorage.logged.restaurant.id).success(function (data) {
                $scope.foundProviders = data;
            });
        };

        $scope.addProvider = function(provider){
            $localStorage.logged.restaurant.providers.push(provider);
            RestaurantService.updateRestaurant($localStorage.logged.restaurant).success(function (data) {
                $localStorage.logged.restaurant = data;
                $uibModalInstance.close();
            });
        };

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        };
    });
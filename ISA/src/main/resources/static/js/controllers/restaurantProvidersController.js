/**
 * Created by Marko on 2/23/2017.
 */
angular.module('restaurantApp.RestaurantProvidersController',[])
    .controller('RestaurantProvidersController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService, RestaurantService) {

        $scope.providers = [];
        function getProviders(){
            ProviderService.getProviders().success(function (data) {
                $scope.providers = data;
            });
        }

        getProviders();

        $scope.loggedRestaurantManager = $localStorage.logged;
        $scope.restaurantProviders = $scope.loggedRestaurantManager.restaurant.providers;

        $scope.removeProvider = function(provider){
            var index = $localStorage.logged.restaurant.providers.indexOf(provider);
            if(index > -1)
                $localStorage.logged.restaurant.providers.splice(index, 1);

            RestaurantService.updateRestaurant($localStorage.logged.restaurant).success(function (data) {
                $localStorage.logged.restaurant = data;
                $scope.restaurantProviders = $scope.loggedRestaurantManager.restaurant.providers;
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
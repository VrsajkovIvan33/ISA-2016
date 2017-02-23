/**
 * Created by Marko on 2/23/2017.
 */
angular.module('restaurantApp.RestaurantProvidersController',[])
    .controller('RestaurantProvidersController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService) {

        $scope.providers = [];
        function getProviders(){
            ProviderService.getProviders().success(function (data) {
                $scope.providers = data;
            });
        }

        getProviders();
    });
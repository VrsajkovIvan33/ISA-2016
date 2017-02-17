/**
 * Created by Marko on 12/21/2016.
 */
angular.module('restaurantApp.ProviderController',[])
    .controller('ProviderController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService) {

        $scope.providers = [];
        function getProviders(){
            ProviderService.getProviders().success(function (data) {
                $scope.providers = data;
            });
        }

        $scope.removeProvider = function (provider) {
            var index = $scope.providers.indexOf(provider);
            ProviderService.removeProvider(provider).success(function (data) {
                if(index > -1)
                    $scope.providers.splice(index, 1);
            });
        }

        $scope.newProvider = {id:null, name:'', surname:'', email:'', password:'', type:'PROVIDER', version:0};
        $scope.addProvider = function (provider) {
            ProviderService.addProvider(provider).success(function (data) {
                $scope.newProvider = {id:null, name:'', surname:'', email:'', password:'', type:'PROVIDER', version:0};
                getProviders();
                window.location.reload();
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/provider/addNewProvider.html',
                controller : 'NewProviderController'
            });
        }

        $scope.openUpdateModal = function (provider) {
            $rootScope.updateProvider = provider;
            $uibModal.open({
                templateUrl : 'html/provider/updateProvider.html',
                controller : 'UpdateProviderController'
            });
        }

        getProviders();
    })
    .controller('NewProviderController', function ($localStorage, $scope, $location, $uibModalInstance, ProviderService) {

        $scope.newProvider = {id:null, name:'', surname:'', email:'', password:'', type:'PROVIDER', version:0};
        $scope.addProvider = function (provider) {
            ProviderService.addProvider(provider).success(function (data) {
                $scope.newProvider = {id:null, name:'', surname:'', email:'', password:'', type:'PROVIDER', version:0};
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    })
    .controller('UpdateProviderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, ProviderService) {

        $scope.providerToUpdate = jQuery.extend(true, {}, $rootScope.updateProvider);
        $scope.updateProvider = function (provider) {
            ProviderService.updateProvider(provider).success(function (data) {
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });
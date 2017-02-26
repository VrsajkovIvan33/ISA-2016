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

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/provider/addNewProvider.html',
                controller : 'NewProviderController'
            }).result.then(function(){
                getProviders();
            });
        }

        $scope.openUpdateModal = function (provider) {
            $rootScope.updateProvider = provider;
            $uibModal.open({
                templateUrl : 'html/provider/updateProvider.html',
                controller : 'UpdateProviderController'
            }).result.then(function(){
                getProviders();
            });
        }

        getProviders();
    })
    .controller('NewProviderController', function ($localStorage, $scope, $location, $uibModalInstance, ProviderService) {

        $scope.newProvider = {id:null, name:'', surname:'', email:'', password:'', type:'PROVIDER', pPasswordChanged:false, version:0};
        $scope.addProvider = function (provider) {
            if(validate(provider)) {
                ProviderService.addProvider(provider).success(function (data) {
                    $scope.newProvider = {
                        id: null,
                        name: '',
                        surname: '',
                        email: '',
                        password: '',
                        type: 'PROVIDER',
                        pPasswordChanged: false,
                        version: 0
                    };
                    $uibModalInstance.close();
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }


        $scope.repeatPassword = '';

        function validate(provider) {
            if(provider.name == '' || provider.surname == '' || provider.email == '' || provider.password == ''){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(provider.email)){
                alert('Wrong email address');
                return false;
            }

            if($scope.repeatPassword != provider.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateProviderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, ProviderService) {

        $scope.providerToUpdate = jQuery.extend(true, {}, $rootScope.updateProvider);
        $scope.updateProvider = function (provider) {
            if(validate(provider)) {
                ProviderService.updateProvider(provider).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }



        $scope.repeatPassword = $scope.providerToUpdate.password;

        function validate(provider) {
            if(provider.name == '' || provider.surname == '' || provider.email == '' || provider.password == ''){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(provider.email)){
                alert('Wrong email address');
                return false;
            }

            if($scope.repeatPassword != provider.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });
/**
 * Created by Marko on 2/24/2017.
 */
angular.module('restaurantApp.ProviderAccountController',[])
    .controller('ProviderAccountController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService) {

        $scope.currentProvider = $localStorage.logged;
        $scope.checkPasswordChanged = function () {
            if($localStorage.logged.pPasswordChanged == false){
                $uibModal.open({
                    templateUrl : 'html/provider/changeProviderPassword.html',
                    controller : 'ChangeProviderPasswordController',
                    backdrop: 'static',
                    keyboard: false
                }).result.then(function(data){
                    $localStorage.logged = data;
                    $scope.currentProvider = data;
                });
            }
        }
        $scope.checkPasswordChanged();
    })
    .controller('ChangeProviderPasswordController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, ProviderService) {

        $scope.init = function(){
            $scope.providerPasswordToChange = jQuery.extend(true, {}, $localStorage.logged);
            $scope.providerPasswordToChange.password = '';
            $scope.repeatPassword = '';
        }
        $scope.init();

        $scope.updateProviderPassword = function (provider) {
            if(validate(provider)) {
                $scope.providerPasswordToChange.pPasswordChanged = true;
                ProviderService.updateProvider(provider).success(function (data) {
                    $uibModalInstance.close(data);
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }



        function validate(provider) {
            if(provider.password == ''){
                alert('There is empty field');
                return false;
            }

            if($scope.repeatPassword != provider.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });

angular.module('restaurantApp.ProviderProfileController',[])
    .controller('ProviderProfileController', function ($localStorage, $scope, $location, $uibModal, $rootScope, ProviderService) {

        $scope.currentProvider = $localStorage.logged;

        $scope.openUpdateModal = function () {
            $uibModal.open({
                templateUrl : 'html/provider/updateProviderAccount.html',
                controller : 'UpdateProviderAccountController'
            }).result.then(function(data){
                $localStorage.logged = data;
                $scope.currentProvider = data;
            });
        }
    })
    .controller('UpdateProviderAccountController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, ProviderService) {

        $scope.providerAccountToUpdate = jQuery.extend(true, {}, $localStorage.logged);

        $scope.updateProviderAccount = function (provider) {
            if(validate(provider)) {
                ProviderService.updateProvider(provider).success(function (data) {
                    $uibModalInstance.close(data);
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }



        $scope.repeatPassword = $scope.providerAccountToUpdate.password;

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
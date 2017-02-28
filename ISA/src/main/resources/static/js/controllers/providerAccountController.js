/**
 * Created by Marko on 2/24/2017.
 */
angular.module('restaurantApp.ProviderAccountController',[])
    .controller('ProviderAccountController', function ($localStorage, $scope, $location, $uibModal, $stomp,toastr, OfferService,$log,$rootScope, ProviderService) {

        if($localStorage.logged == null)
            $location.path("/");
        else {
            if ($localStorage.logged.type != 'PROVIDER')
                $location.path("/");
        }

        $scope.logOut = function(){
            $scope.disconnect();
            $localStorage.logged = null;
            $location.path("/");
        };

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
    .controller('ProviderProfileController', function ($localStorage, $scope, $location, $uibModal,$stomp, OfferService,$log,toastr, $rootScope, ProviderService) {

        if($localStorage.logged == null)
            $location.path("/");
        else {
            if ($localStorage.logged.type != 'PROVIDER')
                $location.path("/");
            else {
                $scope.currentProvider = $localStorage.logged;
            }
        }

        $scope.logOut = function(){
            $scope.disconnect();
            $localStorage.logged = null;
            $location.path("/");
        };

        $scope.openUpdateModal = function () {
            $uibModal.open({
                templateUrl : 'html/provider/updateProviderAccount.html',
                controller : 'UpdateProviderAccountController'
            }).result.then(function(data){
                $localStorage.logged = data;
                $scope.currentProvider = data;
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
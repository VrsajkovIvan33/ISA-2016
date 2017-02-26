/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.SystemmanagerController',[])
    .controller('SystemmanagerController', function ($localStorage, $scope, $location, $uibModal, $rootScope, SystemmanagerService) {

        $scope.systemManagers = [];
        function getSystemManagers(){
            SystemmanagerService.getSystemManagers().success(function (data) {
                $scope.systemManagers = data;
            });
        }

        $scope.removeSystemManager = function (systemManager) {
            var index = $scope.systemManagers.indexOf(systemManager);
            SystemmanagerService.removeSystemManager(systemManager).success(function (data) {
                if(index > -1)
                    $scope.systemManagers.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/systemManager/addNewSystemManager.html',
                controller : 'NewSystemmanagerController'
            }).result.then(function(){
                getSystemManagers();
            });
        }

        $scope.openUpdateModal = function (systemManager) {
            $rootScope.updateSystemManager = systemManager;
            $uibModal.open({
                templateUrl : 'html/systemManager/updateSystemManager.html',
                controller : 'UpdateSystemmanagerController'
            }).result.then(function(){
                getSystemManagers();
            });
        }

        getSystemManagers();

        $scope.checkSystemManagerDelete = function(sm){
            if(sm.email == "admin@gmail.com")
                return false;

            if(sm.id == $localStorage.logged.id)
                return false;

            return true;
        }

        $scope.checkSystemManagerUpdate = function(sm){
            if(sm.email == "admin@gmail.com")
                return false;

            return true;
        }
    })
    .controller('NewSystemmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, SystemmanagerService) {

        $scope.newSystemManager = {id:null, name:'', surname:'', email:'', password:'', type:'SYSTEMMANAGER', version:0};
        $scope.addSystemManager = function (systemManager) {
            if(validate(systemManager)) {
                SystemmanagerService.addSystemManager(systemManager).success(function (data) {
                    $scope.newSystemManager = {
                        id: null,
                        name: '',
                        surname: '',
                        email: '',
                        password: '',
                        type: 'SYSTEMMANAGER',
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

        function validate(systemManager) {
            if(systemManager.name == '' || systemManager.surname == '' || systemManager.email == '' || systemManager.password == ''){
                alert('There are empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(systemManager.email)){
                alert('Wrong email address');
                return false;
            }

            if($scope.repeatPassword != systemManager.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    })
    .controller('UpdateSystemmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, SystemmanagerService) {

        $scope.systemManagerToUpdate = jQuery.extend(true, {}, $rootScope.updateSystemManager);
        $scope.updateSystemManager = function (systemManager) {
            if(validate(systemManager)) {
                SystemmanagerService.updateSystemManager(systemManager).success(function (data) {
                    $uibModalInstance.close();
                });
            }
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }


        $scope.repeatPassword = $scope.systemManagerToUpdate.password;

        function validate(systemManager) {
            if(systemManager.name == '' || systemManager.surname == '' || systemManager.email == '' || systemManager.password == ''){
                alert('There are empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(systemManager.email)){
                alert('Wrong email address');
                return false;
            }

            if($scope.repeatPassword != systemManager.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });
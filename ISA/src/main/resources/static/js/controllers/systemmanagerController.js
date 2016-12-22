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
            });
        }

        $scope.openUpdateModal = function (systemManager) {
            $rootScope.updateSystemManager = systemManager;
            $uibModal.open({
                templateUrl : 'html/systemManager/updateSystemManager.html',
                controller : 'UpdateSystemmanagerController'
            });
        }

        getSystemManagers();

        $scope.checkSystemManager = function(sm){
            if(sm.email == "admin@gmail.com")
                return false;
            //fali za ulogovanog
            return true;
        }
    })
    .controller('NewSystemmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, SystemmanagerService) {

        $scope.newSystemManager = {id:null, name:'', surname:'', email:'', password:'', type:'SYSTEMMANAGER', version:0};
        $scope.addSystemManager = function (systemManager) {
            SystemmanagerService.addSystemManager(systemManager).success(function (data) {
                $scope.newSystemManager = {id:null, name:'', surname:'', email:'', password:'', type:'SYSTEMMANAGER', version:0};
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    })
    .controller('UpdateSystemmanagerController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, SystemmanagerService) {

        $scope.systemManagerToUpdate = $rootScope.updateSystemManager;
        $scope.updateSystemManager = function (systemManager) {
            SystemmanagerService.updateSystemManager(systemManager).success(function (data) {
                $uibModalInstance.close();
                window.location.reload();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }
    });
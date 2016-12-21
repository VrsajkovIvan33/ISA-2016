/**
 * Created by Marko on 12/18/2016.
 */
angular.module('restaurantApp.SystemmanagerController',[])
    .controller('SystemmanagerController', function ($localStorage, $scope, $location, SystemmanagerService) {

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

        $scope.newSystemManager = {id:null, name:'', surname:'', email:'', password:'', type:'SYSTEMMANAGER', version:1};
        $scope.addSystemManager = function (systemManager) {
            SystemmanagerService.addSystemManager(systemManager).success(function (data) {
                //$scope.systemManagers.push(data);
                //$location.path('/systemmanager/systemmanagers');
                $scope.newSystemManager = {id:null, name:'', surname:'', email:'', password:'', type:'SYSTEMMANAGER', version:1};
                getSystemManagers();
                window.location.reload();
            });
        }

        $scope.updateSystemManager = function (systemManager) {
            SystemmanagerService.updateSystemManager(systemManager).success(function (data) {
                getSystemManagers();
            });
        }

        getSystemManagers();

        $scope.checkSystemManager = function(sm){
            if(sm.email == "admin@gmail.com")
                return false;
            //fali za ulogovanog
            return true;
        }
    });
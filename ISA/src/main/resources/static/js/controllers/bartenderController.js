/**
 * Created by Marko on 2/17/2017.
 */
angular.module('restaurantApp.BartenderController',[])
    .controller('BartenderController', function ($localStorage, $scope, $location, $uibModal, $rootScope, BartenderService) {

        $scope.bartenders = [];
        function getBartendersByRestaurant(){
            BartenderService.getBartendersByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.bartenders = data;
            });
        }

        $scope.removeBartender = function (bartender) {
            var index = $scope.bartenders.indexOf(bartender);
            BartenderService.removeBartender(bartender).success(function (data) {
                if(index > -1)
                    $scope.bartenders.splice(index, 1);
            });
        }

        $scope.openAddModal = function () {
            $uibModal.open({
                templateUrl : 'html/bartender/addNewBartender.html',
                controller : 'NewBartenderController'
            }).result.then(function(){
                getBartendersByRestaurant();
            });
        }

        $scope.openUpdateModal = function (bartender) {
            $rootScope.updateBartender = bartender;
            $uibModal.open({
                templateUrl : 'html/bartender/updateBartender.html',
                controller : 'UpdateBartenderController'
            }).result.then(function(){
                getBartendersByRestaurant();
            });
        }

        getBartendersByRestaurant();
    })
    .controller('NewBartenderController', function ($localStorage, $scope, $location, $uibModalInstance, BartenderService, RestaurantService) {

        $scope.newBartender = {id:null, name:'', surname:'', email:'', password:'', type:'BARTENDER', date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, version:0};
        $scope.addBartender = function (bartender) {
            BartenderService.addBartender(bartender).success(function (data) {
                $scope.newBartender = {id:null, name:'', surname:'', email:'', password:'', type:'BARTENDER', date_of_birth:null, dress_size:0, shoe_size:0, restaurant:$localStorage.logged.restaurant, version:0};
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurants = [];
        function getRestaurants(){
            RestaurantService.getRestaurants().success(function (data) {
                $scope.restaurants = data;
            });
        }

        getRestaurants();
    })
    .controller('UpdateBartenderController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, BartenderService, RestaurantService) {

        function getBartender(){
            $scope.bartenderToUpdate = jQuery.extend(true, {}, $rootScope.updateBartender);
            $scope.bartenderToUpdate.date_of_birth = new Date($scope.bartenderToUpdate.date_of_birth);
        }
        getBartender();

        $scope.updateBartender = function (bartender) {
            BartenderService.updateBartender(bartender).success(function (data) {
                $uibModalInstance.close();
            });
        }

        $scope.close = function(){
            $uibModalInstance.dismiss('cancel');
        }

        $scope.restaurants = [];
        function getRestaurants(){
            RestaurantService.getRestaurants().success(function (data) {
                $scope.restaurants = data;
            });
        }

        getRestaurants();
    });
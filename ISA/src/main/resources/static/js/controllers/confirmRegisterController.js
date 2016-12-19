/**
 * Created by Nole on 12/18/2016.
 */

angular.module('restaurantApp.ConfirmRegisterController', [])
       .controller('ConfirmRegisterController', function($scope, $location, ConfirmFactory){
           function init(){

           }

           init();

           $scope.confirm = function(user){
                ConfirmFactory.confirmGuest(user)
                    .success(function(data){
                        if(data){
                            $location.path('/');
                        }
                    })
                    .error(function(data){
                        alert('Please enter valid username and password');
                    });
           };
       });
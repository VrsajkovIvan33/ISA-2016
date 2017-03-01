/**
 * Created by Verpsychoff on 11/24/2016.
 */

angular.module('restaurantApp.GuestRegisterController',[])
    .controller('GuestRegisterController', function ($scope, $location, GuestRegisterFactory) {
        function init() {
            console.log("Kao neko registrovanje");
        }

        init();

        $scope.registerGuest = function (guest) {
            if(validate(guest)) {
                GuestRegisterFactory.postGuest(guest).success(function (data) {
                    $location.path('/');
                });
            }
        }

        $scope.newGuest = {id:null, name:'', surname:'', email:'', password:'',  type:'GUEST', active:false, version:0};

        $scope.repeatedPassword = '';

        function validate(guest) {
            if(guest.name == '' || guest.surname == '' || guest.email == '' || guest.password == ''){
                alert('There is empty field');
                return false;
            }

            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if(!re.test(guest.email)){
                alert('Wrong email address');
                return false;
            }

            if($scope.repeatedPassword != guest.password){
                alert('Password does not match');
                return false;
            }

            return true;
        }
    });
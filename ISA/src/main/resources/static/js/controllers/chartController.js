/**
 * Created by Marko on 2/26/2017.
 */
angular.module('restaurantApp.ChartController',[])
    .controller('ChartController', function ($localStorage, $scope, $location, $uibModal, $rootScope, BillService) {

        if($localStorage.logged == null)
            $location.path("/");
        else {
            if ($localStorage.logged.type != 'RESTAURANTMANAGER')
                $location.path("/");
            else {
                $scope.loggedManager = $localStorage.logged;
            }
        }

        $scope.logOut = function(){
            $localStorage.logged = null;
            $location.path("/");
        };

        $scope.restaurantBills = [];
        $scope.visits = [];
        var labels = [];
        var numbers = [0, 0, 0, 0, 0, 0, 0, 0];
        function getBills(){
            BillService.getBillsByRestaurant($localStorage.logged.restaurant.id).success(function (data) {
                $scope.restaurantBills = data;

                var dates = [];
                var currentDate = new Date();
                currentDate.setHours(23,59,59,0);

                currentDate.setDate(currentDate.getDate() - 8);
                for(var j = 0; j < 8; j++){
                    currentDate.setDate(currentDate.getDate() + 1);

                    dates.push(new Date(currentDate.getTime()));

                    var dd = currentDate.getDate();
                    var mm = currentDate.getMonth()+1; //January is 0!
                    var yyyy = currentDate.getFullYear();


                    if(dd<10) {
                        dd='0'+dd
                    }

                    if(mm<10) {
                        mm='0'+mm
                    }

                    var date = mm+'/'+dd+'/'+yyyy;
                    labels.push(date);
                }

                for(var i = 0; i < $scope.restaurantBills.length; i++) {
                    var temp = new Date($scope.restaurantBills[i].date);

                    for(var j = 0; j < 7; j++){
                        if(dates[j] < temp && temp < dates[j + 1]){
                            numbers[j+1] += 1;
                        }
                    }
                }

                for(var i = 1; i < 8; i++){
                    var obj = {label: labels[i], value: numbers[i]};
                    $scope.myDataSource.data.push(obj)
                }
            });
        }
        getBills();

        $scope.myDataSource = {
            chart: {
                caption: "Restaurant Visits",
                theme: "fint"
            },
            data:[]
        };
    });
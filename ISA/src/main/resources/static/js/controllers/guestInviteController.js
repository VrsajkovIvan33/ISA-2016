/**
 * Created by Nole on 2/25/2017.
 */

angular.module('restaurantApp.InviteController', {})
       .controller('InviteController', function($scope, $location, toastr, InviteFactory){
              function init(){
                     var path = $location.path();
                     var res = path.split("/");
                     $scope.reservation = null;
                     $scope.guestId = Number(res[3]);
                     $scope.reservationId = Number(res[2]);
                     InviteFactory.getReservation($scope.reservationId).success(function(data){
                            $scope.reservation = data;
                            var date = new Date($scope.reservation.date);
                            $scope.day = date.getDate();
                            $scope.month = date.getMonth()+1;
                            $scope.year = date.getFullYear();
                     });
              }

              $scope.accept = function(){
                     InviteFactory.acceptInvitation($scope.guestId, $scope.reservationId).success(function(){
                            $location.path('/');
                     });
              }

              $scope.decline = function(){
                     InviteFactory.declineInvitation($scope.guestId, $scope.reservationId).success(function(){
                            $location.path('/');
                     });
              }

              init();
       });

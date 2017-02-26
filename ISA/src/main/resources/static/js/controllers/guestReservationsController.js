/**
 * Created by Nole on 2/26/2017.
 */

angular.module('restaurantApp.GuestReservationsController', [])
       .controller('GuestReservationsController', function($localStorage, $scope, $uibModal, $stomp, $log, toastr, GuestReservationsFactory){
            function init(){
                $scope.loggedUser = $localStorage.logged;
                $scope.friendRequestsNumber = 0;
                $scope.showRequests = false;
                $scope.reservations = [];
                $scope.dates = {};
                GuestReservationsFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function(data){
                    $scope.friendRequestsNumber = data;
                    if(data > 0)
                        $scope.showRequests = true;
                });

                GuestReservationsFactory.getReservations($localStorage.logged.id).success(function(data){
                    $scope.reservations = data;
                    for(i=0; i<$scope.reservations.length; i++){
                        var date = new Date($scope.reservations[i].date);
                        $scope.dates[$scope.reservations[i].id] = {
                            "date" : date.getDate(),
                            "month" : date.getMonth(),
                            "year" : date.getFullYear()
                        };
                    }
                });
            }

            var friendRequestSubscription = null;
            var acceptedFriendRequestSubscription = null;
            init();

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
               .then(function(frame){
                   friendRequestSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                       toastr.info('You have new friend request!');
                       $scope.friendRequestsNumber = numberOfRequests;
                       if(numberOfRequests > 0)
                           $scope.showRequests = true;
                   });

                   acceptedFriendRequestSubscription = $stomp.subscribe('/topic/friendAcceptedRequest/' + $localStorage.logged.id, function(friend, headers, res){
                       toastr.info(friend.name + ' ' + friend.surname + ' accepted friend request.');
                   });
               });

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };
       });

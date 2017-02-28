/**
 * Created by Nole on 12/21/2016.
 */

angular.module('restaurantApp.GuestProfileController', [])
       .controller('GuestProfileController', function ($localStorage, $scope, $uibModal,$location, $stomp, $log, toastr, GuestProfileFactory) {
          function init(){
              if($localStorage.logged == null)
                  $location.path("/");
              else {
                  if ($localStorage.logged.type != 'GUEST')
                      $location.path("/")
                  else {
                      $scope.loggedUser = $localStorage.logged;
                      $scope.friendRequestsNumber = 0;
                      $scope.showRequests = false;
                      GuestProfileFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function (data) {
                          $scope.friendRequestsNumber = data;
                          if (data > 0)
                              $scope.showRequests = true;
                      });
                  }
              }
          };

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

           $scope.logOut = function(){
               $scope.disconnect();
               $localStorage.logged = null;
               $location.path("/");
           };

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
               acceptedFriendRequestSubscription.unsubscribe();
               $stomp.disconnect().then(function(){
                   $log.info('disconnected');
               });
           };

           $scope.openUpdateModal = function () {
               $uibModal.open({
                   templateUrl : 'html/guest/updateGuestInfoModal.html',
                   controller : 'UpdateGuestProfileController',
               }).result.then(function(updatedUser){
                   $scope.loggedUser = updatedUser;
               });
           }

       })
       .controller('UpdateGuestProfileController', function ($localStorage, $scope, $uibModalInstance, $location, GuestProfileFactory) {
           function init(){
               $scope.userToUpdate = jQuery.extend(true, {}, $localStorage.logged);
           };

           init();

           $scope.update = function(user){
                  GuestProfileFactory.updateGuest(user).success(function(data){
                      if(data != null) {
                          $localStorage.logged = data;
                          $scope.userToUpdate = $localStorage.logged;
                          $uibModalInstance.close($localStorage.logged);
                      }else{
                          alert("It is not possible to change info");
                      }
                  });
           };

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           };
       });

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
       .controller('UpdateGuestProfileController', function ($localStorage, $scope, toastr, $uibModalInstance, $location, GuestProfileFactory) {
           function init(){
               $scope.userToUpdate = jQuery.extend(true, {}, $localStorage.logged);
           };

           init();

           $scope.update = function(user){
               if(validateUser(user)) {
                   GuestProfileFactory.updateGuest(user).success(function (data) {
                       if (data != null) {
                           $localStorage.logged = data;
                           $scope.userToUpdate = $localStorage.logged;
                           $uibModalInstance.close($localStorage.logged);
                       } else {
                           alert("It is not possible to change info");
                       }
                   });
               }
           };

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           };

           function validateUser(user){
               var checked = true;
               if(user.name == '') {
                   checked = false;
                   toastr.error('Name must not be empty');
               }
               if(user.surname == '') {
                   checked = false;
                   toastr.error('Surname must not be empty')
               }
               if(user.email == '') {
                   checked = false;
                   toastr.error('Email must not be empty')
               }
               var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
               if(!re.test(user.email)){
                   toastr.error('Wrong email address');
                   checked = false;
               }
               return checked;
           }
       });

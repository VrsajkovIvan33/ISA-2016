/**
 * Created by Nole on 12/21/2016.
 */

angular.module('restaurantApp.GuestProfileController', [])
       .controller('GuestProfileController', function ($localStorage, $scope, $uibModal, $stomp, $log, toastr, GuestProfileFactory) {
          function init(){
                $scope.loggedUser = $localStorage.logged;
          };

           var friendRequestSubscription = null;
           init();

           $stomp.setDebug(function(args){
               $log.debug(args);
           });

           $stomp.connect('/stomp', {})
               .then(function(frame){
                   friendRequestSubscription = $stomp.subscribe('/topic/friendRequest/' + $localStorage.logged.id, function(numberOfRequests, headers, res){
                       toastr.info('You have new friend request!');
                   });
               });

           $scope.disconnect = function(){
               friendRequestSubscription.unsubscribe();
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

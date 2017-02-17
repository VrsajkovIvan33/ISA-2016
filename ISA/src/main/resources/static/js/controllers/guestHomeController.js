/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestHomeController', [])
       .controller('GuestHomeController', function($localStorage, $scope, $uibModal, $stomp, $log, toastr, GuestHomeFactory){
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
       });

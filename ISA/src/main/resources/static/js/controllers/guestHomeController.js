/**
 * Created by Nole on 2/17/2017.
 */

angular.module('restaurantApp.GuestHomeController', [])
       .controller('GuestHomeController', function($localStorage, $scope, $uibModal, $stomp, $log, toastr, $rootScope, GuestHomeFactory, VisitHistoryFactory){
           function init(){
               $scope.loggedUser = $localStorage.logged;
               $scope.friendRequestsNumber = 0;
               $scope.showRequests = false;
               GuestHomeFactory.getFriendRequestsNumber($scope.loggedUser.id).success(function(data){
                   $scope.friendRequestsNumber = data;
                   if(data > 0)
                       $scope.showRequests = true;
               });

               VisitHistoryFactory.getHistoriesByGuest($scope.loggedUser).success(function(data) {
                   $scope.histories = data;
               })

           };

           var friendRequestSubscription = null;
           var acceptedFriendRequestSubscription = null;
           init();

           $scope.openGradesModal = function(history) {
               $rootScope.historyToShow = history;
               $uibModal.open({
                   templateUrl : 'html/guest/historyGradesModal.html',
                   controller : 'HistoryGradesController'
               }).result.then(function(){
                   VisitHistoryFactory.getHistoriesByGuest($scope.loggedUser).success(function(data) {
                       $scope.histories = data;
                   })
               });
           }

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
       })
       .controller('HistoryGradesController', function ($localStorage, $scope, $location, $uibModalInstance, $rootScope, VisitHistoryFactory) {

           $scope.historyToShow = $rootScope.historyToShow;
           $scope.grades = [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5];
           $scope.restaurantGrade = $scope.grades[4];
           $scope.menuGrade = $scope.grades[4];
           $scope.serviceGrade = $scope.grades[4];

           $scope.close = function(){
               $uibModalInstance.dismiss('cancel');
           }

           $scope.grade = function() {
               $scope.historyToShow.restaurantGrade = $scope.restaurantGrade;
               $scope.historyToShow.menuGrade = $scope.menuGrade;
               $scope.historyToShow.serviceGrade = $scope.serviceGrade;
               VisitHistoryFactory.updateHistory($scope.historyToShow).success(function(data) {
                   $uibModalInstance.close();
               })
           }
       });

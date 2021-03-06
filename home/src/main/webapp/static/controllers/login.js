angular.module("C4C")
	.controller("loginCtrl", function($scope, $http, $location, $window, $rootScope) {
		$rootScope.loggedIn = false;
			$scope.loginUser = function() {
				$http({
					method : "POST",
					url : "login",
					data : $scope.user
				}).then(function(response) {
					if(response.status === 200){
						$rootScope.loggedInUser = response;
						$rootScope.loggedIn = true;
						$window.location.reload();
						$location.path("/home");
					}
					if(response.status === 204) {
						window.alert("invalid login credentials, try again!");
						$window.location.reload();
					}
				});
			}
		});
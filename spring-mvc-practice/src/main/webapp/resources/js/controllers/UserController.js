userApp.controller("UserController",['$scope','$http',
    function($scope, $http) {
        $scope.user = {};
        $scope.addUser = function(user) {
            $http.post('add', user).success(function (data) {
                console.log("successfully" + data);
            });
        }
    }
]);
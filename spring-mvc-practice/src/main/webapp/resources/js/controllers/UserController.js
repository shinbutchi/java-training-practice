userApp.controller("UserController",['$scope','$http','$anchorScroll',
    function($scope, $http, $anchorScroll) {
        $scope.user = {};
        $scope.addUser = function(user) {
            $http.post('add', user).success(function (data) {
                console.log(data);
                setSuccess($scope, data);
                $scope.gotoMessage("messageContainer");
            });
        };

        $scope.gotoMessage = function (id) {
            //$location.hash(id);
            $anchorScroll();
        };
    }
]);
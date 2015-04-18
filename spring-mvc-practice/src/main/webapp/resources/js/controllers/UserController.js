userApp.controller("UserController",['$scope','$http','$anchorScroll','$sce',
    function($scope, $http, $anchorScroll, $sce) {
        $scope.user = {};
        $scope.addUser = function(user) {
            $http.post('add', user).success(function (data) {
                console.log(data);
                if(isErrorMessage(data)) {
                    setErrorMessage($scope, data);
                }
                else {
                    setSuccess($scope, data);
                    //$scope.gotoMessage("messageContainer");
                }
            });
        };

        $scope.gotoMessage = function () {
            $anchorScroll();
        };


    }


]);
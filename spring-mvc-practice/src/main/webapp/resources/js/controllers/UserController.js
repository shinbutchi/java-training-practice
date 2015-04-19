userApp.controller("UserController",['$scope','$http','$anchorScroll','$sce','$timeout',
    function($scope, $http, $anchorScroll, $sce, $timeout) {
        $scope.user = {};
        $scope.maxRecordPerPage = 5;
        $scope.totalRecord = 0;
        $scope.maxSize = 5;
        $scope.countries =
            [
                { id: 0, countryName: "Vietnam", cities: [
                    { id: 0, cityName:"Danang"},
                    { id: 1, cityName:"Hochiminh"},
                    { id: 2, cityName:"Cantho"}
                    ]
                },
                { id: 1, countryName: "Italy", cities: [
                    { id: 0, cityName:"Milan"},
                    { id: 1, cityName:"Roma"},
                    { id: 2, cityName:"Torin"},
                    { id: 2, cityName:"Bologna"}
                    ]
                }
            ];
        $scope.user.country = "0";
        $scope.getCities = function() {
            return $scope.countries[$scope.user.country].cities;
        };


        $scope.addUser = function(user) {
            $http.post('add', user).success(function (data) {
                console.log(data);
                if(isErrorMessage(data)) {
                    setFieldErrorMessage($scope, data);
                }
                else {
                    setSuccess($scope, data);
                    $timeout(function(){
                      $scope.redirectLoginPage()
                    },3000);
                }
            });
        };

        $scope.updateUser = function(user) {
            $http.post('update', user).success(function (data) {
                console.log(data);
                if(isErrorMessage(data)) {
                    setFieldErrorMessage($scope, data);
                }
                else {
                    if(!data.success){
                        setErrorMessage($scope, data)
                    }
                    else {
                        setSuccess($scope, data);
                    }
                }
            });
        };

        $scope.initData = function(user) {
            $scope.user = user;

        };

        $scope.gotoMessage = function () {
            $anchorScroll();
        };

        $scope.redirectLoginPage = function() {
            window.location.href="/login";
        };

        $scope.getData = function (page) {
            $http.get('list-user/' + page)
                .success(function (data) {
                    $scope.users = data.content;
                    $scope.totalRecord = data.totalElements;
                    $scope.totalPages = data.totalPages;
                    $scope.currentPage = data.number + 1;
                });
        };

        $scope.listUsers = function () {
            $scope.getData(1);
        };



    }


]);
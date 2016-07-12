function UserList($scope, $http) {
    $http.get('http://localhost:8080/user/list').
        success(function(data) {
            $scope.list = data;
            console.log($scope.list);
        });
}
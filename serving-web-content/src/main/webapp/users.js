function UserList($scope, $http) {
    $http.get('https://web-content.cfappssandbox.ebiz.verizon.com/user/list').
        success(function(data) {
            $scope.list = data;
            console.log($scope.list);
        });
}
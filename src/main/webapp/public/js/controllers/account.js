app.controller('AccountController', ['$scope', '$rootScope', 'GetUser', '$cookies',
    function ($scope, $rootScope, GetUsers, $cookies) {
        if ($cookies['hlebushek_auth']) {
            $scope.auth = true;
        } else {
            $scope.auth = false;
        }

        /*GetUsers.getById({
            'user_id': $scope.id
        }).then(function (response) {
            $scope.users = response.data;
        });*/
    }]);
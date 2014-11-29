app.controller('AccountController', ['$scope', '$rootScope', 'GetUser', '$cookies', '$cookieStore',
    function ($scope, $rootScope, GetUser, $cookies, $cookieStore) {
        if ($cookies['hlebushek_auth']) {
            $scope.auth = true;
            GetUser.getById({
                'user_id': $cookies['hlebushek_auth']
            }).then(function (response) {
                $scope.user = response.data['data'];
            });
        } else {
            $scope.auth = false;
        }

        // LogOut
        $scope.logOut = function() {
            $cookieStore.remove('hlebushek_auth');
        }
    }]);
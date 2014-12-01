app.controller('AccountController', ['$scope', '$window', 'GetUser', '$cookies',
    function ($scope, $window, GetUser, $cookies) {
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
            delete_cookie('hlebushek_auth');
            $window.location.reload();
        }
    }]);

function delete_cookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
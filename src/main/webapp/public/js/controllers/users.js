app.controller('UsersController', ['$scope', '$rootScope', 'GetUser', '$routeParams',
    function ($scope, $rootScope, GetUsers) {
        GetUsers.getById({
            'user_id': $scope.id
        }).then(function (response) {
            $scope.users = response.data;
        });
    }]);
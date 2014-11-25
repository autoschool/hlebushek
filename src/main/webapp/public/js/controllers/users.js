app.controller('UsersController', ['$scope', '$rootScope', 'GetUser', '$routeParams',
    function ($scope, $rootScope, GetUsers) {
        GetUsers.getById({
            'userId': $scope.id
        }).then(function (response) {
            $scope.users = response.data;
        });
    }]);
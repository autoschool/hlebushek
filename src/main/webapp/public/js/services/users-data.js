app.service('GetUser', ['$http', function ($http) {
    var url = 'service/GetUser';
    this.getById = function (id) {
        return $http.get(url, {
            params: id
        });
    };
}]);
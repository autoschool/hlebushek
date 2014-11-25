app.service('GetComments', ['$http', function ($http) {
    var url = 'service/GetComments';
    this.getById = function (id) {
        return $http.get(url, {
            params: id
        });
    };
}]);
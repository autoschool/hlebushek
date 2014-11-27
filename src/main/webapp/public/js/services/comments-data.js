app.service('GetComments', ['$http', function ($http) {
    var url = 'service/comments';
    this.getById = function (params) {
        return $http.get(url, {
            params: params
        });
    };
}]);
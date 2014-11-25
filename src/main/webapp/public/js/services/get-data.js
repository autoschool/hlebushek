app.service('GetPosts', ['$http', function ($http) {
    var url = 'service/GetPosts';
    this.call = function (params) {
        return $http.get(url, {
            params: params
        });
    };
}]);
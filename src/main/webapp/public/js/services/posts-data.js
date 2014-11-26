app.service('GetPosts', ['$http', function ($http) {
    var url = 'service/posts';
    this.call = function (params) {
        return $http.get(url, {
            params: params
        });
    };
}]);
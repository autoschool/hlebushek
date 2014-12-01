app.service('GitInfo', ['$http', function ($http) {
    var url = 'service/git_info';
    this.call = function () {
        return $http.get(url);
    };
}]);

app.factory('Posts', ['$http', '$rootScope', function ($http, $rootScope) {
    var posts = [];
    var service = {};

    function getPosts() {
        $http({
            method: 'GET',
            url: 'service/GetPosts'
        })
            .success(function (data) {
                posts = data;
                $rootScope.$broadcast('posts:updated');
            })
    }

    getPosts();

    service.getAll = function () {
        return posts;
    };

    service.getById = function (id) {
        var singlePost = null;
        angular.forEach(posts, function (value) {
            if (parseInt(value.post_id) === parseInt(id)) {
                singlePost = value;
                return false;
            }
        });
        return singlePost;
    };

    service.getByAuthor = function (id) {
        var postsByAuthor = [];
        angular.forEach(posts, function (value) {
            if (parseInt(value.author_id) === parseInt(id)) {
                postsByAuthor.push(value);
                return false;
            }
        });
        return postsByAuthor;
    };

    return service;
}]);
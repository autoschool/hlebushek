/**
 * Created by ksenie on 21.11.14.
 */

app.factory('Posts', ['$http', '$rootScope', function($http, $rootScope) {
    var posts = [];

    function getPosts() {
        $http({method: 'GET', url: 'service/data/posts'})
            .success(function(data, status, headers, config) {
                posts = data;
                $rootScope.$broadcast('posts:updated');
            })
            .error(function(data, status, headers, config) {
            });
    }

    getPosts();

    var service = {};

    service.getAll = function() {
        return posts;
    }


    service.get = function(id) {
        var post = null;
        angular.forEach(posts, function(value) {
            if (parseInt(value.post_id) === parseInt(id)) {
                post = value;
                return false;
            }
        });
        return post;
    }

    service.getByAuthor = function(id) {
        var postsByAuthor = [];
        angular.forEach(posts, function(value) {
            if (parseInt(value.author_id) === parseInt(id)) {
                postsByAuthor.push(value);
                return false;
            }
        });
        return postsByAuthor;
    }

    return service;
}]);
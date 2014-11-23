app.controller('SinglePostController', ['$scope', '$rootScope', 'Posts', '$routeParams',
    function ($scope, $rootScope, Posts, $routeParams) {
        if ($routeParams.postId !== undefined) {
            $scope.posts = Posts.getById($routeParams.postId);
        } else {
            $scope.posts = Posts.getAll();
        }

        $rootScope.$on('posts:updated', function () {
            $scope.posts = Posts.getAll();
        });

        $scope.show = function (post) {
            $rootScope.$broadcast('post:show', post);
        };
    }]);

app.controller('PostsController', ['$scope', '$rootScope', 'GetPosts', '$routeParams',
    function ($scope, $rootScope, GetPosts, $routeParams) {
        GetPosts.call({
            'authorId': $routeParams['userId'],
            'postId': $routeParams['postId']
        }).then(function (response) {
            $scope.posts = response.data;
        });
    }]);
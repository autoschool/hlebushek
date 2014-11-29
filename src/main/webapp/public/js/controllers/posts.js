app.controller('PostsController', ['$scope', 'GetPosts', 'GetUser', '$routeParams',
    function ($scope, GetPosts, GetUser, $routeParams) {
        GetPosts.call({
            'author_id': $routeParams['userId'],
            'post_id': $routeParams['postId']
        }).then(function (response) {
            var result = response.data;
            if (!result['is_error']) {
                var dataPosts = result['data'];
                if (dataPosts instanceof Array) {
                    $scope.posts = dataPosts;
                    if ($routeParams['userId']) {
                        GetUser.getById({
                            'user_id': $routeParams['userId']
                        }).then(function (response) {
                            $scope.user = response.data['data'];
                        });
                    }
                } else {
                    $scope.post = dataPosts;
                }
            }
        });
    }]);
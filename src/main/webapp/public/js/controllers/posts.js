app.controller('PostsController', ['$scope', 'GetPosts', '$routeParams',
    function ($scope, GetPosts, $routeParams) {
        GetPosts.call({
            'author_id': $routeParams['userId'],
            'post_id': $routeParams['postId']
        }).then(function (response) {
            var result = response.data;
            if (!result['is_error']) {
                var dataPosts = result['data'];
                if (dataPosts instanceof Array) {
                    $scope.posts = dataPosts;
                } else {
                    $scope.post = dataPosts;
                }
            } else {
                console.log("Error get posts data: " + result['error'])
            }
        });
    }]);
/**
 * Created by ksenie on 21.11.14.
 */

app.controller('PostListController', ['$scope', '$rootScope', 'Posts', '$routeParams',
    function($scope, $rootScope, Posts, $routeParams){

    if ($routeParams.userId !== undefined) {
        $scope.posts = Posts.getByAuthor($routeParams.userId);
    } else{
        $scope.posts=Posts.getAll();
    }

    $rootScope.$on('posts:updated', function() {
        $scope.posts = Posts.getAll();
    });

    $scope.show = function(post) {
        $rootScope.$broadcast('post:show', post);
    }
}]);
/**
 * Created by ksenie on 22.11.14.
 */

app.controller('SinglePostController', ['$scope', '$rootScope', 'Posts', '$routeParams',
    function($scope, $rootScope, Posts, $routeParams){

        if ($routeParams.postId !== undefined) {
            $scope.posts = Posts.get($routeParams.postId);
            console.log("here");
        } else{
            $scope.posts=Posts.getAll();
            console.log("here2");
        }

        $rootScope.$on('posts:updated', function() {
            $scope.posts = Posts.getAll();
        });

        $scope.show = function(post) {
            $rootScope.$broadcast('post:show', post);
        }
        console.log("SCOPE-PARAM:", $scope.posts);
    }]);

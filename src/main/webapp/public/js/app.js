var app = angular.module('blogWebApp', [
    'ngRoute'
])
    .constant('ROUTES', (function () {
        return {
            HOME: "/",
            ABOUT: "/about",
            ALL_POSTS: "/all_posts",
            ALL_POSTS_USER: "/all_posts/user:userId",
            ALL_POSTS_POST: "/user:userId/post:postId",
            ADD_POST: "/add_post",
            ERROR: "/404"
        }
    })())
    .config(['$routeProvider', 'ROUTES', function ($routeProvider, ROUTES) {
        $routeProvider.when(ROUTES.HOME, {
            templateUrl: "partials/auth.html",
            controller: "TestController"
        });
        $routeProvider.when(ROUTES.ABOUT, {
            templateUrl: "partials/about.html",
            controller: "TestController"
        });
        $routeProvider.when(ROUTES.ALL_POSTS, {
            templateUrl: "./partials/all-posts.html",
            controller: "PostsController"
        });
        $routeProvider.when(ROUTES.ALL_POSTS_USER, {
            templateUrl: "partials/author-blog.html",
            controller: "PostsController"
        });
        $routeProvider.when(ROUTES.ALL_POSTS_POST, {
            templateUrl: "partials/single-post.html",
            controller: "PostsController"
        });
        $routeProvider.when(ROUTES.ADD_POST, {
            templateUrl: "partials/add-post.html",
            controller: "TestController"
        });
        $routeProvider.when(ROUTES.ERROR, {
            templateUrl: "partials/404.html",
            controller: "TestController"
        });
        $routeProvider.otherwise({ redirectTo: ROUTES.ERROR });
    }])
    .controller('TestController', function () {
        console.log("TestController");
    })
    .run(['$rootScope', 'ROUTES', function ($rootScope, ROUTES) {
        $rootScope.ROUTES = ROUTES;
    }]);

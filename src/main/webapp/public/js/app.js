var app = angular.module('blogWebApp', [
    'ngRoute', 'ngCookies'
])
    .constant('ROUTES', (function () {
        return {
            HOME: "/",
            AUTH: "/auth",
            REG: "/reg",
            ABOUT: "/about",
            ALL_POSTS: "/all_posts",
            ALL_POSTS_USER: "/all_posts/user:userId",
            ALL_POSTS_POST: "/user:userId/post:postId",
            ACCOUNT: "/account",
            ERROR: "/404"
        }
    })())
    .config(['$routeProvider', 'ROUTES', function ($routeProvider, ROUTES) {
        $routeProvider.when(ROUTES.AUTH, {
            templateUrl: "partials/auth.html",
            controller: "MainController"
        });
        $routeProvider.when(ROUTES.REG, {
            templateUrl: "partials/reg.html",
            controller: "MainController"
        });
        $routeProvider.when(ROUTES.HOME, {
            templateUrl: "./partials/all-posts.html",
            controller: "PostsController"
        });
        $routeProvider.when(ROUTES.ABOUT, {
            templateUrl: "partials/about.html",
            controller: "MainController"
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
        $routeProvider.when(ROUTES.ACCOUNT, {
            templateUrl: "partials/account.html",
            controller: "AccountController"
        });
        $routeProvider.when(ROUTES.ERROR, {
            templateUrl: "partials/404.html",
            controller: "MainController"
        });
        $routeProvider.otherwise({ redirectTo: ROUTES.ERROR });
    }])
    .run(['$rootScope', 'GitInfo', 'ROUTES', function ($rootScope, GitInfo, ROUTES) {
        $rootScope.ROUTES = ROUTES;
        if (!$rootScope['git']) {
            GitInfo.call().then(function(response) {
                $rootScope['git'] = response.data;
            });
        }
    }]);

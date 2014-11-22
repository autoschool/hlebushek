var app = angular.module('blogWebApp', [
    'ngRoute'
]);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when("/", {templateUrl: "partials/home.html", controller: "TestController"})
        .when("/about", {templateUrl: "partials/about.html", controller: "TestController"})
        .when("/all_posts", {templateUrl: "partials/all_posts.html", controller: "PostListController"})
        .when("/all_posts/user:userId", {templateUrl: "partials/all_posts.html", controller: "PostListController"})
        .when("/add_post", {templateUrl: "partials/add_post.html", controller: "TestController"})
        .otherwise("/404", {templateUrl: "partials/404.html", controller: "TestController"});
}]);

app.controller('TestController', function () {
    console.log("Just a test controller");
});


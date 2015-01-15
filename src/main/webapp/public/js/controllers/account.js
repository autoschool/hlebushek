app.controller('AccountController', ['$scope', '$window', 'GetUser', '$cookies', '$http',
    function ($scope, $window, GetUser, $cookies, $http) {
        if ($cookies['hlebushek_auth']) {
            $scope.auth = true;
            GetUser.getById({
                'user_id': $cookies['hlebushek_auth']
            }).then(function (response) {
                $scope.user = response.data['data'];
            });
        } else {
            $scope.auth = false;
        }

        // LogOut
        $scope.logOut = function() {
            delete_cookie('hlebushek_auth');
            $window.location.reload();
        }

        // Comment submit
        $scope.submit=function(){

            $scope.CommentFormData.post_id = $scope.post.post_id;
            $scope.CommentFormData.author_id = $scope.user.user_id;
            $scope.dataLoaded = false;

            $http({
                method: 'POST',
                url: './service/comments',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: $scope.CommentFormData
            }).success(function(){
                $scope.CommentData = {};
                $scope.CommentData.first_name = $scope.user.first_name;
                $scope.CommentData.last_name = $scope.user.last_name;
                $scope.CommentData.message = $scope.CommentFormData.message;
                $scope.CommentData.create_date = new Date($.now());
                $scope.addComment($scope.CommentData);
                $scope.CommentFormData = {};
            }).error(function(reason){
                $scope.dataLoaded = true;
            })
        }
    }]);

function delete_cookie(name) {
    document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

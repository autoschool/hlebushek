app.controller('GetComments', ['$scope', 'GetComments', '$routeParams',
    function ($scope, GetComments, $routeParams) {
        GetComments.getById({
            'post_id': $routeParams.postId
        }).then(function (response) {
            var result = response.data;
            if (!result['is_error']) {
                var dataComments = result['data'];
                if (dataComments instanceof Array) {
                    $scope.comments = dataComments;
                } else {
                    $scope.comment = dataComments;
                }
            } else {
                console.log("Error get comments data: " + result['error'])
            }
        });
    }]);
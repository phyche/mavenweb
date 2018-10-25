
var app = angular.module('jumpApp', []);
app.controller('validateCtrl', function($scope) {
   $scope.email = "123@qq.com";

    //$scope.val1 = "1";
    //$scope.isString= angular.isString($scope.val1);
    //$scope.isNumber= angular.isNumber($scope.val1);

    $scope.changeCheck = function () {
        $scope.isString= angular.isString(fromJson($scope.val1));
        $scope.isNumber= angular.isNumber(fromJson($scope.val1));
        $scope.isArray= angular.isArray(fromJson($scope.val1));
        $scope.isDate= angular.isDate(new Date());
        $scope.isDefined= angular.isDefined($scope.val1);
        $scope.isElement= angular.isElement($scope.val1);
        $scope.isObject= angular.isObject($scope.val1);
        $scope.isUndefined= angular.isUndefined($scope.val1);
    };

    //转换为json格式
    function toJson(a) {
        return angular.toJson(a,true);
    }

    //将json格式数据反转为数组或者普通数据
    function fromJson(a) {
        return angular.fromJson(a,true);
    }

    $scope.goBack = function () {
        window.location.href = "jump-index.jsp";
    }
});





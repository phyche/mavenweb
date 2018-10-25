var app = angular.module("jumpApp",['ngRoute']);
app.config(['$routeProvider',function ($routeProvider) {
    $routeProvider
        .when('/',{template:"这是首页页面"})
        .when('/computer',{template:"这是电脑页面"})
        .when('/mobile',{template:"这是手机页面"})
        .when('/iPad',{template:"这是平板页面"});
}]);
app.controller("routeCtrl",function ($scope) {
    $scope.goBack = function () {
        window.location.href = "jump-index.jsp";
    };

    //$scope.function = "展示鼠标功能";
    $scope.mouseover = function () {
        $scope.function = "鼠标移到元素上方的功能";
    };
    $scope.mousedown = function () {
        $scope.function = "鼠标按下的功能";
    };
    $scope.mouseleave = function () {
        $scope.function = "鼠标离开元素的功能";
    };
});
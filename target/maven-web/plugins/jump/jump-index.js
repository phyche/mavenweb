
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName= "John";
    $scope.lastName= "Doe";

    $scope.jumpTo = function () {
        window.location.href = "jump.jsp";
    };
    $scope.jumpTo2 = function () {
        window.location.href = "jump2.jsp";
    };
    $scope.jumpTo3 = function () {
        window.location.href = "jump3.jsp";
    };
    $scope.jumpTo4 = function () {
        window.location.href = "jump4.jsp";
    };
    $scope.jumpTo5 = function () {
        window.location.href = "jump5.jsp";
    };
});





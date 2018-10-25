
var app = angular.module('jumpApp', ['ngAnimate']);
app.value("defaultInput",3);
app.factory('MathService', function() {
    var factory = {};

    factory.multiply = function(a, b) {
        return a * b;
    };
    return factory;
});

app.service("CalcService",function (MathService) {
    this.square = function (a) {
       return MathService.multiply(a,a);
    }
});
app.controller('animateCtrl', function($scope,CalcService,defaultInput) {

    $scope.goBack = function () {
        window.location.href = "jump-index.jsp";
    };

    $scope.number = defaultInput;
    $scope.result = CalcService.square($scope.number);
    $scope.square = function () {
        $scope.result = CalcService.square($scope.number);
    }
});





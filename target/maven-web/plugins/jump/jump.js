var app = angular.module('jumpApp', []);
app.controller('myCtrl', function($scope) {
    $scope.firstName= "John";
    $scope.lastName= "Doe";

    $scope.sayHello = function () {
        $scope.gretting = "Hello " + $scope.firstName + " " + $scope.lastName + "!";
    };

    $scope.names = ["May","Mary","Davy"];

    $scope.persons = [{firstName : "John",lastName : "Doe",country:"China"},
        {firstName : "Mary",lastName : "Doe",country:"Japan"},
        {firstName : "Davy",lastName : "Doe",country:"Korea"}];

    $scope.msg= "Davy Doe";

    $scope.goBack = function () {
        window.location.href = "jump-index.jsp";
    }

});

app.filter('reverse', function() { //可以注入依赖
    return function(text) {
        return text.split("").reverse().join("");
    }
});
angular.module('myApp', []).controller('userCtrl', function($scope) {
    $scope.id = '';
    $scope.fName = '';
    $scope.lName = '';
    $scope.passw1 = '';
    $scope.passw2 = '';
    $scope.users = [
        {id:1, fName:'Hege', lName:"Pege" ,passwoed:"1"},
        {id:2, fName:'Kim',  lName:"Pim" ,passwoed:"1"},
        {id:3, fName:'Sal',  lName:"Smith" ,passwoed:"1"},
        {id:4, fName:'Jack', lName:"Jones" ,passwoed:"1"},
        {id:5, fName:'John', lName:"Doe" ,passwoed:"1"},
        {id:6, fName:'Peter',lName:"Pan" ,passwoed:"1"}
    ];
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.lastId = $scope.users[$scope.users.length-1].id;

    $scope.editUser = function(id) {
        if (id == 'new') {
            $scope.edit = true;
            $scope.incomplete = true;
            $scope.fName = '';
            $scope.lName = '';
            $scope.id = '';
        } else {
            $scope.edit = false;
            $scope.fName = $scope.users[id-1].fName;
            $scope.lName = $scope.users[id-1].lName;
            $scope.id = $scope.users[id-1].id;
        }
    };

    $scope.$watch('passw1',function() {$scope.test();});
    $scope.$watch('passw2',function() {$scope.test();});
    $scope.$watch('fName', function() {$scope.test();});
    $scope.$watch('lName', function() {$scope.test();});

    $scope.test = function() {
        if ($scope.passw1 !== $scope.passw2) {
            $scope.error = true;
        } else {
            $scope.error = false;
        }
        $scope.incomplete = false;
        if ($scope.edit && (!$scope.fName.length ||
                !$scope.lName.length ||
                !$scope.passw1.length || !$scope.passw2.length)) {
            $scope.incomplete = true;
        }
    };

    $scope.save = function () {
        for (var i = 0;i<$scope.users.length;i++) {
            if ($scope.id == '') {
                $scope.users[$scope.users.length] = {id:$scope.lastId + 1, fName:$scope.fName, lName:$scope.lName ,passwoed:$scope.passw1};
            } else {
                if ($scope.id == $scope.users[i].id) {
                    $scope.users[i] = {id:$scope.id, fName:$scope.fName, lName:$scope.lName ,passwoed:$scope.passw1};
                }
            }
        }
    };

    $scope.goBack = function () {
        window.location.href = "jump-index.jsp";
    }
});
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <style>
        div {
            transition: all linear 0.5s;
            background-color: lightblue;
            height: 100px;
            width: 100%;
            position: relative;
            top: 0;
            left: 0;
        }

        .ng-hide {
            height: 0;
            width: 0;
            background-color: transparent;
            top:-200px;
            left: 200px;
        }

    </style>

    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular.js/1.4.6/angular-animate.min.js"></script>
</head>
<body >
    <form ng-app="jumpApp" ng-controller="animateCtrl">
        <h2>动画以及依赖注入功能</h2>
        <button ng-click='goBack()'>返回到首页</button><br/>

        <p>点我隐藏div：<input type="checkbox" ng-model = "checkValue"></p>
        <div ng-hide = "checkValue"></div>


        <p>输入一个数字：<input type="number" ng-model="number"></p>
        <button ng-click = "square()" >X<sup>2</sup></button>
        <p>结果：{{result}}</p>

    </form>
</body>
<script src="jump4.js?v=1.4"></script>

</html>

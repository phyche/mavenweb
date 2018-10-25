<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
    <script src="http://apps.bdimg.com/libs/angular-route/1.3.13/angular-route.js"></script>
</head>
<body >
    <form ng-app="jumpApp" ng-controller = "routeCtrl">
        <h2>路由以及鼠标功能</h2>
        <button ng-click='goBack()'>返回到首页</button><br/>

        <p>这是路由页面</p>
        <ul>
            <li><a href="#/">首页</a></li>
            <li><a href="#/computer">电脑</a></li>
            <li><a href="#/mobile">手机</a></li>
            <li><a href="#/iPad">平板</a></li>
        </ul>

        <p>展示路由页面<div ng-view></div></p>

        <p ng-mouseover = "mouseover()" ng-mousedown = "mousedown()" ng-mouseleave = "mouseleave()">显示鼠标功能</p>
        <h4>{{function}}</h4>
    </form>
</body>
<script src="jump5.js?v=1.1"></script>

</html>

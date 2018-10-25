<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body >
    <div ng-app="myApp" ng-controller="myCtrl">
        <%--展示数据--%>
        <p>姓名为 <span ng-bind="firstName"></span></p>
        <p>姓氏为 <span ng-bind="lastName"></span></p>

        <%--双向绑定并计算展示数据--%>
        <p>姓 : <input id="lastName" type="text" ng-model="lastName" ></p>
        <p>名 : <input id="firstName" type="text" ng-model="firstName" ></p>
        <h1>Hello {{firstName + " " + lastName}}</h1>

        <ul>
            <li style="height: 30px">
                <button ng-click='jumpTo()'>点击跳转到基础指令以及控制器页面</button>
            </li>
            <li style="height: 30px">
                <button ng-click='jumpTo2()'>点击跳转到输入验证以及api功能页面</button>
            </li>
            <li style="height: 30px">
                <button ng-click='jumpTo3()'>点击跳转到结合Bootstrap功能页面</button>
            </li>
            <li style="height: 30px">
                <button ng-click='jumpTo4()'>点击跳转到动画以及依赖注入功能页面</button>
            </li>
            <li style="height: 30px">
                <button ng-click='jumpTo5()'>点击跳转到路由以及鼠标功能页面</button>
            </li>
        </ul>

    </div>
</body>
<%--<script src="common.js"></script>--%>
<script src="/maven-web/plugins/jump/jump-index.js"></script>
</html>

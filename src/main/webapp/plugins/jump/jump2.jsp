<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body >
    <form ng-app="jumpApp" ng-controller="validateCtrl" name="myForm" novalidate>
        <h2>输入验证以及api功能</h2>
        <button ng-click='goBack()'>返回到首页</button><br/>
        <%--校验--%>
        <p>邮箱:<br>
            <input type="email" name="email" ng-model="email" required>
            <span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">
        <span ng-show="myForm.email.$error.required">邮箱是必须的。</span>
        <span ng-show="myForm.email.$error.email">非法的邮箱。</span>
        </span>
        </p>


        <input type="text" ng-model="val1" ng-change = "changeCheck()">
        <p>输入的数据为：{{val1}}</p>
        <p>是否是字符串：{{ isString }}</p>
        <p>是否是数字：{{ isNumber }}</p>
        <p>是否是数组：{{ isArray }}</p>
        <p>是否是日期：{{ isDate }}</p>
        <p>是否是定义过的元素：{{ isDefined }}</p>
        <p>是否是DOM元素：{{ isElement }}</p>
        <p>是否是对象：{{ isObject }}</p>
        <p>是否是未定义过的元素：{{ isUndefined }}</p>

    </form>
</body>
<script src="jump2.js?v=1.4"></script>


</html>

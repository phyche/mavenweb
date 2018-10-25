<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body >
    <div ng-app="jumpApp" ng-controller="myCtrl">

        <h2>基础指令以及控制器</h2>
        <button ng-click='goBack()'>返回到首页</button><br/>
        <%--scope方法获取最新数据,双向绑定展示--%>
        <button ng-click='sayHello()'>点我显示问候语</button>
        <p><input type="text" ng-model="gretting" ></p>
        <p>{{gretting}}</p>

        <%--ng-repeat数组循环显示--%>
        <p>{{lastName}} 家族成员:</p>
        <ul>
            <li ng-repeat="x in names">{{x}} {{lastName}}</li>
        </ul>
        <h1>{{lastName}} 家族成员:</h1>
        <ul>
            <li ng-repeat="x in persons">{{x.firstName + "  " + lastName + " " + x.country}}</li>
        </ul>

        <%--过滤输入test（保留test关键字的信息过滤其他的）并按照country排序，并对firstName大写转换--%>
        <p>输入需要过滤的关键字<input type="text" ng-model="test"></p>
        <p>过滤后的家庭成员，名字大写并按国家排序</p>
        <ul>
            <li ng-repeat="x in persons | filter:test | orderBy:'country'">
                {{ (x.firstName | uppercase) + ', ' + x.country }}
            </li>
        </ul>

        <%--自定义过滤 --%>
        <p>输入需要反转的内容:<input type="text" ng-model="msg"></p>
        <p>反转后的内容: {{ msg | reverse }}</p>

            <form>
                选中复选框，显示标题:
                <input type="checkbox" ng-model="checkVal">
            </form>
            <h1 ng-show="checkVal">My Header</h1>

            <%--ng-switch 指令根据单选按钮的选择结果显示或隐藏 HTML 区域。--%>
            <form>
                选择一个选项:
                <input type="radio" ng-model="myVar" value="dogs">Dogs
                <input type="radio" ng-model="myVar" value="tuts">Tutorials
                <input type="radio" ng-model="myVar" value="cars">Cars
            </form>

            <div ng-switch="myVar">
                <div ng-switch-when="dogs">
                    <h1>Dogs</h1>
                    <p>Welcome to a world of dogs.</p>
                </div>
                <div ng-switch-when="tuts">
                    <h1>Tutorials</h1>
                    <p>Learn from examples.</p>
                </div>
                <div ng-switch-when="cars">
                    <h1>Cars</h1>
                    <p>Read about cars.</p>
                </div>
            </div>
    </div>
</body>
<script src="jump.js?v=1.4"></script>


</html>

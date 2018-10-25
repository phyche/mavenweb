<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="/maven-web/resources/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" charset="utf-8" src="../resources/js/page.min.js"></script>
    <link href="../resources/css/highlight.min.css" rel="stylesheet">
    <link href="../resources/css/common.css" rel="stylesheet">
    <link href="../resources/css/page.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
    <link href="../resources/css/style.min.css" rel="stylesheet">
</head>
<style>
    td {
        vertical-align: middle;
        text-align: center;
        font-size: 16px;
    }
    th {
        font-weight:bold;
        vertical-align: middle;
        text-align: center;
        font-size: 16px;
    }
</style>
<body >

<div>
    <div style="margin-top: 15px">
        <label style="font-size: 16px">用户名：</label>
        <input style="width: 100px;height: 30px;font-size: 16px" id="username" name="username" type="text" placeholder="请输入关键字"/>
        <button onclick="search()" type="submit" style="width: 50px;height: 30px;background: #3f8ef1;font-size: 16px">查询</button>
    </div>
    <div style="margin-top: 10px">
        <table>
            <thead>
            <tr>
                <th width="20%">序号</th>
                <th width="20%">用户名</th>
                <th width="20%">密码</th>
                <th width="20%">头像</th>
                <th width="20%">操作</th>
            </tr>
            </thead>
            <tbody id="table">
            </tbody>
        </table>
    </div>
    <div style="margin-top: 10px;float: right" id="page">

    </div>
</div>



</body>
<%--<script src="common.js"></script>--%>
<script>

    var userList = "";
    $("#page").pagination({
        pageIndex: 0 ,
        pageSize: 5 ,
        total: 0,
        pageBtnCount: 11,
        showFirstLastBtn: true,
        alwaysBtnShow: true,
        showJump: true,
        showPageSizes: true,
        pageSizeItems: [5, 10, 15, 20],
        pageElementSort: ['$info', '$page', '$size', '$jump'],
        firstBtnText: "<<",
        lastBtnText: ">>",
        prevBtnText: "<",
        nextBtnText: ">",
        jumpBtnText: "GO",
        loadFirstPage: true,
        showInfo: true,
        infoFormat: " 共 {total} 条",
        noInfoText: "没有任何数据",
        remote: {
            type: 'post',
            url: "http://localhost:8083/maven-web/user/queryPage",
            pageParams: function(data){
                return {
                    pageNum:data.pageIndex+1,
                    pageSize:data.pageSize,
                    username:$("#username").val()
                };
            },
            success: function(data){
                userList = data.userList;
                addUserList(userList);
            },
            beforeSend: null,
            complete:  null ,
            pageIndexName: 'pageNum',
            pageSizeName: 'pageSize',
            totalName: 'page.rowCount',
            traditional: false
        },
        debug:false
    });

    function addUserList(data){
        $("#table").html('');
        for(var i=0 ; i<data.length ; i++){
            var userId = data[i].id;
            var username = data[i].username;
            var password = data[i].password;
            var pic = data[i].pic;

            var tableContent=new Array();
            tableContent.push('<tr><td>'+ (i+1) +'</td>');
            if (username != null) {
                tableContent.push('<td>' + username + '</td>');
            } else {
                tableContent.push('<td></td>');
            }

            if (password != null) {
                tableContent.push('<td>' + password + '</td>');
            } else {
                tableContent.push('<td></td>');
            }

            if (pic != null) {
                tableContent.push('<td><img style="width: 65px;height: 65px;" src = "' +"http://localhost:8083/maven-web"+ pic + '"><span/></td>');
            } else {
                tableContent.push('<td></td>');
            }

            tableContent.push('<td><a href="" style="font-size: 18px">查看</a></td>');
            $("#table").append(tableContent.join(""));
        }
    }

    function search() {
        $("#page").pagination("setParams",{"username":$("#username").val()});
        $("#page").pagination("remote");
    }
</script>

</html>

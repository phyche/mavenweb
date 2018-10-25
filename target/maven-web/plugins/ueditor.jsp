<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="/maven-web/resources/js/jquery-1.10.2.js"></script>
<%--<script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="../resources/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="../resources/ueditor/ueditor.all.js"></script>
    <link href="../resources/ueditor/themes/default/css/ueditor.css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="../resources/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="../resources/js/page.min.js"></script>
    <link href="../resources/css/page.css" rel="stylesheet">
    <link href="../resources/css/style.css" rel="stylesheet">
</head>
<body >

    富文本框：<textarea id="content" name="content" type="text/plain" style="width:93%;height:300px;"></textarea>

    <div id="page">

    </div>
</body>
<%--<script src="common.js"></script>--%>
<script>
    UE.Editor.prototype.placeholder = function (justPlainText) {
        var _editor = this;

        _editor.addListener("focus", function () {
            var localHtml = _editor.getPlainTxt();
            if ($.trim(localHtml) === $.trim(justPlainText)) {
                _editor.setContent(" ");
            }
        });

        _editor.addListener("blur", function () {
            var localHtml = _editor.getContent();
            if (!localHtml) {
                _editor.setContent("<p class='set-color'>" +justPlainText + "</p>");
            }
        });

        _editor.ready(function () {
            _editor.fireEvent("blur");
        });
    };

    var ue = UE.getEditor('content'/*,{
        toolbars: [],//隐藏所有的按钮
        autoHeightEnabled: true,
        autoFloatEnabled: true
    }*/);//富文本编辑器实例化
    ue.placeholder("请输入内容（限500字以内）");

    ue.ready(function() {
        UE.dom.domUtils.setStyles(ue.body, {
            'color': 'rgba(85, 85, 85, 1)',
            'font-family': "'Microsoft Yahei','Helvetica Neue', Helvetica, STHeiTi, Arial, sans-serif",
            'font-size': '14px'
        });
    });

    $("#page").pagination({
        pageIndex: 0 ,
        pageSize: 10 ,
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
            url: "http://localhost:8083/maven-web/user/page",
            pageParams: function(data){
                return {
                    pageNum:data.pageIndex+1,
                    pageSize:data.pageSize
                };
            },
            success: function(data){
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
</script>

</html>

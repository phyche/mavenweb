<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="/maven-web/resources/js/jquery-1.10.2.js"></script>
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
</head>
<body >
    <div id="resultForm" <%--method="post" action="" enctype="multipart/form-data"--%>>
        <span>姓名：</span>
        <input name="name" id="name"  type="text" placeholder="请输入"/>
        <span>密码：</span>
        <input name="password" id="password"  type="text" placeholder="请输入"/>
        <button onclick="save()">保存</button>
        <button onclick="test()">测试实体直接传参</button>
        <button onclick="testRedis()">测试redis</button>
    </div>

    <div class="row">
        <span>头像：</span>
        <input type="file" name="file" id="file" onchange="saveImg(this)">
        <span><img id="picImg" src="" width="120" height="120"/></span>
    </div>
    <div class="row">
        <span class="personattr">身份证正面照片:</span>
        <input type="hidden" name="img"  id="thumbUrl"/>
        <input type="file" name="logoFile" id="logoFile" onchange="setImg(this);">
        <span><img id="thumburlShow" src="" width="120" height="120"/></span>
    </div>

    <form action="http://localhost:8083/maven-web/fileUpload/filesUpload" method="POST" name="xiangmu" id="xiangmu"
          enctype="multipart/form-data">
        <table>
            <tr>
                <td>身份证图片上传：</td>
                <td><input type=file name="myfiles" id="doc"
                           onchange="showImage();">
                </td>
                <div id="localImag">
                    <img id="preview" width=-1 height=-1 style="diplay:none" />
                </div>
            </tr>
            <tr>
                <td>公司运营情况:</td>
                <!-- 为了实现张图片上传，上传框这个name要都一样 -->
                <td><input type="file" name="myfiles" id="doc1"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="提交" />
                </td>
            </tr>
        </table>
    </form>
<button onclick="jumpUeditor()">跳转百度富文本框</button>
<button onclick="jumpUserPage()">跳转用户列表</button>
</body>
<script src="common.js"></script>
<script >
    function jumpUeditor() {
        window.location.href = "ueditor.jsp";
    }

    function jumpUserPage() {
        window.location.href = "http://localhost:8083/maven-web/user/userManage";
    }

    function showImage() {
        var docObj = document.getElementById("doc");
        var imgObjPreview = document.getElementById("preview");
        if (docObj.files && docObj.files[0]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'blockOld';
            imgObjPreview.style.width = '300px';
            imgObjPreview.style.height = '120px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            //必须设置初始大小
            localImagId.style.width = "250px";
            localImagId.style.height = "200px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters
                    .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    }

    function saveImg(obj) {
        var data = new FormData();
        $.each($(obj)[0].files,function(i,file){
            data.append('picture', file);
        });
        data.append("type","advertisement");
        $.ajax( {
            url : 'http://localhost:8083/maven-web/fileUpload/image',
            data:data,
            type : 'POST',
            dataType : 'json',
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            success : function(suc) {
                alert("成功");
                var picImgUrl =  "http://localhost:8083/maven-web" + suc.imageGroup.mediumUrl;
                console.log("picImgUrl:" + picImgUrl);
                $("#thumbUrl").val(suc.imageGroup.sourceUrl);//将地址存储好
                $("#picImg").attr("src",picImgUrl);//显示图片
            },
            error:function(data) {
                alert("失败");
                console.log("error:"+data);
            }
        });
    }

    function test() {
        var user = {};
        user['username'] = $("#name").val();
        user['password'] = $("#password").val();
        user['pic'] = $("#thumbUrl").val();
        $.ajax( {
            url : 'http://localhost:8083/maven-web/user/test',
            data:JSON.stringify(user),
            type : 'POST',
            dataType : 'json',
            contentType: "application/json; charset=utf-8",
            success : function(data) {
                alert("成功");
            },
            error:function(data) {
                alert("失败");
                console.log("error:"+data);
            }
        });
    }

    function save() {
        //window.location.href = 'http://localhost:8083/maven-web/fileUpload/test';
        $.ajax( {
            url : 'http://localhost:8083/maven-web/user/save',
            data : {
                "userName": $("#name").val(),
                "password":$("#password").val(),
                "pic":$("#thumbUrl").val()
            },
            type : 'POST',
            dataType : 'json',
            success : function(data) {
                if(data.code==0){
                    alert("保存成功");
                }else{
                    alert("保存失败");
                    console.log("error:"+data);
                }
            },
            error:function(data) {
                alert("保存失败");
                console.log("error:"+data);
            }
        });
    }

    function setImg(obj){//用于进行图片上传，返回地址
        var f=$(obj).val();
        if(f == null || f ==undefined || f == ''){
            return false;
        }
        if(!/\.(?:png|jpg|bmp|gif|PNG|JPG|BMP|GIF)$/.test(f))
        {
            alert("类型必须是图片(.png|jpg|bmp|gif|PNG|JPG|BMP|GIF)");
            $(obj).val('');
            return false;
        }
        var data = new FormData();
        $.each($(obj)[0].files,function(i,file){
            data.append('file', file);
        });
        $.ajax({
            type: "POST",
            url: "http://localhost:8083/maven-web/fileUpload/uploadImg",
            data: data,
            cache: false,
            contentType: false,    //不可缺
            processData: false,    //不可缺
            dataType:"json",
            success: function(suc) {
                if(suc.code==0){
                    $("#thumbUrl").val(suc.message);//将地址存储好
                    $("#thumburlShow").attr("src",suc.message);//显示图片
                }else{
                    alert("上传失败");
                    $("#url").val("");
                    $(obj).val('');
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("上传失败，请检查网络后重试");
                $("#url").val("");
                $(obj).val('');
            }
        });
    }

    function testRedis() {
        $.ajax( {
            url : 'http://localhost:8083/maven-web/user/testRedis',
            data : {
                "userName": $("#name").val(),
                "password":$("#password").val(),
                "pic":$("#thumbUrl").val()
            },
            type : 'POST',
            dataType : 'json',
            success : function(data) {
                if(data.code==0){
                    alert("redis测试成功");
                }else{
                    alert("redis测试失败");
                    console.log("error:"+data);
                }
            },
            error:function(data) {
                alert("redis测试失败");
                console.log("error:"+data);
            }
        });
    }
</script>
</html>

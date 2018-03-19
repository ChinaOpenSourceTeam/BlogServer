<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>个人主页</title>
	<#include "../part/comment.ftl"/>
</head>
<body>

<div class="container-fluid">
    <div class="row">
		<#include "../part/head.ftl"/>
    </div>
    <div class="row">
		<div class="col-lg-2">
            <div class="list-group">
                <a href="#" class="list-group-item active"><span class="badge">14</span>我的信息</a>
                <a href="#" class="list-group-item">我的Blog</a>
                <a href="#" class="list-group-item">我的分类</a>
                <a href="#" class="list-group-item">我的收藏</a>
            </div>
		</div>
        <div class="col-lg-10">
            <table class="table table-striped table-hover">
                <tr>
					<td>登陆名</td>
                    <td>${user.loginname}</td>
				</tr>
                <tr>
                    <td>昵称</td>
                    <td>${user.nickname}</td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>${user.phone}</td>
                </tr>
                <tr>
                    <td>邮箱</td>
                    <td>${user.email}</td>
                </tr>
            </table>
		</div>

    </div>
    <div class="row">
		<#include "../part/footer.ftl"/>
    </div>

</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "${dynamicUrl}/user/info",
            success: function(data){
                //存储用户信息
                var result = JSON.parse(data);
                sessionStorage.setItem("name",result.data.name);
                $("#headRegister").hide();
                $("#headLogin").hide();
                $("#headBlog").show();
                $("#headLoginName").text(result.data.name);
            },
            error: function(data){
                alert("获取失败");
            }
        });
    });
</script>
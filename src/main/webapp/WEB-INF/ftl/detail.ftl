<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>${blog.title}</title>
	<#include "part/comment.ftl"/>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
		<#include "part/head.ftl"/>
		</div>
		<div class="row">
			<div class="col-md-1">
				<input id="blogUserId" type="hidden" value="${blog.createuser}">
				<div id="blogUser"></div>
			</div>
			<div class="col-md-10 col-lg-offset-1">
				${blog.content}
			</div>
		</div>
		<div class="row">
		<#include "part/footer.ftl"/>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        var userId = $("#blogUserId").val();
        $.ajax({
            type: "GET",
            url: "${staticUrl}/api/user/bloguser?userId="+userId,
            success: function(data){
                //存储用户信息
                var result = JSON.parse(data);
                if(result.code == 0){
                    $("#blogUser").html(result.data.name);
                }else {
                    alert(result.message);
                }
            },
            error: function(data){
                alert("获取失败");
            }
        });
    });

</script>
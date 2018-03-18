<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
	<title>公共笔记本</title>
    <#include "part/comment.ftl"/>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <#include "part/head.ftl"/>
        </div>
        <div class="row">
            <div>
                <h1>最新更新博客</h1>
            </div>
            <div id="newsblog"></div>
        </div>
        <div class="row">
            <#include "part/footer.ftl"/>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            type: "GET",
            url: "${staticUrl}/api/blog/newblog",
            success: function(data){
               //存储用户信息
               var result = JSON.parse(data);
               if(result.code == 0){
                   settingNewBlogList(result.data);
               }else {
                   alert(result.message);
               }

            },
            error: function(data){
                alert("获取失败");
            }
        });
    });

    //设置 最新更新blog
    function settingNewBlogList(data) {
        var html="";
        $.each(data,function (index,ele) {
            html += "<div><a href='${staticUrl}/"+ele.location+"'>"+ele.title+"</a></div>"
        });
        $("#newsblog").html(html);
    }

</script>
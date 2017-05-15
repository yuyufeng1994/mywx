<%--
  Created by IntelliJ IDEA.
  User: yuyufeng
  Date: 2017/5/15
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>yyf的微信调试页面</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="static/vendor/weui/weui.min.css">
    <script src="static/vendor/jquery.min.js"></script>

</head>

<body ontouchstart>
<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd">文字组合列表</div>
    <div class="weui-panel__bd" id="my-panel">
        <div class="weui-media-box weui-media-box_text">
            <h4 class="weui-media-box__title">标题0</h4>
            <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p>
        </div>

    </div>
    <div class="weui-panel__ft">
        <a href="javascript:myFun.getMore();" class="weui-cell weui-cell_access weui-cell_link">
            <div class="weui-cell__bd">查看更多</div>
            <span class="weui-cell__ft"></span>
        </a>
    </div>
</div>
</body>
<script>
    var moreIndex = 1;
    var myFun = {
        getMore: function () {
            console.log("getMore");
            $("#my-panel").append("<div class='weui-media-box weui-media-box_text'><h4 class='weui-media-box__title'>标题"+(moreIndex++)+"</h4><p class='weui-media-box__desc'>由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。</p> </div>")
        },
        test: function () {
            console.log("test");
        }
    }
    myFun.test();
</script>
</html>

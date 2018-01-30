<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <%@ include file="../common/_include_css.jsp"%>
</head>

<body class="front-body ">
<%@ include file="../common/_navbar.jsp" %>
    <div class="front-inner ">
        <div class="container ">
            <div class="row">
                <div class="col-lg-8">
                    <div class="panel panel-default front-panel">
                        <div class="panel-heading" style="font-size: 20px">添加</div>
                            <div class="panel-body front-last-no-margin">
                                <form class="form-horizontal" method="post" action="add" onsubmit="return preSubmit()">
                                    <div class="form-group">
                                        <label for="source" class="col-sm-2 control-label">数据源</label>
                                        <div class="col-sm-10">
                                            <input name="source" class="form-control" id="source">
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label for="target" class="col-sm-2 control-label">目标文件夹</label>
                                    <div class="col-sm-10">
                                        <input name="target" class="form-control" id="target">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="intervals" class="col-sm-2 control-label">同步时间间隔</label>
                                    <div class="col-sm-9">
                                        <input name="intervals" class="form-control" id="intervals">
                                    </div>
                                    <label for="intervals" class="col-sm-1  control-label" style="text-align: left">min</label>

                                </div>
                                <div class="form-group">
                                    <label for="userName" class="col-sm-2 control-label">用户名</label>
                                    <div class="col-sm-10">
                                        <input name="userName" class="form-control" id="userName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">密码</label>
                                    <div class="col-sm-10">
                                        <input type="password" name="password" class="form-control" id="password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <button type="submit" class="btn btn-default btn-primary">添加</button>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-2">
                                        <button onclick="restart()" type="button" class="btn btn-default btn-primary">重启</button>
                                    </div>
                                    <div class="col-sm-8">
                                        <span></span>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <c:forEach var="item" items="${infos}" varStatus="status">
                        source:${item.source}<a class="pull-right" href="delete?id=${item.id}">删除</a><br>

                    </c:forEach>
                </div>
        </div>
     </div>
</div>



<%@ include file="../common/_include_js.jsp"%>


<script type="text/javascript">

    function restart() {

        $.post("restart",
            {

            },
            function (data) {

                if (data["restart"]==="success")
                {
                    $.fillTipBox({type:'success', icon:'glyphicon-ok', content:'服务重启'});
                }else{
                    $.fillTipBox({type:'danger', icon:'glyphicon-alert', content:'重启失败'});
                }
            }

        );


    }

    function preSubmit() {
        var sourcePath = $('#source').val();
        var user = $("#userName").val();
        var passwd = $("#password").val();
        var interval = $("#intervals").val();
        var target = $("#target").val();
        if (sourcePath==="" || user==="" || passwd === "" || interval === "" || target === "") {
            $.fillTipBox({type: 'danger', icon: 'glyphicon-alert', content: '不能为空'});
            return false
        }
        else {
            return true
        }
    }

</script>

</body>



</html>
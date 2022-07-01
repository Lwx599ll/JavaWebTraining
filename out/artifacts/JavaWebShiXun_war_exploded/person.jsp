<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <%@ include file="header.jsp"%>
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="demoTable">
                用户名：
            <div class="layui-inline">
                <input class="layui-input" name="User" id="User" autocomplete="off"  placeholder="请输入想查询的用户名:">
            </div>
             <button class="layui-btn" data-type="reload" id="search">搜索</button>
        <button type="reset" class="layui-btn">重置</button>
    </div>

</script>

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'${path}/employee?method=selectByPage'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID', sort: true}
                ,{field:'name', title:'用户名'}
                ,{field:'age', title:'年龄'}
                ,{field:'gender', title:'性别'}
                ,{field:'basic', title:'基本工资'}
                ,{field:'jiangjin', title:'奖金'}
                ,{field:'baoxiao', title:'报销'}
                ,{field:'total', title:'总工资'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
            ]]
            ,page: true
            ,id:'reload'
        });
        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    // 弹出添加界面
                    layer.open({
                        type:2,
                        area:['700px','400px'],
                        // content: '${path}/user?method=getUserAddPage']
                        content: '${path}/user_add.jsp'
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });
    });
</script>

</body>
</html>
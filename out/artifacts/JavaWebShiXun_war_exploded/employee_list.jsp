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

	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	
	<script type="text/html" id="toolbarDemo">
  		<div class="layui-btn-container">
    		<button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
    		<button class="layui-btn layui-btn-sm" lay-event="deleteAll">批量删除</button>
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
		      ,{field:'deptName', title:'部门名称'}
		      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
		    ]]
		    ,page: true
		    ,id:'tableId'
		  });
		  
		  //头工具栏事件
		  table.on('toolbar(test)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id);
		    switch(obj.event){
		      case 'add':
		         // 弹出添加界面
		         layer.open({
		        	 type:2,
		        	 area:['700px','350px'],
		        	 content: '${path}/employee?method=getEmployeeAddPage'
			        	 // content: '${path}/employee_add.jsp'
		         });
		      break;
		      case 'deleteAll':
		        var data = checkStatus.data;
		        console.log(data);
		        // [{age: 43, gender: '男', id: 7, name: '', password: '23232'},{}]
		        var idArray = new Array();
		        for(var i = 0; i < data.length; i++) {
		        	idArray.push(data[i].id);
		        }
		        // [23, 3] -> '23,3'
		        var ids = idArray.join(',');
		        layer.confirm('您确认要删除么?', function() {
					$.post(
						'${path}/employee?method=deleteAll',
						{'ids': ids},
						function(jsonResult) {
							console.log(jsonResult);
							if(jsonResult.ok) {
								mylayer.okMsg(jsonResult.msg);
								// 删除完之后刷新表格，展示最新数据
								table.reload('tableId');
							} else {
								mylayer.errorMsg(jsonResult.msg);
							}
						},
						'json'
					);
				});
		        
		      break;
		      //自定义头工具栏右侧图标 - 提示
		      case 'LAYTABLE_TIPS':
		        layer.alert('这是工具栏右侧自定义的一个图标按钮');
		      break;
		    };
		  });
		  
		  //监听行工具事件
		  table.on('tool(test)', function(obj){
		    var data = obj.data;
		    console.log(data);
		    //console.log(obj)
		    if(obj.event === 'del'){
		    	layer.confirm('您确认要删除么?', function() {
					$.post(
						'${path}/employee?method=deleteById&id=' + data.id,
						function(jsonResult) {
							console.log(jsonResult);
							if(jsonResult.ok) {
								mylayer.okMsg(jsonResult.msg);
								// 删除完之后刷新表格，展示最新数据
								table.reload('tableId');
							} else {
								mylayer.errorMsg(jsonResult.msg);
							}
						},
						'json'
					);
				});
		    } else if(obj.event === 'edit'){
		    	layer.open({
		        	 type:2,
		        	 area:['700px','350px'],
		        	 content: '${path}/employee?method=getEmployeeUpdatePage&id=' +  data.id
		        	 // content: '${path}/employee_update.jsp'
		         });
		    }
		  });
		});
	</script>
	
	<%-- ${list} --%>
	<%-- <table class="table table-striped table-bordered table-condensed table-hover">
		<tr>
			<td>ID</td>
			<td>名字</td>
			<td>年龄</td>
			<td>性别</td>
			<td>密码</td>
			<td>删除</td>
			<td>编辑</td>
		</tr>
		<c:forEach items="${list}" var="employee">
			<tr>
				<td>${employee.id}</td>
				<td>${employee.name}</td>
				<td>${employee.age}</td>
				<td>${employee.gender}</td>
				<td>${employee.password}</td>
				<td><a href="javascript:deleteById(${employee.id})">删除</a></td>
				<td><a>编辑</a></td>
			</tr>
		</c:forEach>
		<!-- delete from employee where id=2; -->
	</table> --%>
</body>
</html>
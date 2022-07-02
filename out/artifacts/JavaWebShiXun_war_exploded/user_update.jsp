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
	<form  id="formId" class="layui-form layui-form-pane" action="">
		<input type="hidden" name="id" value="${user.id}">
	  <div class="layui-form-item">
	    <label class="layui-form-label">用户名</label>
	    <div class="layui-input-block">
	      <input type="text" name="name" value="${user.name}" autocomplete="off"  class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">密码</label>
	    <div class="layui-input-block">
	      <input type="text" name="password" value="${user.password}" lay-verify="required" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">年龄</label>
	    <div class="layui-input-block">
	      <input type="text" name="age" value="${user.age}" lay-verify="required" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">性别</label>
	    <div class="layui-input-block">
	      <c:if test='${user.gender=="男"}'>
	      	<input type="radio" name="gender" value="男" title="男" checked="">
	      </c:if>
	      <c:if test='${user.gender!="男"}'>
	      	<input type="radio" name="gender" value="男" title="男">
	      </c:if>
	      
	      <c:if test='${user.gender=="女"}'>
	      	<input type="radio" name="gender" value="女" title="女" checked="">
	      </c:if>
	      <c:if test='${user.gender!="女"}'>
	      	<input type="radio" name="gender" value="女" title="女">
	      </c:if>
	    </div>
	  </div>
		<div class="layui-form-item">
			<label class="layui-form-label">管理员类型</label>
			<div class="layui-input-block">
				<c:if test='${user.type==0}'>
					<input type="radio" name="type" value="0" title="管理员" checked="">
				</c:if>
				<c:if test='${user.type!=0}'>
					<input type="radio" name="type" value="0" title="管理员">
				</c:if>

				<c:if test='${user.type==1}'>
					<input type="radio" name="type" value="1" title="财务管理员" checked="">
				</c:if>
				<c:if test='${user.type!=1}'>
					<input type="radio" name="type" value="1" title="财务管理员">
				</c:if>
				<c:if test='${user.type==2}'>
					<input type="radio" name="type" value="2" title="普通员工" checked="">
				</c:if>
				<c:if test='${user.type!=2}'>
					<input type="radio" name="type" value="2" title="普通员工">
				</c:if>
			</div>
		</div>
	  <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button type="button" class="layui-btn" onclick="submitForm()">修改</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
  	</div>
	</form>
	<script>
		layui.use(['form'], function(){
		  var form = layui.form;
		});
		
		function submitForm() {
			$.post(
					'${path}/user?method=update',
					$('#formId').serialize(), // {'name':'zhangsan','age':23, 'gender':'男'}
				function(jsonResult) {
					if(jsonResult.ok) {
						layer.msg(
							jsonResult.msg,
							{icon:1, time:2000},
							function() {// 弹出消息2000毫秒后出发这个function
								// 获得当前弹出框的index
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                parent.location.reload();//刷新父级页面
							}
						);
					}
				},
				'json'
			);
		}
	</script>
</body>
</html>
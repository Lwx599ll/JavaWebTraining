<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <script type="text/javascript">
        let alert_msg = '${alert_msg}';
        if (alert_msg != null && alert_msg.trim() != '') {
            window.alert(alert_msg);
        }
    </script>
    <style>
        * {
            padding: 0;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            letter-spacing: .05em;
        }

        html {
            height: 100%;
        }

        body {
            height: 100%;
        }

        .container {
            height: 100%;
            /*background-image: linear-gradient(to right, #fbc2eb, #a6c1ee); 设置渐变色 */
            padding: 0;
            margin: 0;
            background: url("${path}/static/img/1.jpg") no-repeat;
            background-size: 100% 100%;
        }

        .login-wrapper {
            background-color: #fff;
            width: 300px;
            height: 500px;
            position: relative;
            padding: 0 50px;
            border-radius: 15px;
            /* 设置框的居中位置也可以采用flex布局方式 */
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .login-wrapper .header {
            font-size: 30px;
            font-weight: bold;
            text-align: center;
            line-height: 120px;
        }

        .login-wrapper .form-wrapper .input-item {
            display: block;
            width: 100%;
            margin-bottom: 20px;
            border: 0;
            padding: 10px;
            border-bottom: 1px solid rgb(128, 125, 125);
            font-size: 15px;
            outline: none;
        }

        .login-wrapper .form-wrapper .input-item ::placeholder {
            text-transform: uppercase;
        }

        .login-wrapper .form-wrapper .btn {
            text-align: center;
            padding: 10px;
            margin-top: 40px;
            width: 100%;
            background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
            color: #fff;
        }

        .login-wrapper .msg {
            text-align: center;
            line-height: 80px;
        }

        .login-wrapper .msg a {
            /* 消除下划线 */
            text-decoration: none;
            text-decoration-color: unset;
            color: #a6c1ee;
        }
    </style>
</head>

<body>
<div class="container">
    <div class="login-wrapper">
        <div class="header">Login</div>
        <div class="form-wrapper">
            <form id="formId" method="post" onsubmit="return check()">
                <input type="text" name="name" id="name" placeholder="登录账号"  class="input-item">
                <input type="password" name="password" id="password" placeholder="登录密码"  class="input-item">
                <input type="text" name="code" id="validationCode" placeholder="请输入验证码" class="input-item">
                <img id="img_validation_code" src="${path}/auth" onclick="refresh()" />
                <input class="btn" type="button" onclick="submitForm()" value="登录"/>
            </form>
        </div>
        <div class="msg">
            Don't have account?<a href='register.jsp' style="font-weight: bold;">Sign up</a>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function refresh() {
        let img = document.getElementById("img_validation_code")
        img.src = "${path}/auth?r=" + Math.random();
    }

    function submitForm() {
        $.post(
            '${path}/user?method=login',
            $('#formId').serialize(), // {'name':'zhansgan','age':23}
            function (jsonResult) {
                if (jsonResult.ok) {
                    // 弹出成功消息，跳转到首页
                    // localhost:8080/
                    mylayer.okUrl(jsonResult.msg, '${path}/index.jsp');
                } else {
                    mylayer.errorMsg(jsonResult.msg);
                }
            },
            'json'
        );
    }
</script>
</html>

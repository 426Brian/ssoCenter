<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"></meta>
    <title>www.sso.com</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>

    <style type="text/css">
        #lightbox { /*该层为高亮显示层*/
            BORDER-RIGHT: #fff 1px solid;
            BORDER-TOP: #fff 1px solid;
            DISPLAY: block;
            Z-INDEX: 9999; /*设置该层在网页的最上端,设置足够大*/
            BACKGROUND: #fdfce9; /*设置背景色*/
            LEFT: 50%;
            MARGIN: -120px 0px 0px -150px;
            BORDER-LEFT: #fff 1px solid;
            WIDTH: 300px;
            BORDER-BOTTOM: #fff 1px solid;
            POSITION: absolute;
            TOP: 50%;
            HEIGHT: 150px;
            TEXT-ALIGN: left
        }

        #overlay { /*该层为覆盖层*/
            DISPLAY: block;
            Z-INDEX: 9998; /*设置高亮层的下方*/
            FILTER: alpha(opacity=80); /*设置成透明*/
            LEFT: 0px;
            WIDTH: 100%;
            POSITION: absolute;
            margin: 0px 0px -300px 0px;
            TOP: 0px;
            HEIGHT: 100%;
            BACKGROUND-COLOR: #000;
            moz-opacity: 0.8;
            opacity: .80
        }
    </style>

</head>
<body>

<!--该层为覆盖层 -->
<div id="overlay"></div>
<!--/该层为覆盖层 -->
<!--该层为高亮显示层 -->
<div id="lightbox" name="lightbox">
    <!--/该层为高亮显示层 -->
    <form name="form1" method="post" action="">
       <input type="hidden" name="clientUrl" value="${clientUrl}"/>
        <table width="100%" height="22" border="0" cellpadding="0" cellspacing="0" style="font-size:12px;">
            <tr>
                <td align="center" bgcolor="#999999">用户登录</td>
            </tr>
        </table>
        <table width="243" height="102" border="0" align="center" cellpadding="0" cellspacing="0"
               style="font-size:12px;">
            <tr>
                <td width="70" valign="bottom">用户名：</td>
                <td width="173" valign="bottom"><label>
                    <input type="text" name="empno" value=""/>
                </label></td>
            </tr>
            <tr>
                <td valign="bottom">密码：</td>
                <td valign="bottom"><label>
                    <input type="text" name="password" value=""/>
                </label></td>
            </tr>
            <tr>
                <td valign="bottom"><label>
                    <input type="submit" name="submit" value="提交" onclick="f_tologin()">
                </label></td>
                <td valign="bottom"><label>
                    <input name="reset" type="reset" value="重置">
                    <input name="cancel" type="button" onclick="f_hidden()" value="去除背景" /><br>

                </label></td>
            </tr>
        </table>

    </form>
</div>
<script>
    function show() {
        box1 = document.getElementById("lightbox");
        box2 = document.getElementById("overlay");
        box1.style.display = 'block';
        box2.style.display = 'block';

    }

    function f_hidden() {
        box1 = document.getElementById("lightbox");
        box2 = document.getElementById("overlay");
        box1.style.display = 'none';
        box2.style.display = 'none';
    }


    function f_tologin() {
        form1.action = "http://www.sso.com:8081/islogin";
        form1.submit();


        <%--<%response.sendRedirect("page/success.jsp");%>--%>
    }
</script>

</body>
</html>


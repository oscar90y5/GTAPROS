<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=1,initial-scale=1,user-scalable=1" />
        <title>GTAPROS</title>
        <%@include file="WEB-INF/jspf/includes.jspf" %>
    </head>
    <body>
        <section class="container">
            <section class="login-form">
                <form method="post" action="login" role="login">
                    <img src="./images/logo.png" class="img-responsive" alt="" />
                    <input type="text" name="id" placeholder="User" required class="form-control input-lg" />
                    <input type="password" name="password" placeholder="Password" required class="form-control input-lg" />
                    <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Entrar</button>
                </form>
                <div class="form-links">
                    <a href="index.jsp">www.gtapros.com</a>
                </div>
            </section>
        </section>

    </body>
</html>
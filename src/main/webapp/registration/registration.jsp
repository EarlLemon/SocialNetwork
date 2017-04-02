<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <%--<meta charset="utf-8">--%>
    <title>Sign up</title>
        <style type="text/css">
            .lang{
                position:absolute;
                top:0;
                right:0;
            }
        </style>
</head>
<body>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="Bundle"/>
<fmt:message key="alreadyReg" var="alreadyReg"/>
<fmt:message key="signIn" var="signIn"/>
<fmt:message key="username" var="username"/>
<fmt:message key="fillForm" var="fillForm"/>
<fmt:message key="password" var="password"/>
<fmt:message key="repeatPas" var="repeatPas"/>
<fmt:message key="email" var="email"/>
<fmt:message key="firstname" var="firstname"/>
<fmt:message key="lastname" var="lastname"/>
<fmt:message key="signUp" var="signUp"/>

<div align="middle">

    <form action="/AddUserController" method="post">
        <h2>${fillForm}</h2>
        <input type="text" placeholder="${username}" name="username"> <br>
        <input type="password" placeholder="${password}" name="password"> <br>
        <input type="password" placeholder="${repeatPas}" name="repeatPassword"> <br>
        <input type="text" placeholder="${firstname}" name="firstname"> <br>
        <input type="text" placeholder="${lastname}" name="lastname"> <br>
        <input type="text" placeholder="${email}" name="email" id="email"> <br>
        <button class="btn btn-large btn-primary" type="submit" id=1>${signUp}</button>
    </form>

</div>
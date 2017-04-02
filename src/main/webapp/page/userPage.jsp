<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>My page</title>
    <style>
        #left {
            float: left;
            height: 100%;
            width: 50%;
        }

        #right {
            float: right;
            height: 100%;
            width: 50%;
        }
    </style>
</head>
<body>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="Bundle"/>
<fmt:message key="logout" var="logout"/>
<fmt:message key="allUsers" var="allUsers"/>
<fmt:message key="hello" var="hello"/>
<fmt:message key="subscribes" var="subs"/>
<fmt:message key="followers" var="followers"/>
<fmt:message key="enterLesson" var="enterLesson"/>
<fmt:message key="lessons" var="lessons"/>
<fmt:message key="myLessons" var="myLessons"/>
<fmt:message key="mySubs" var="mySubs"/>
<fmt:message key="user" var="user"/>
<fmt:message key="text" var="text"/>

<span>${subs}: <a href="/subscribeList/"> ${countSubscribes:countSubscribes(requestScope["subscribes"])}</a></span>
<span>${followers}: <a href="/followersList/"> ${countSubscribes:countSubscribes(requestScope["followers"])}</a></span>

<div align="right">
    <form action="/logout" method="POST">
        <button type="submit">${logout}</button>
    </form>
    <form action="/locale/locale.jsp">
        <button type="submit">language</button>
    </form>
</div>
<form action="GetAllUsers">
    <button type="submit">${allUsers}</button>
</form>
<div align="middle">
    <form action="AddLessonController">
        <textarea name="newPost" rows="4" cols="55" placeholder="${enterLesson}"></textarea><br/><br/>
        <button type="submit">${lessons}</button>
    </form>
</div>
<div align="middle" id="left">
    <span>${myLessons}</span> <br/>
    <table border="1">
        <tr>
            <th>${text}</th>
        </tr>

        ${postOut:getUserLessons(requestScope["posts"])}

    </table>
    <br/>
</div>
<div align="middle" id="right">
    <span>${mySubs}</span>
    <table border="1">
        <tr>
            <th>${user}</th>
            <th>${text}</th>
        </tr>
        ${subPostOut:getUserLessons(requestScope["subPosts"])}

    </table>
    <br/>

</div>
</body>
</html>


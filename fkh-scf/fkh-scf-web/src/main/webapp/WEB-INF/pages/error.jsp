<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link id="typeStyle" rel="stylesheet" type="text/css" href="../newassets/register/css/isMobileCss.css">
    <script src="../newassets/register/js/jquery-1.11.0.min.js"></script>
    <title>错误信息</title>
</head>

<body>
<!--错误信息页面-->
<section class="errorPage" style="text-align: center;">
    <img src="../newassets/imgs/icon_error.png">
    <section class="errorTitle">
        <p style="margin-top: 15px;">${errorMsg}</p>
    </section>
</section>
</body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/6/20
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<jsp:include page="top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
<div class="tray tray-center">
<div class="content-header">
    <h2> 添加员工 </h2>
    <p class="lead"></p>
</div>
<div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
<div class="panel heading-border">
<form:form action="${pageContext.request.contextPath}/employee/update" modelAttribute="employee" id="admin-form" name="addForm">
    <form:hidden path="password"/>
    <div class="panel-body bg-light">
    <div class="section-divider mt20 mb40">
    <span> 基本信息 </span>
    </div>
    <div class="section row">
    <div class="col-md-6">
    <label for="sn" class="field prepend-icon">
    <form:input path="sn" cssClass="gui-input" placeholder="工号..." readonly="true"/>
    <label for="sn" class="field-icon">
    <i class="fa fa-user"></i>
    </label>
    </label>
    </div>
    <div class="col-md-6">
    <label for="name" class="field prepend-icon">
    <form:input path="name" cssClass="gui-input" placeholder="姓名..."/>
    <label for="name" class="field-icon">
    <i class="fa fa-user"></i>
    </label>
    </label>
    </div>
    </div>
    <div class="section row">
    <div class="col-md-6">
    <label for="sn" class="field select">
    <form:select path="department_sn" items="${dlist}" itemLabel="name" itemValue="sn" placeholder="所属部门..." cssClass="gui-input"/>
    <i class="arrow double"></i>
    </label>
    </div>
    <div class="col-md-6">
    <label for="post" class="field select">
    <form:select path="post" items="${plist}" placeholder="职务..." cssClass="gui-input"/>
    <i class="arrow double"></i>
    </label>
    </div>
    </div>
    <div class="panel-footer text-right">
    <button type="submit" class="button"> 保存 </button>
    <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
    </div>
    </div>
</form:form>
    </div>
    </div>
    </div>
    </section>
    <jsp:include page="bottom.jsp"/>
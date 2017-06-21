<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>NHS</title>
</head>
<body>
<h2>Add Patient Data</h2>
<form:form method="POST" action="submit" modelAttribute="patient">
    <table>
        <tr>
            <td ><form:label path="id">Patient ID:</form:label></td>

            <td ><form:input id="id" path="id" value="${patient.id}" readonly="true" /></td>
        </tr>
        <tr>
            <td ><form:label path="name">Patient Name:</form:label></td>

            <td ><form:input id="name" path="name" value="${patient.name}"/><form:errors path="name" /></td>

        </tr>
        <tr>
            <td ><input name="submit" type="submit"  id="submit" value="Submit"/></td>
            <td ><input name="cancel" type="submit"  id="cancel" value="Cancel"/></td>
        </tr>
    </table>


</form:form>
</body>
</html>

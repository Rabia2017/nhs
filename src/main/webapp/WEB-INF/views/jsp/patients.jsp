<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NHS</title>
</head>
<body>
<h1>List Patients</h1>


<c:if test="${!empty patients}">
 <table align="left" border="1">
  <tr>
   <th colspan="2">Patient ID</th>
   <th colspan="2">Patient Name</th>
   </tr>

  <c:forEach items="${patients}" var="patient">


   <td colspan="2"><c:out value="${patient.id}"/></td>
    <td colspan="2"><c:out value="${patient.name}"/></td>

   <c:url var="updateUrl" value="/patientForm"/>
      <form id="${patientUpdateId}" action="${updateUrl}" method="POST">
     <td ><input id="id" name="id" type="hidden" value="${patient.id}" />
      <input type="submit" value="update" onClick=""/>
     </td>
   </form>

      <td>
       <c:url var="deleteUrl" value="/patients/delete.html"/>
       <form id="${patientFormId}" action="${deleteUrl}" method="POST">
        <input id="patient" name="patient" type="hidden" value="${patient.id}"/>
        <input type="submit" value="delete" onClick="return confirm('Are you sure you want to delete {} ' + ${patient.name})"/>
       </form>
      </td>

      </tr>


     </c:forEach>

     <tr>
      <a href="patientForm.html">Add More Patient</a>
     </tr>

    </table>
   </c:if>

   </body>

   </html>

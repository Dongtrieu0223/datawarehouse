<%@page import="model.Log"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
</head>
<body style="background-color: #f4f5f8">


<div class="col-12">
	<div class="card shadow mt-5">
	<!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Log</h6>
                   <a class="btn btn-link" href="<c:url value='/trang-chu'/>">Nhập config</a>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                
		                <table class="table table-bordered table-sm">
					    <thead class="thead-light">
					      <tr>
					        <th>Name log </th>
					        <th>Comment</th>
					        <th>STATUS</th>
					        <th>URL local</th>
					        <th>Time_download </th>
					        <th>Time_upload Staging </th>
					        <th>Time_upload WareHouse</th>
					      </tr>
					    </thead>
					    
					    <%--  --%>
					   <tbody>
					    <% ArrayList<Log> logs = (ArrayList) request.getAttribute("logs");%>
					    <% for(Log item : logs) { %>
					      <tr>
					      	<%-- lay name log --%>	
					      	<td><%=item.getNameLog() %></td>
					      	<%--lay comment --%>
					      	<td><%=item.getComment() %></td>
					      	
					      	
					      	<% if(item.getStatus().equals("DOWNLOAD_SUCCESS")) { %>
					      		<th class="text-success"><%= item.getStatus() %></th>
					      	<% } else if(item.getStatus().equals("UPLOAD_STAGING")) {%>
					      		<th class="text-warning"><%= item.getStatus() %></th>
					      	<% } else { %>
					      		<th class="text-primary"><%= item.getStatus() %></th>
					      	<% } %>
					  
					      	
					      	<%-- lay url local --%>
					      	<td><%= item.getUrlLocal() %></td>
					      	<%--lay time_download--%>
					      	<td><%=item.getTime_download() %> </td>
					      	<%--lay time_upload Staging --%>
					      	<td><%=item.getTime_uploadStraging() %></td>
					      	<%--lay time_upload WareHouse --%>
					      	<td><%= item.getTime_uploadWarehouse() %></td>
					      </tr>
					     <% } %>
					    </tbody>
					    
					  </table>
                </div>
               
    </div>
              
</div>
	
	<script type="text/javascript">
	
	$(document).ready(function() {
		  setInterval(function() {
		    cache_clear()
		  }, 1000);
		});
		function cache_clear() {
		  window.location.reload(true);
		  // window.location.reload(); use this if you do not remove cache
		}
	</script>
	
</body>
</html>
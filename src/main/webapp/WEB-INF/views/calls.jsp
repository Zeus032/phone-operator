<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
	<title>Phone Operator App - Calls</title>
	<style>
		table {
		    font-family: arial, sans-serif;
		    border-collapse: collapse;
		}
		td, th {
		    border: 1px solid #dddddd;
		    text-align: left;
		    padding: 8px;
		}
		tr:nth-child(even) {
    		background-color: #dddddd;
		}
	</style>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  	</head>
	<body>
		<div>
			<h2>Calls</h2>
			<form action="/search" method="post">
				*Phone number: <input type="text" name="phoneNumber" />
				<br /><br />
				
				Date from: <input type="text" class="datepicker" name="dateFrom">
				<br /><br />
				
				Date to: <input type="text" class="datepicker" name="dateTo">
				<br /><br />
				
				*Call direction: <br />
				<input type="radio" name="callDirection" value="incoming"> Incoming<br>
				<input type="radio" name="callDirection" value="outgoing"> Outgoing<br>
        <input type="radio" name="callDirection" value="all"> All<br>
				<br /><br />
	
				<input type="submit" value="Search" />
			</form>
		</div>
	
		<div>
			<c:if test="${not empty errorMessage}">
	    		<p style="color: red">*${errorMessage}</p>
			</c:if>
		</div>
	
		<div>
			<h2>Results</h2>
			<c:if test="${not empty callSearchResponses}">
		    	<table>
				  	<tr>
		    			<th>Number 1</th>
              <th>User 1</th>
              <th>Address 1</th>
					    <th>Call date</th>
              <th>Duration</th>
              <th>Number 2</th>
              <th>User 2</th> 
              <th>Address 2</th> 
		  			</tr>
					<c:forEach items="${callSearchResponses}" var="callSearchResponse">
			  			<tr>
			    			<td>${callSearchResponse.numberCaller}</td>
			    			<td>${callSearchResponse.userCaller}</td>
			    			<td>${callSearchResponse.addressCaller}</td>
						    <td><fmt:formatDate type="both" value="${callSearchResponse.callDate}" /></td> 
						    <td>${callSearchResponse.duration}</td>
						    <td>${callSearchResponse.numberReciever}</td>
                <td>${callSearchResponse.userReciever}</td>
                <td>${callSearchResponse.addressReciever}</td>
			  			</tr>
					</c:forEach>
				</table>
	    	</c:if>
		</div>
	</body>
	<script>
  	$(function() {
    	$(".datepicker").datepicker({
    		dateFormat: "dd-mm-yy"
    	});
    });
  	</script>
</html>
<html>
<body>
  <form action = "" method = "post">
    <input type="submit" name="insertDB" value="Import CSV into OrientDB" />
  </form>
  <br>
  <div>
    <h2>
      <c:if test="${not empty initDbResponse}">
        ${initDbResponse}
      </c:if>
    </h2>
      
    </div>
  <br>
	<h2><a href="/calls">Search for calls between numbers...</a></h2>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script>	
  function myfunction(){
	  document.getElementById("1").style.visibility = "visible";
  }
  </script>
</head>
<body>

<div class="Container" style="width:760px; margin: 20px auto; padding-bottom:20px;" >
<div class="page-header">
	<img alt="" src="/img/logo.jpg">
    <h1>View Review</h1>      
  </div>
<%-- <% 
	String name=request.getParameter("user");
	session.setAttribute("Name",name);%> --%>

<h3 style="text-align: center;"><p class="text-danger">Welcome Admin!</p></h3>

<div class="row">
<div class="col-md-6" style="text-align: center;">
	<p class="text-info">Click to add new books</p>
	<input type="button" name="Add" value="ADD" onclick="myfunction()"/>
</div>
<div class="col-md-6" style="text-align: center;">
	<p class="text-info">Click to view books</p>
	<form action=""AdminUpdate.jsp">
	<input type="submit" name="View" value="Update Books" formaction="AdminUpdate.jsp" />
	</form>
</div>
</div>
<div id="1" style="visibility:hidden; ">
<!-- <form  action="AddBook"> -->
<form method="post" action="AddBook">
    <div class="form-group">
      <label >Enter Book name:</label>
      <input type="text" class="form-control" name="book" placeholder="Enter Book name">
    </div>
    <div class="form-group">
      <label >Description:</label>
      <input type="text" class="form-control" name="description" placeholder="Description" >
    </div>
    <div class="form-group">
      <label >genre:</label>
      <select class="form-control" name="genre" id="sel1" >
        <option selected disabled>select genre</option>
        <option>fictional</option>
        <option>comedy</option>
        <option>drama</option>
        <option>horror</option>
        <option>nonfiction</option>
        <option>romanticnovel</option>
        <option>satire</option>
        <option>tragedy</option>
        <option>tragicomedy</option>
        <option>fantasy</option>
      </select>
    </div>
    <div class="form-group">
      <label >Author</label>
      <input type="text" class="form-control" name="author" placeholder="Author">
    </div>
    <div class="form-group" style="display-block:none;">
  
      <button type="submit" class="btn btn-default" name="add-book" >Add Book</button>
    
  </div>
   
  </form>

</div>
</div>
</body>
</html>
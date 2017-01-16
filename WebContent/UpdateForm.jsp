<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    
 %>
 
 <%@page import="model.Books"%>
<!DOCTYPE html>
<html>
<head>
	<title>Update Form</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="default.css" />
</head>
<body>
<%
	Books processBook = (Books)session.getAttribute("processBook");
%>
	<div class="page">
        <form class="form-horizontal" action="EditBook" method="post">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Title</label>
                <div class="col-sm-10">
                  <input value="<%=processBook.getTitle() %>" type="input" class="form-control" id="name" name="title" placeholder="Enter Book Title">
                </div>
            </div>
            <div class="form-group">
                <label for="author" class="col-sm-2 control-label">Author</label>
                <div class="col-sm-10">
                  <input value="<%=processBook.getAuthor() %>" type="text" class="form-control" id="author" name="author" placeholder="Enter Author Name">
                </div>
            </div>
            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-10">
                  <textarea style="resize: none;" class="form-control" id="description" name="description" placeholder="Enter Description"><%=processBook.getDescription()%></textarea>
                </div>
            </div>
            <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
              <input type="submit" name="btn-submit" class="btn btn-default" value="Update">
              <input type="submit" name="btn-cancel" class="btn btn-default" value="Cancel">
            </div>
            </div>
        </form>
    </div>
</body>
</html>
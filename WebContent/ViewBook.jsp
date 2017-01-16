<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.Books" %>
    <%@page import="model.Review" %>
    <%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>View Books</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="default.css" />
</head>
<body>
<% 
	Books processBook = (Books)session.getAttribute("processBook");
	ArrayList<Review>reviews=new ArrayList<>();
	reviews=(ArrayList<Review>)session.getAttribute("reviews");
%>			
			<form method="post" action="AdminUpdate.jsp">
				<div class="page">
		
						<div class="book-details">
							<h2><%=processBook.getTitle()%></h2>
							<p class="description">Author:<%=processBook.getAuthor()%></p>
							<p class="description">Description:<%=processBook.getDescription()%></p>
							
							<% for(int i=0;i<reviews.size();i++)
							{%>
							<p class="description">Review:<%=reviews.get(i).getComment()%></p>	
							<p class="description">Rating:<%=reviews.get(i).getRating()%></p>
							<%}%>
							<div class="col-sm-offset-2 col-sm-10">
				              <input type="submit" name="btn-submit" class="btn btn-default" value="Back">
				            </div>		
			            </div>
				</div>
		</form>
	</body>
</html>
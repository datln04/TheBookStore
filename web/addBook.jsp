<%-- 
    Document   : addBook
    Created on : May 11, 2021, 5:16:05 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    </head>
    <style>
        body {
            background: #555;
        }
        .content{
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 10px;
            border-radius: 8px;
            margin-top: 130px;
        }
        #but{
            margin: 10px 100px 5px 220px;
        }
    </style>
    <body>
        <c:if test="${sessionScope.User.roleID eq '1'}">
            <div class="content">


                <form action="MainController" method="POST">
                    <div class="form-group ">
                        <label for="exampleInputEmail1">Book Name</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="bookName" placeholder="Enter book Name" name="txtBookName">               
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Images</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Images..." name="txtImages">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Price</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Price..." name="txtPrice">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Quantity</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Quantity..." name="txtQuantity">
                    </div>
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Type</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="txtCateID">
                            <c:forEach items="${sessionScope.ListCate}" var="c">
                                <option value="${c.categoryID}">${c.categoryName}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <input type="submit" class="btn btn-primary" name="btAction" value="Add" id="but"/>
                </form>
            </div>
        </c:if>
    </body>
</html>

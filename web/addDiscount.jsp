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
                        <label for="exampleInputEmail1">Discount Name</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="discountName" placeholder="Enter discountName" name="txtDiscountName" required>               
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Key Word</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="KeyWord..." name="txtKeyWord" required>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Discount Percent</label>
                        <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Percent..." name="txtDiscountPercent" required>
                    </div>

                    <div class="form-group">
                        <label for="exampleFormControlSelect1">End Date</label>
                        <input type="date" class="form-control" id="exampleInputPassword1" placeholder="End Date..." name="txtEndDate" required>
                    </div>

                    <input type="submit" class="btn btn-primary" name="btAction" value="AddDiscount" id="but"/>
                </form>
            </div>
        </c:if>
    </body>
</html>

<%-- 
    Document   : manageBook
    Created on : May 25, 2021, 7:33:12 PM
    Author     : ASUS
--%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Page</title>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <style>
            .add{
                text-decoration: none;
                color: white;
            }
        </style>
    </head>
    <body style="background-color: #212934">
        <c:if test="${sessionScope.User.roleID eq '1'}">
            <a class="nav-link" href="MainController?btAction=LogOut">Logout</a>
            <table class="table table-striped" >
                <thead>
                    <tr style="background-color: gold">
                    <th scope="col">Book ID</th>
                    <th scope="col">Book Name</th>
                    <th scope="col">Images</th>
                    <th scope="col">Price</th>
                    <th scope="col">Category</th>
                    <th scope="col">Update</th>
                    <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="b" items="${sessionScope.ListBook}">
                        <form action="MainController" method="POST">
                            <tr>
                            <th scope="row"><input type="text" name="txtBookID" value="${b.bookID}" readonly="true" style="border-radius: 5px"/></th>
                            <td><input type="text" name="txtBookName" value="${b.bookName}" style="border-radius: 5px"/></td>
                            <td><input type="text" name="txtImages" value="${b.images}" style="border-radius: 5px"/></td>
                            <td><input type="text" name="txtPrice" value="${b.price}" style="border-radius: 5px"/> </td>
                            <td>
                                <select name="txtCateID" aria-label="Default select example">
                                    <c:forEach items="${sessionScope.ListCate}" var="c">
                                        <option value="${c.categoryID}" 
                                                <c:if test="${c.categoryID eq b.categoryID}">selected</c:if>
                                                    >
                                                ${c.categoryName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="submit" value="Update" class="btn btn-info" name="btAction" /></td>

                            <td><!--<input type="button" value="Delete" class="btn btn-danger" onClick="return ano()"/> -->
                                <input type="submit" name="btAction" id="btnDelete" value="Delete" class="btn btn-danger">
                            </td>
                            </tr>  
                        </form>
                    </c:forEach>

                </tbody>
            </table>
        <button type="button" class="btn btn-success" style="float: right"><a class="add"href="addBook.jsp">Add New</a></button>
        <button type="button" class="btn btn-success" style="float: right"><a class="add"href="addDiscount.jsp">Add DisCount</a></button>
        <p style="clear: both"></p>     

        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

        <script>
                                function ano() {
                                    swal({
                                        title: "Are you sure?",
                                        text: "Once deleted, you will not be able to recover this Book!",
                                        icon: "warning",
                                        buttons: true,
                                        dangerMode: true,
                                    }).then((willDelete) => {
                                        if (willDelete) {
                                            document.getElementById("btnDelete").click();
                                            swal("Poof! The Book has been deleted!", {
                                                icon: "success",
                                            });
                                        } else {
                                            swal("The Book is safe!");
                                        }
                                    });
                                }

        </script>
    </c:if>  
</body>
</html>

<%-- 
    Document   : shoppingCard
    Created on : May 14, 2021, 11:43:43 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    </head>
    <body>
        <c:if test="${sessionScope.User.roleID eq '2'}">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="shopping-cart">
                    <div class="px-4 px-lg-0">

                        <div class="pb-5">
                            <div class="container">
                                <div class="row">
                                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                                        <!-- Shopping cart table -->
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="p-2 px-3 text-uppercase">Date</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="p-2 px-3 text-uppercase">Name</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Image</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Quantity</div>
                                                        </th>
                                                        <th scope="col" class="border-0 bg-light">
                                                            <div class="py-2 text-uppercase">Price</div>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="h" items="${sessionScope.ListHistory}">
                                                    <tr>
                                                        <td class="align-middle">
                                                            ${h.date}
                                                        </td>
                                                        <td class="align-middle">
                                                            ${h.bookName}
                                                        </td> 
                                                        <th scope="row">
                                                            <div class="p-2">
                                                                <img src="${h.images}" alt="card" width="70" class="img-fluid rounded shadow-sm">
                                                                <!-- <div class="ml-3 d-inline-block align-middle">
                                                                     <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${item.book.bookName}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                                 </div> -->
                                                            </div>
                                                        </th>
                                                        <td class="align-middle">
                                                            ${h.quantity}
                                                        </td>
                                                        <td class="align-middle">
                                                            ${h.price}
                                                        </td>

                                                    </tr> 
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- End -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.jsp"></jsp:include>
        </c:if>
    </body>
</html>




<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <style>
            .add{
                text-decoration: none;
                color: white;
            }
        </style>
    </head>

    <body>
        <c:if test="${sessionScope.User.roleID eq '2'}">
            <jsp:include page="header.jsp"></jsp:include>
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <div class="w-20 d-flex align-items-center" >
                                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                                        <li class="breadcrumb-item"><a href="#">Category</a></li>
                                    </div>
                                    <form action="MainController" class="d-flex align-items-center" >
                                        <li > <input class="form-control " type="number" name="number1" value="${param.number1}$" placeholder="" required="true"></li>
                                    <li class="mx-2" > <input class="form-control" type="number" name="number2" value="${param.number2}$" placeholder="" required="true"></li>
                                    <li> <input class="btn btn-success" type="submit" name="btAction" value="Search"></li>
                                </form>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row">

                    <!-- category -->               
                    <div class="col-sm-3">
                        <div class="card bg-light mb-3">
                            <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                            <ul class="list-group category_block">
                                <c:forEach var="c" items="${sessionScope.ListCate}">
                                    <li class="list-group-item text-white"><a href="MainController?btAction=Category&cateId=${c.categoryID}">${c.categoryName}</a></li>
                                    </c:forEach>

                            </ul>
                        </div>
                    </div>
                    <!-- category -->

                    <div class="col-sm-9">
                        <div class="row">
                            <c:forEach var="b" items="${sessionScope.ListBook}">
                                <div class="col-12 col-md-6 col-lg-4">
                                    <div class="card">
                                        <img class="card-img-top" src="${b.images}" alt="Card image cap">
                                        <div class="card-body">
                                            <h4 class="card-title show_txt"><a href="#" title="View Product">${b.bookName}</a></h4>                                     
                                            <div class="row">
                                                <div class="col">
                                                    <p class="btn btn-danger btn-block">${b.price} $</p>
                                                </div>
                                                <div class="col">
                                                    <c:if test="${b.quantity gt 0}">
                                                        <a href="MainController?btAction=AddToCard&bookID=${b.bookID}" class="btn btn-success btn-block">Add to cart</a>
                                                    </c:if>
                                                    <c:if test="${b.quantity eq 0}">
                                                        Out Of Stock
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>
            </div>

            <jsp:include page="footer.jsp"></jsp:include>
                <script>
                <c:if test="${requestScope.SUCCESS != null}">
                    swal({
                        title: "Check Out Success!",
                        text: "You clicked the button!",
                        icon: "success",
                        button: "Aww yiss!",
                    });
                </c:if>
            </script>
        </c:if>
    </body>
</html>
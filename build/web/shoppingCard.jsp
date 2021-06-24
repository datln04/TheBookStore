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
        <title>Shopping Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            #checkOUT{
                border-radius: 30px;
                width: 411px;
                margin-bottom: 10px;
            }
        </style>

    </head>

    <body>
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
                                                        <div class="p-2 px-3 text-uppercase">Product</div>
                                                    </th>

                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Price</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">Quantity</div>
                                                    </th>
                                                    <th scope="col" class="border-0 bg-light">
                                                        <div class="py-2 text-uppercase">delete</div>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="item" items="${sessionScope.Order.listItem}">
                                                <tr>
                                                    <th scope="row">
                                                        <div class="p-2">
                                                            <img src="${item.book.images}" alt="card" width="70" class="img-fluid rounded shadow-sm">
                                                            <div class="ml-3 d-inline-block align-middle">
                                                                <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block">${item.book.bookName}</a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                                            </div>
                                                        </div>
                                                    </th>
                                                    <td class="align-middle"><strong>${item.book.price}</strong></td>
                                                    <td class="align-middle">
                                                        <c:if test="${item.quantity gt 1}">
                                                            <a href="MainController?btAction=DescreaseItem&itemID=${item.itemID}"><button class="btnSub">-</button></a> 
                                                        </c:if>
                                                        <strong>${item.quantity}</strong>
                                                        <c:if test="${item.book.quantity gt 0}">
                                                            <a href="MainController?btAction=IncreaseItem&itemID=${item.itemID}"><button class="btnAdd">+</button></a>
                                                        </c:if>
                                                    </td>
                                                    <td class="align-middle"><a href="MainController?btAction=RemoveItemFromCard&itemID=${item.itemID}" class="text-dark">
                                                            <button type="button" class="btn btn-danger">Delete</button>
                                                        </a>
                                                        <c:set var="totalBook" value="${totalBook + (item.quantity * item.book.price)}"></c:set>
                                                        </td>
                                                    </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- End -->
                            </div>
                        </div>

                        <div class="row py-5 p-4 bg-white rounded shadow-sm">
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Voucher</div>
                                <div class="p-4">
                                    <div class="input-group mb-4 border rounded-pill p-2">
                                        <form action="MainController" method="POST">
                                            <select name="txtDiscountID">
                                                <c:forEach var="d" items="${sessionScope.ListDiscount}">
                                                    <option class="form-control border-0" value="${d.discountID}" aria-describedby="button-addon3">${d.discountName},${d.keyWord}-${d.discountPercent}%</option>
                                                </c:forEach>
                                            </select>  
                                            <input type="submit" value="DiscountApply" class="btn btn-info" name="btAction" style="float: left"/>
                                        </form>  
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Total Price</div>
                                <div class="p-4">
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Shipping</strong><strong>Free ship</strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">VAT</strong><strong>0 $</strong></li>
                                        <c:if test="${empty requestScope.disCount}">                                                
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>                                                
                                                ${totalBook}
                                            </li>
                                            <button value="CheckOut" class="btn" id="checkOUT"/><a href="MainController?btAction=CheckOut&total=${totalBook}&discountID=${requestScope.disCount}">CheckOut</a></button>
                                        </c:if>
                                        <c:if test="${not empty requestScope.disCount}">
                                            <c:set var="totalBook" value="${totalBook * (100 - requestScope.DISCOUNT)/100} "/>
                                            <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>                                                
                                                ${totalBook}
                                            </li>
                                            <button value="CheckOut" class="btn" id="checkOUT"/><a href="MainController?btAction=CheckOut&total=${totalBook}&discountID=${requestScope.disCount}">CheckOut</a></button>
                                        </c:if>
                                    </ul>
                                    <div id="paypal-button-container"></div>
                                    <form action="MainController" style="display: none"> 
                                        <input type="hidden" name="total" value="${totalBook}" />
                                        <input type="hidden" name="discountID" value="${requestScope.disCount}" />
                                        <input type="submit" value="CheckOut" name="btAction" id="PPCheckOut"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
            <script src="https://www.paypal.com/sdk/js?client-id=AbGqiNi-uM7Hz50d9_wes6kOMRYQzGmPq_q8kSyxNHmQDH0IGQEep9pl9bb3zVndiS1p7IKwrlTWE669"></script>



            <script>
                paypal.Buttons({
                    style: {
                        color: 'blue',
                        shape: 'pill'
                    },
                    createOrder: function (data, actions) {
                        // This function sets up the details of the transaction, including the amount and line item details.
                        return actions.order.create({
                            purchase_units: [{
                                    amount: {
                                        value: '${totalBook+0}'
                                    }
                                }]
                        });
                    },
                    onApprove: function (data, actions) {
                        // This function captures the funds from the transaction.
                        return actions.order.capture().then(function (details) {
                            // This function shows a transaction success message to your buyer.

                            document.getElementById("PPCheckOut").click();
                        });
                    }
                }).render('#paypal-button-container');
                //This function displays Smart Payment Buttons on your web page.

        </script> 
    </body>
</html>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!--begin of menu-->

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="MainController?btAction=LoadPage">Psy Store</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Hello ${sessionScope.User.userName}</a>
                </li>
                <c:if test="${sessionScope.User.roleID eq 2}">
                    <li class="nav-item">
                        <a class="nav-link" href="history.jsp">History</a>
                    </li> 
                </c:if>              
                <li class="nav-item">
                    <a class="nav-link" href="MainController?btAction=LogOut">Logout</a>
                </li>
            </ul>

            <form action="MainController" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input name="txtSearchValue" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <input type="submit" value="Search" name="btAction"/>
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <a class="btn btn-success btn-sm ml-3" href="shoppingCard.jsp">
                <i class="fa fa-shopping-cart"></i> Cart
                <span class="badge badge-light">${sessionScope.Order.getCountList()}</span>
            </a>

        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading">Kho Sách Uy Tín Và Chất Lượng</h1>
        <p class="lead text-muted mb-0">Kiến thức là vô tận - Psy Store cung cấp mọi loại sách </p>
    </div>
</section>
<!--end of menu-->

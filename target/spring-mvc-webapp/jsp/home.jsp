<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Skateboarding Tricks</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/layout.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" 
                                data-target="#navbar1">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="${pageContext.request.contextPath}/home" class="navbar-left"><img src="${pageContext.request.contextPath}/img/Skateboard-logo-blue.png" class="img-logo"></a>
                        <!--<a class="navbar-left navbar-text" href="${pageContext.request.contextPath}/home">Skateboard Trick List</a>-->
                    </div>

                    <div id="navbar1" class="navbar-collapse collapse navbar-right">
                        <ul class="nav navbar-nav">
                            <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                            <li role="presentation"><a href="${pageContext.request.contextPath}/search"><span class="glyphicon glyphicon-search"></span> Search</a></li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
                <!--/.container-fluid -->
            </nav>
            <div class="row">
                <div class="col-md-6">
                    <h2>Skateboard Tricks</h2>
                    <%@include file="trickSummaryTableFragment.jsp"%>
                </div>
                <div class="col-md-6">
                    <div class="col-md-offset-4 col-md-8">
                        <h2>Add New Trick</h2>
                    </div>
                    <form class="form-horizontal" action="trick" method="POST" id="newTrickForm">
                        <div class="form-group">
                            <label for="add-name" class="col-md-4 control-label">
                                Trick Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-name" placeholder="Trick Name" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-category" class="col-md-4 control-label">
                                Category:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-category" placeholder="Category" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-type" class="col-md-4 control-label">
                                Type:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" 
                                       id="add-type" placeholder="Type" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-rating" class="col-md-4 control-label">
                                Difficulty Rating:
                            </label>
<!--                            <div class="col-md-8">
                                <input type="number" class="form-control" 
                                       id="add-rating"/>
                            </div>-->
                            <div class="col-md-8">
                                <select class="form-control" id="add-rating">
                                    <option value="1">1 - Easiest</option>
                                    <option value="2">2 - Easy</option>
                                    <option value="3">3 - Medium</option>
                                    <option value="4">4 - Hard</option>
                                    <option value="5">5 - Hardest</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default">
                                    Create Trick
                                </button>
                            </div>
                        </div>
                    </form>
                    <div id="validationErrors" class="alert alert-danger" style="display:none"/>
                </div>
            </div>
        </div>
        <%@include file="detailsEditModalFragment.jsp"%>
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/trickList.js"></script>
    </body>
</html>


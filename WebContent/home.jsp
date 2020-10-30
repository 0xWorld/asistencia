<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<%@ page import ="clase_mvc.AlumnoBean"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- Head -->
<head>
    <meta charset="utf-8" />
    <title>Home</title>

    <meta name="description" content="blank page" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">

    <!--Basic Styles-->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link id="bootstrap-rtl-link" href="" rel="stylesheet" />
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" />
    <link href="assets/css/weather-icons.min.css" rel="stylesheet" />

    <!--Fonts-->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300"
          rel="stylesheet" type="text/css">

    <!--Beyond styles-->
    <link id="beyond-link" href="assets/css/beyond.min.css" rel="stylesheet" />
    <link href="assets/css/demo.min.css" rel="stylesheet" />
    <link href="assets/css/typicons.min.css" rel="stylesheet" />
    <link href="assets/css/animate.min.css" rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />

    <!--Skin Script: Place this script in head to load scripts for skins and rtl support-->
    <script src="assets/js/skins.min.js"></script>
</head>
<!-- /Head -->
<!-- Body -->
<body>
    <!-- Loading Container -->
    <div class="loading-container">
        <div class="loader"></div>
    </div>
    <!--  /Loading Container -->
    <!-- Navbar -->
    <div class="navbar">
        <div class="navbar-inner">
            <div class="navbar-container">
                <!-- Navbar Barnd -->
                <div class="navbar-header pull-left">
                    <a href="#" class="navbar-brand">
                        <small>
                            <img src="assets/img/logo.png" alt="" />
                        </small>
                    </a>
                </div>
                <!-- /Navbar Barnd -->
                <!-- Sidebar Collapse -->
                <div class="sidebar-collapse" id="sidebar-collapse">
                    <i class="collapse-icon fa fa-bars"></i>
                </div>
                <!-- /Sidebar Collapse -->
                <!-- Account Area and Settings --->
                <div class="navbar-header pull-right">
                    <div class="navbar-account">
                        <ul class="account-area">
                           
                            <li>
                                <a class="login-area dropdown-toggle" data-toggle="dropdown">
                                    <div class="avatar" title="View your public profile">
                                        <img src="assets/img/avatars/adam-jansen.jpg">
                                    </div>
                                    <section>
                                        <h2><span class="profile"><span>${nombre}</span></span></h2>
                                    </section>
                                </a>
                                <!--Login Area Dropdown-->
                                <ul class="pull-right dropdown-menu dropdown-arrow dropdown-login-area">
                                    <!--/Theme Selector Area-->
                                    <li class="dropdown-footer">
                                        <a href="<%=request.getContextPath()%>/login">
                                            Sign out
                                        </a>
                                    </li>
                                </ul>
                                <!--/Login Area Dropdown-->
                            </li>
                            <!-- /Account Area -->                            
                            <!-- Settings -->
                        </ul>
                    </div>
                </div>
                <!-- /Account Area and Settings -->
            </div>
        </div>
    </div>
    <!-- /Navbar -->
    <!-- Main Container -->
    <div class="main-container container-fluid">
        <!-- Page Container -->
        <div class="page-container">
            <!-- Page Sidebar -->
            <div class="page-sidebar" id="sidebar">                
                <!-- Sidebar Menu -->
                <ul class="nav sidebar-menu">
                    <!--Dashboard-->
                    <li>
                        <a href="index.html">
                            <i class="menu-icon glyphicon glyphicon-home"></i>
                            <span class="menu-text"> Asistencia </span>
                        </a>
                    </li>                    
                </ul>
                <!-- /Sidebar Menu -->
            </div>
            <!-- /Page Sidebar -->            
            
            <!-- Page Content -->
            <div class="page-content">
                <!-- Page Breadcrumb -->
                <div class="page-breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="#">Asistencia</a>
                        </li>                                             
                    </ul>
                </div>
                <!-- /Page Breadcrumb -->
                <!-- Page Header -->
                <div class="page-header position-relative">
                    <div class="header-title">
                        <h1>
                            ${clase.getNomcur()}
                        </h1>
                    </div>                  
                </div>
                <!-- /Page Header -->
                <!-- Page Body -->
                <div class="page-body">
                    
                    
                    <div class="row">
                    		<div class="col-lg-3">
                    		</div>
                         <div class="col-lg-6">
                             <div class="widget">
                                 <div class="widget-header bordered-bottom bordered-themesecondary">
                                     <i class="widget-icon fa fa-tags themesecondary"></i>
                                     <span class="widget-caption themesecondary">${clase.getNomcur()} : ${clase.getHorini()} -  ${clase.getHorfin()}</span>
                                     <form action="<%=request.getContextPath()%>/docente" method="POST">                                                                                
                                     	<button class="btn btn-default" type="submit">MarcarSalida</button>
                                     </form>                                     
                                 </div><!--Widget Header-->
                                 <div class="widget-body  no-padding">
                                     <div class="tickets-container">
                                         <ul class="tickets-list">
                                         <form action="<%=request.getContextPath()%>/clase" method="POST">
                                         
                                        <% ArrayList<AlumnoBean> alumnos = (ArrayList<AlumnoBean>)request.getAttribute("alumnos");
                                        	for (int i = 0; i < alumnos.size(); i++) {%>   
                                        	   <li class="ticket-item">
                                                 <div class="row">
                                                     <div class="ticket-user col-lg-8 col-sm-12">
                                                         <img src="assets/img/avatars/adam-jansen.jpg" class="user-avatar">
                                                         <span class="user-name"><%=alumnos.get(i).getNomalu() %></span>
                                                         <span class="user-at"><%=alumnos.get(i).getCodalu() %></span>                                                                
                                                     </div>
                                                     <div class="ticket-time  col-lg-4 col-sm-6 col-xs-12">
                                                         <div class="divider hidden-md hidden-sm hidden-xs"></div>
                                                         
                                                         <div class="col-xs-4">
	                                                        <label>
	                                                        	<% if(alumnos.get(i).getAsistio()){ %>
	                                                            <input name="cbalumno" value="<%=alumnos.get(i).getCodalu() %>" class="checkbox-slider yesno" type="checkbox" checked="checked">	                                                            
	                                                            <% }else{ %>
	                                                            <input name="cbalumno" value="<%=alumnos.get(i).getCodalu() %>" class="checkbox-slider yesno" type="checkbox">
	                                                            <% } %>
	                                                            <span class="text"></span>
	                                                        </label>
	                                                    </div>
                                                     </div>                                                                                                                        
                                                 </div>
                                             </li>
				   						<% } %>
				   						<button class="btn btn-default" type="submit">Guardar Asistencia Alumnos</button>				   							
				   						</form>
                                         </ul>
                                     </div>
                                 </div>
                             </div>

                         </div>
                     </div>
                    
                    
                </div>
                <!-- /Page Body -->
            </div>
            <!-- /Page Content -->
        </div>
        <!-- /Page Container -->
        <!-- Main Container -->

    </div>

    <!--Basic Scripts-->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/slimscroll/jquery.slimscroll.min.js"></script>

    <!--Beyond Scripts-->
    <script src="assets/js/beyond.min.js"></script>

    <!--Page Related Scripts-->
    
</body>
<!--  /Body -->
</html>
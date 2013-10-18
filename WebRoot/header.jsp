<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.io.*"%>
<%
/**
 * Simple document system front page for presentation purpose 
 * 
 * email: s3440760@student.rmit.edu.au
 * @author Yongjiang Zhang
 *
 */
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

  <html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=us-ascii" />

      <title>IIDE Assignment Presentation</title>
    <style type="text/css" media="screen">
      #navigation-bar {
        position:relative;
        height:50px;
        color:#3e3e3e;
        width:100%;
        text-align:center;
        margin:10px auto;
      }

      #navigation-bar ul {
        border-bottom:#000000 2px solid;
        padding-bottom:5px;
        margin:0;
        font-weight:bold;
        font-size:13px;
        font-family:Arial, Helvetica, serif;
      }

      #navigation-bar ul li {
        display:inline;
        width:32px;
        padding-right: 20px;
        margin-bottom:5px;
      }
    </style>
    </head>
<body>
      <div id="navigation-bar">
        <!-- ********** TOP NAVIGATION ******** -->
        <ul>
          <li><a href="index.jsp">Home Page</a></li>
          <li><a href="login.jsp">Login</a></li>
          <li><a href="register.jsp">Register</a></li>
          <li><a href="upload.jsp">Upload</a></li>
          <li><a href="docList.jsp">Documents</a></li>
        </ul>
      </div>
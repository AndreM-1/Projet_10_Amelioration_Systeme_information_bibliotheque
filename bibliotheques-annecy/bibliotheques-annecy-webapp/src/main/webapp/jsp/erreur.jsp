<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<%@ include file="_include/head.jsp"%>
	</head>
	<body>
		<div class="container">
			<!-- Header -->
			<%@ include file="_include/header.jsp"%>
			
			<section>
			    <h1 class="text-center">Une erreur s'est produite</h1>
			    <s:actionerror />
			</section>
			
			<!-- Footer -->
			<%@ include file="_include/footer.jsp"%>
	    </div>
	</body>
</html>
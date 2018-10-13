<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>异步刷新   服务器端推送</title>
</head>
<body>
<script type="text/javascript" src="<c:url value="assets/jquery-3.3.1.js"/>"></script>
<script type="text/javascript">
	defer();
	function defer(){
		$.get("defer",function(data){
			console.log(data);
			defer();
		})
	}
</script>
</body>
</html>
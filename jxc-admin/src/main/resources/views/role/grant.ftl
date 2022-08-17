<html>
<head>
	<link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.excheck.js"></script>
</head>
<body>
<div id="test1" class="ztree"></div>
<input id="roleId" value="${roleId!}" type="hidden">
<script type="text/javascript">
	var ctx="${ctx.contextPath}";
</script>
<script type="text/javascript" src="${ctx.contextPath}/js/role/grant.js"></script>
</body>
</html>
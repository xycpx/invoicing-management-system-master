<html>
<head>
	<#include "../common.ftl">
	<link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.excheck.js"></script>
</head>
<body>
<div id="goodsType" class="ztree"></div>
<#--<div class="layui-form-item layui-row layui-col-xs12">
	<div class="layui-input-block">
		<button class="layui-btn layui-btn-lg" lay-submit=""
				lay-filter="addOrUpdateGoods">确定
		</button>
		<a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
	</div>
</div>-->
<input type="hidden" name="typeId" value="${typeId!""}"/>
<script type="text/javascript" src="${ctx.contextPath}/js/goods/goods.type.js"></script>
</body>
</html>
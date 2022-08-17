<!DOCTYPE html>
<html>
<head>
	<title>选择商品页</title>
	<#include "../common.ftl">
	<link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
	<script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">


<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 12%" >

			<div class="layui-card-body">
				<fieldset class="layui-elem-field layui-field-title" >
					<legend style="font-size: 15px">商品类别</legend>
				</fieldset>

				<div id="goodsTypeTree" class="ztree">
				</div>
				<input type="hidden" name="typeId"/>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 88%">
			<div class="layui-card">
				<fieldset class="layui-elem-field layui-field-title" >
					<legend style="font-size: 15px">商品管理</legend>
				</fieldset>
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<div class="layui-inline">
									<div class="layui-input-inline">
										<input type="text" name="goodsName"
											   class="layui-input
					searchVal" placeholder="商品名" />
									</div>
									<a class="layui-btn search_btn" data-type="reload"><i
												class="layui-icon">&#xe615;</i> 搜索</a>
								</div>
							</form>
						</blockquote>
						<table id="goodsList" class="layui-table"  lay-filter="goods"></table>

						<#--操作-->
						<script id="goodsListBar" type="text/html">
							<a class="layui-btn layui-btn-xs" id="add" lay-event="add">选择</a>
						</script>

					</form>

				</div>
			</div>
		</div>

	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/common/goods.js"></script>
</body>
</html>
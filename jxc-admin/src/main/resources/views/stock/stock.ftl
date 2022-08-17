<!DOCTYPE html>
<html>
<head>
	<title>期初库存</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 45%" >
			<fieldset class="layui-elem-field layui-field-title" >
				<legend style="font-size: 15px">商品信息</legend>
			</fieldset>
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<div class="layui-inline">
									<div class="layui-input-inline">
										<input type="text" name="goodsName"
											   class="layui-input
					searchVal" placeholder="商品名称" />
									</div>
									<a class="layui-btn search_btn" data-type="reload"><i
												class="layui-icon">&#xe615;</i> 搜索</a>
								</div>
							</form>
						</blockquote>
						<table id="goodsList01" class="layui-table"  lay-filter="goods01"></table>


						<script id="goodsListBar01" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">添加商品到仓库</a>
						</script>
					</form>
				</div>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 55%">
			<fieldset class="layui-elem-field layui-field-title" >
				<legend style="font-size: 15px">期初库存</legend>
			</fieldset>
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >

						<table id="goodsList02" class="layui-table"  lay-filter="goods02"></table>

						<#--操作-->
						<script id="goodsListBar02" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">修改库存或成本</a>
							<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
						</script>
					</form>

				</div>
			</div>
		</div>

	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/stock/stock.js"></script>
</body>
</html>
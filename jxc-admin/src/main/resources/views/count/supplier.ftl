<!DOCTYPE html>
<html>
<head>
	<title>供应商统计</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
	<div class="layui-row">
				<form class="layui-form" >
					<blockquote class="layui-elem-quote quoteBox">
						<form class="layui-form">
							<fieldset class="layui-elem-field site-demo-button" >
								<legend>供应商统计</legend>
								<br/>
								<div class="layui-row">
									<div class="layui-col-xs4">
										<label class="layui-form-label">开始日期</label>
										<div class="layui-input-block">
											<input type="text" name="startDate" id="startDate" lay-verify="startDate"
												   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
										</div>
									</div>

									<div class="layui-col-xs4">
										<label class="layui-form-label">结束日期</label>
										<div class="layui-input-block">
											<input type="text" name="endDate" id="endDate" lay-verify="endDate"
												   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
										</div>
									</div>
									<div class="layui-col-xs4">
										<label class="layui-form-label">供应商</label>
										<div class="layui-input-block">
											<select id="supplierId" name="supplierId"  >
												<option value="" >请选择</option>
											</select>
										</div>
									</div>
								</div>
								<br/>
								<div class="layui-row">
									<div class="layui-col-xs4">
										<label class="layui-form-label">是否付款</label>
										<div class="layui-input-block">
											<select id="state" name="state"   class="select">
												<option value="" >全部</option>
												<option value="1" >已付</option>
												<option value="0" >未付</option>
											</select>
										</div>
									</div>
									<div class="layui-col-xs4">
										<label class="layui-form-label"></label>
										<a class="layui-btn search_btn" data-type="reload"><i
													class="layui-icon">&#xe615;</i> 搜索</a>
									</div>
								</div>
							</fieldset>
						</form>
					</blockquote>
					<table id="supplierList" class="layui-table"  lay-filter="supplierList"></table>


					<script id="purchaseListBar" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
						{{#  if(d.state ==0){ }}
							<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">结算</a>
						{{#  } else { }}

						{{#  } }}
					</script>
				</form>
	</div>


	<div class="layui-row">
		<fieldset class="layui-elem-field site-demo-button" >
			<legend>商品信息</legend>
			<table id="listGoods" class="layui-table"  lay-filter="listGoods"></table>
		</fieldset>
	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/count/supplier.js"></script>
</body>
</html>
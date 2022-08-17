<!DOCTYPE html>
<html>
<head>
	<title>销售出库</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<input type="hidden" name="saleNumber" value="${saleNumber!}" >
	<input type="hidden" name="goodsJson" >
	<blockquote class="layui-elem-quote quoteBox">
		<fieldset class="layui-elem-field site-demo-button" >
			<legend>单号:${saleNumber!}</legend>
			<br/>
			<div class="layui-row">
				<div class="layui-col-xs3">
					<label class="layui-form-label">客户</label>
					<div class="layui-input-block">
						<select id="customerId" name="customerId"  >
							<option value="0" >请选择</option>
						</select>
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">应付金额</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input amountPayable"
							   name="amountPayable" id="amountPayable" >
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">实付金额</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input amountPaid"
							   name="amountPaid" id="amountPaid" >
					</div>
				</div>
				<div class="layui-col-xs3">
					<label class="layui-form-label">销售日期</label>
					<div class="layui-input-block">
						<input type="text" name="saleDate" id="saleDate" lay-verify="saleDate"
							   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
					</div>
				</div>
			</div>
			<br/>
			<div class="layui-row">
				<div class="layui-col-xs4">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<input type="text" class="layui-input remarks"
							   name="remarks" id="remarks" >
					</div>
				</div>
				<div class="layui-col-xs4">
					<label class="layui-form-label">是否付款</label>
					<div class="layui-input-block">
						<select id="state" name="state"   class="select">
							<option value="1" >已付</option>
							<option value="0" >未付</option>
						</select>
					</div>
				</div>
				<div class="layui-col-xs4">
					<label class="layui-form-label"></label>
					<button class="layui-btn layui-btn-lg" lay-submit=""
							lay-filter="addSaleList">保存
					</button>
				</div>

			</div>
		</fieldset>
	</blockquote>
	<table id="saleList" class="layui-table"  lay-filter="sales"></table>

	<#--操作-->
	<script id="goodsListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>


	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
				<i class="layui-icon">&#xe608;</i>
				添加
			</a>
		</div>
	</script>
</form>
<script type="text/javascript" src="${ctx.contextPath}/js/sale/sale.js"></script>

</body>
</html>
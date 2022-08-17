<!DOCTYPE html>
<html>
<head>
	<title>销售单据查询</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 60%" >
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>进货单据查询</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">单据号</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input saleNumber"
													   name="saleNumber" id="saleNumber" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">客户</label>
											<div class="layui-input-block">
												<select id="customerId" name="customerId"  >
													<option value="" >请选择</option>
												</select>
											</div>
										</div>
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
									</div>
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
											<label class="layui-form-label"></label>
											<a class="layui-btn search_btn" data-type="reload"><i
														class="layui-icon">&#xe615;</i> 搜索</a>
										</div>
									</div>
								</fieldset>
							</form>
						</blockquote>
						<table id="saleList" class="layui-table"  lay-filter="saleList"></table>


						<script id="purchaseListBar" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
							<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
						</script>
					</form>
				</div>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 40%">

			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>销售单商品信息</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">销售单号</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input saleNumber_"
													   name="saleNumber_" id="saleNumber_" readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">客户</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input customerName"
													   name="customerName_" id="customerName_"  readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">销售金额</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input purchaseNumber"
													   name="amountPayable_" id="amountPayable_" readonly="readonly" >
											</div>
										</div>
									</div>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">支付状态</label>
											<div class="layui-input-block">
												<input type="state_" id="state_"  class="layui-input" readonly="readonly" >
											</div>
										</div>

										<div class="layui-col-xs4">
											<label class="layui-form-label">操作员</label>
											<div class="layui-input-block">
												<input type="text"  name="userName_" id="userName_" class="layui-input" readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label"></label>
											<a class="layui-btn search_btn02" data-type="reload"><i
														class="layui-icon">&#xe615;</i> 重置</a>
										</div>
									</div>
								</fieldset>
							</form>
						</blockquote>
						<table id="saleListGoods" class="layui-table"  lay-filter="saleListGoods"></table>
					</form>

				</div>
			</div>
		</div>

	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/sale/sale.search.js"></script>
</body>
</html>
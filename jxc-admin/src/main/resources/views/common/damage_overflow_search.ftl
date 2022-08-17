<!DOCTYPE html>
<html>
<head>
	<title>报损报溢查询</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<!-- 左树 -->
		<div class="layui-col-sm12 layui-col-md4 layui-col-lg3" style="width: 55%" >
			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>报损报溢查询</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs3">
											<label class="layui-form-label">开始日期</label>
											<div class="layui-input-block">
												<input type="text" name="startDate" id="startDate" lay-verify="startDate"
													   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
											</div>
										</div>

										<div class="layui-col-xs3">
											<label class="layui-form-label">结束日期</label>
											<div class="layui-input-block">
												<input type="text" name="endDate" id="endDate" lay-verify="endDate"
													   placeholder="yyyy-MM-dd" autocomplete="off" readonly="readonly" class="layui-input">
											</div>
										</div>
										<div class="layui-col-xs3">
											<label class="layui-form-label">单据类型</label>
											<div class="layui-input-block">
												<select id="type" name="type"  >
													<!--
													   默认 报损单数据
													-->
													<option value="1" selected="selected">报损单</option>
													<option value="2" >报溢单</option>
												</select>
											</div>
										</div>
										<div class="layui-col-xs3">
											<label class="layui-form-label"></label>
											<a class="layui-btn search_btn" data-type="reload"><i
														class="layui-icon">&#xe615;</i> 搜索</a>
										</div>
									</div>
								</fieldset>
							</form>
						</blockquote>
						<table id="leftList" class="layui-table"  lay-filter="leftList"></table>


						<script id="leftListBar" type="text/html">
							<a class="layui-btn layui-btn-xs" id="edit" lay-event="search">货单</a>
							<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
						</script>
					</form>
				</div>
			</div>
		</div>
		<!-- 右表 -->
		<div class="layui-col-sm12 layui-col-md8 layui-col-lg9" style="width: 45%">

			<div class="layui-card">
				<div class="layui-card-body">
					<form class="layui-form" >
						<blockquote class="layui-elem-quote quoteBox">
							<form class="layui-form">
								<fieldset class="layui-elem-field site-demo-button" >
									<legend>报损单商品信息</legend>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">单号</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input number_"
													   name="number_" id="number_" readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">日期</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input date_"
													   name="date_" id="date_"  readonly="readonly" >
											</div>
										</div>
										<div class="layui-col-xs4">
											<label class="layui-form-label">类型</label>
											<div class="layui-input-block">
												<input type="text" class="layui-input typeName"
													   name="typeName_" id="typeName_" readonly="readonly" >
											</div>
										</div>
									</div>
									<br/>
									<div class="layui-row">
										<div class="layui-col-xs4">
											<label class="layui-form-label">操作员</label>
											<div class="layui-input-block">
												<input type="userName_" id="userName_"  class="layui-input" readonly="readonly" >
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
						<table id="rightList" class="layui-table"  lay-filter="rightList"></table>
					</form>

				</div>
			</div>
		</div>

	</div>
</div>








<script type="text/javascript" src="${ctx.contextPath}/js/common/damage.overflow.search.js"></script>
</body>
</html>
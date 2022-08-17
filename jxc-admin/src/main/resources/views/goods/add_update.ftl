<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(goods.id)!}"/>
    <input name="goodsUnit" type="hidden" value="${(goods.unit)!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品类别</label>
        <div class="layui-input-block">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="typeName"
                           class="layui-input
					searchVal" placeholder="商品类别" readonly="readonly"  value="${(goodsType.name)!""}"/>
                    <input type="hidden" name="typeId" value="${(goodsType.id)!""}" />
                </div>
                <a class="layui-btn search_btn" data-type="reload" href="javascript:void(0)" id="reloadGoodsType"><i
                            class="layui-icon">&#xe615;</i> 选择</a>
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input userName"
                   name="name" id="trueName" value="${(goods.name)!}" placeholder="请输入商品名称">
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input typeId"
                       lay-verify="required" name="model" id="model" value="${(goods.model)!""}"
                       placeholder="商品型号">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">采购价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="purchasingPrice" id="purchasingPrice" value="${(goods.purchasingPrice)!}"
                       placeholder="商品采购价">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">销售价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       lay-verify="required" name="sellingPrice" id="sellingPrice" value="${(goods.sellingPrice)!""}"
                       placeholder="商品销售价">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">库存下限</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input minNum"
                       name="minNum" id="minNum" value="${(goods.minNum)!}"
                       placeholder="库存下限">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品单位</label>
            <div class="layui-input-block">
                <select id="unit" name="unit" lay-verify="required"  class="select">
                    <option value="0" >请选择</option>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">生产厂商</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input producer"
                       name="producer" id="producer" value="${(goods.producer)!}" placeholder="请输入商品生产厂商">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="商品备注" class="layui-textarea" name="remarks">${(goods.remarks)!}</textarea>
        </div>
    </div>


    <br/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="addOrUpdateGoods">保存
            </button>
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="next">保存并新增下一商品
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>


<script type="text/javascript" src="${ctx.contextPath}/js/goods/add.update.js"></script>
</body>
</html>
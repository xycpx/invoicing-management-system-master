<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
    <link rel="stylesheet" href="${ctx.contextPath}/js/zTree_v3-3.5.32/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx.contextPath}/lib/jquery-3.4.1/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/js/zTree_v3-3.5.32/js/jquery.ztree.core.js"></script>
</head>
<body class="childrenBody">
    <fieldset class="layui-elem-field site-demo-button" >
        <legend>商品信息</legend>
        <br/>
    <input name="id" type="hidden" value="${(goods.id)!}"/>
    <input name="typeId" type="hidden" value="${(goods.typeId)!}"/>
    <input name="flag" type="hidden" value="${flag!}"/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品名</label>
        <div class="layui-input-block">
            <input type="text" class="layui-input name" readonly="readonly"
                   name="name" id="name" value="${(goods.name)!}">
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                        name="code" id="code" readonly="readonly" value="${(goods.code)!""}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品类别</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input minNum" readonly="readonly"
                       name="typeName" id="typeName" value="${(goods.typeName)!}">
            </div>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input model" readonly="readonly"
                        name="model" id="model" value="${(goods.model)!""}"
                       placeholder="商品型号">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品单位</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input unitName"  readonly="readonly"
                       name="unitName" id="unitName" value="${(goods.unitName)!}">
            </div>
        </div>
    </div>
    <br/>

    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">上次进价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="lastPurchasingPrice" id="lastPurchasingPrice" value="${(goods.lastPurchasingPrice)!""}"
                       placeholder="商品销售价">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">当前库存</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input inventoryQuantity" readonly="readonly"
                       name="inventoryQuantity" id="inventoryQuantity" value="${(goods.inventoryQuantity)!}">
            </div>
        </div>
    </div>
    </fieldset>
    <hr/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">单价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                        name="price" id="price"
                       value="${(goods.lastPurchasingPrice)!""}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-block">
                <input type="number" class="layui-input inventoryQuantity"
                       name="num" id="num" value="${(goods.num)!"0"}"required="required"min="0" max="100">
            </div>
        </div>
    </div>


    <br/>
    <br/>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" id="select">选择
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>


<script type="text/javascript" src="${ctx.contextPath}/js/common/goods.add.update.js"></script>
</body>
</html>
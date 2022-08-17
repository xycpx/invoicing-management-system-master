<!DOCTYPE html>
<html>
<head>
    <#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input name="id" type="hidden" value="${(goods.id)!}"/>
    <fieldset class="layui-elem-field layui-field-title" >
        <legend style="font-size: 15px">商品信息</legend>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品名</label>
            <div class="layui-input-block">
                <input type="text" name="name" class="layui-input name"
                       readonly="readonly" id="name" value="${(goods.name)!""}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品编号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input code"
                       readonly="readonly" name="code" id="code" value="${(goods.code)!}">
            </div>
        </div>
    </div>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">商品型号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input sellingPrice"
                       readonly="readonly" name="model" id="model" value="${(goods.model)!""}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">成本价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="purchasingPrice000" id="purchasingPrice" readonly="readonly" value="${(goods.purchasingPrice)!}">
            </div>
        </div>
    </div>
    </fieldset>
    <br/>
    <div class="layui-row">
        <div class="layui-col-xs6">
            <label class="layui-form-label">期初数量</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input inventoryQuantity"
                       name="inventoryQuantity" id="inventoryQuantity"  lay-verify="required" placeholder="请输入库存数"
                       value="${(goods.inventoryQuantity)!}">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">成本价</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input purchasingPrice"
                       name="purchasingPrice" id="purchasingPrice" value="${(goods.purchasingPrice)!}" placeholder="请指定成本价">
            </div>
        </div>
    </div>

    <br/>
    <br/>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit=""
                    lay-filter="updateGoods">入库
            </button>
            <a class="layui-btn layui-btn-lg layui-btn-normal"  id="closeDlg" href="javascript:void(0)">取消</a>
        </div>
    </div>
</form>


<script type="text/javascript" src="${ctx.contextPath}/js/stock/goods.update.js"></script>
</body>
</html>
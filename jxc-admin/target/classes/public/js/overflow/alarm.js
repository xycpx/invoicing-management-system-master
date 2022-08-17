layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;




     table.render({
        elem: '#goodsList',
        url : ctx+'/common/listAlarm',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "goodsListTable",
        cols : [[
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {field: 'lastPurchasingPrice', title: '采购价(￥)', minWidth:100, align:'center'},
            {field: 'sellingPrice', title: '销售价(￥)', minWidth:100, align:'center'},
            {field: 'inventoryQuantity', title: '当前库存', minWidth:100, align:'center'},
            {field: 'minNum', title: '库存下限', minWidth:100, align:'center'},
            {field: 'producer', title: '生产厂商', align:'center',minWidth:150}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("goodsListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                goodsName: $("input[name='goodsName']").val()
            }
        })
    });





});

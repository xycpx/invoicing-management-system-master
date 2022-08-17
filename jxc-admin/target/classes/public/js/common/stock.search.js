layui.use(['table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;




    $.ajax({
        type:"post",
        url:ctx+"/goodsType/queryAllGoodsTypes",
        dataType:"json",
        success:function (data) {
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                view:{
                    showLine: false
                },
                callback: {
                    onClick: zTreeOnClick
                }
            };
            $.fn.zTree.init($("#goodsTypeTree"), setting, data);
        }
    })

    function zTreeOnClick(event, treeId, treeNode) {
        // 获取店家节点对应类型id
        var typeId =  treeNode.id;
        table.reload("goodsListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                goodsName: $("input[name='goodsName']").val(),
                typeId:typeId
            }
        })
        // 设置商品类别查询条件到隐藏域
        $("input[name='typeId']").val(typeId);
    };


    var  tableIns = table.render({
        elem: '#goodsList',
        url : ctx+'/common/stockList',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        toolbar: "#toolbarDemo",
        id : "goodsListTable",
        cols : [[
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {field: 'producer', title: '生产厂商', align:'center',minWidth:150},
            {field: 'inventoryQuantity', title: '库存量', minWidth:100, align:'center'},
            {field: 'saleTotal', title: '销售总数', minWidth:100, align:'center'},
            {field: 'lastPurchasingPrice', title: '上次进价(￥)', minWidth:100, align:'center'},
            {field: 'purchasingPrice', title: '成本均价(￥)', minWidth:100, align:'center'},
            {field: 'sellingPrice', title: '销售价(￥)', minWidth:100, align:'center'},
            {field: 'amount', title: '库存总金额(￥)', minWidth:100, align:'center',templet: function (d) {
                    return (d.inventoryQuantity * d.purchasingPrice).toFixed(2);
                }
             }
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

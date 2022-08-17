layui.use(['element','table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;



    //商品列表展示
    var  tableIns01 = table.render({
        elem: '#goodsList01',
        url : ctx+'/stock/listNoInventoryQuantity',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        //toolbar: "#toolbarDemo01",
        id : "goodsListTable01",
        cols : [[
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {title: '操作', minWidth:180, templet:'#goodsListBar01',fixed:"right",align:"center"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("goodsListTable01",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                goodsName: $("input[name='goodsName']").val()
            }
        })
    });

    /**
     * 行监听
     */
    table.on("tool(goods01)", function(obj) {
        var layEvent = obj.event;
        if (layEvent === "edit") {
            openUpdateGoodsInfoDialog(obj.data.id);
        }
    })

    function  openUpdateGoodsInfoDialog(gid){
        var url  =  ctx+"/stock/toUpdateGoodsInfoPage?gid="+gid;
        layui.layer.open({
            title : "期初库存-商品入库",
            type : 2,
            area:["600px","400px"],
            maxmin:true,
            content : url
        });
    }


    //商品列表展示
    var  tableIns02 = table.render({
        elem: '#goodsList02',
        url : ctx+'/stock/listHasInventoryQuantity',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        //toolbar: "#toolbarDemo02",
        id : "goodsListTable02",
        cols : [[
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:50, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:50, align:'center'},
            {field: 'unitName', title: '单位', align:'center'},
            {field: 'inventoryQuantity', title: '库存', minWidth:50, align:'center'},
            {field: 'purchasingPrice', title: '成本价(￥)', minWidth:100, align:'center'},
            {field: 'amount', title: '库存金额(￥)', minWidth:120, align:'center',templet:function (d){
                    return (d.inventoryQuantity * d.purchasingPrice).toFixed(2);
                }},
            {title: '操作', minWidth:180, templet:'#goodsListBar02',fixed:"right",align:"center"}
        ]]
    });



    /**
     * 行监听
     */
    table.on("tool(goods02)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openUpdateGoodsInfoDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前商品？', {icon: 3, title: "期初库存"}, function (index) {
                $.post(ctx+"/stock/deleteStock",{id:obj.data.id},function (data) {
                        if(data.code==200){
                            layer.msg("操作成功！");
                            tableIns02.reload();
                            tableIns01.reload();
                        }else{
                            layer.msg(data.message, {icon: 5});
                        }
                });
            })
        }
    });




});

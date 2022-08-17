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


    //角色列表展示
    var  tableIns = table.render({
        elem: '#goodsList',
        url : ctx+'/goods/list',
        cellMinWidth : 95,
        page : true,
        height : "full-75",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "goodsListTable",
        cols : [[
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {field: 'lastPurchasingPrice', title: '上次进价', minWidth:100, align:'center'},
            {field: 'purchasingPrice', title: '成本价', minWidth:100, align:'center'},
            {field: 'inventoryQuantity', title: '当前库存', minWidth:100, align:'center'},
            {title: '操作', minWidth:150, templet:'#goodsListBar',fixed:"right",align:"center"}
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



    /**
     * 行监听
     */
    table.on("tool(goods)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "add") {
            select(obj.data.id);
        }
    });



    function select(gid){
        var url  =  ctx+"/common/toAddGoodsInfoPage?gid="+gid;
        layui.layer.open({
            title : "商品信息设置",
            type : 2,
            area:["800px","550px"],
            maxmin:true,
            content : url
        });
    }



});

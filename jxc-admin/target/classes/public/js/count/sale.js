layui.use(['laydate','table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;


    laydate.render({
        elem: '#startDate'
    });
    laydate.render({
        elem: '#endDate'
    });

    var zTreeObj;
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
            zTreeObj=$.fn.zTree.init($("#goodsTypeTree"), setting, data);
        }
    })

    function zTreeOnClick(event, treeId, treeNode) {
        // 设置商品类别查询条件到隐藏域
        $("input[name='typeId']").val(treeNode.id);
        renderTableData();
    };


    table.render({
        elem: '#saleList',
        cellMinWidth : 95,
        page : true,
        height : "auto",
        url:ctx+"/sale/countSale",
        limits : [10,15,20,25],
        limit : 20,
        id : "saleListTable",
        cols : [[
            {field: 'number', title: '单号', minWidth:50, align:"center"},
            {field: 'type', title: '类型', minWidth:100, align:'center',templet: function (d){
                return "销售";
                }},
            {field: 'date', title: '日期', minWidth:100, align:'center'},
            {field: 'name', title: '客户', minWidth:100, align:'center'},
            {field: 'code', title: '商品编码', minWidth: 50, align: "center"},
            {field: 'goodsName', title: '商品名称', minWidth: 50, align: "center"},
            {field: 'model', title: '商品型号', minWidth: 100, align: 'center'},
            {field: 'typeName', title: '类别', minWidth: 100, align: 'center'},
            {field: 'price', title: '单价', minWidth: 100, align: 'center'},
            {field: 'num', title: '数量', minWidth: 100, align: 'center'},
            {field: 'unitName', title: '单位', minWidth: 100, align: 'center'},
            {field: 'total', title: '总金额', minWidth: 100, align: 'center'}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function(){
        renderTableData()
    });

    function renderTableData(){
        table.reload("saleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                goodsName: $("input[name='goodsName']").val(),
                startDate: $("input[name='startDate']").val(),
                endDate: $("input[name='endDate']").val(),
                typeId:$("input[name='typeId']").val()
            }
        })
    }

});

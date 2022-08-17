layui.use(['laydate','table','layer'],function() {
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        form = layui.form
    table = layui.table;

    laydate.render({
        elem: '#startDate'
    });
    laydate.render({
        elem: '#endDate'
    });


    $.ajax({
        type: "post",
        url: ctx + "/supplier/allGoodsSuppliers",
        success: function (data) {
            if (data !== null) {
                $.each(data, function (index, item) {
                    $("#supplierId").append("<option value='" + item.id + "' >" + item.name + "</option>");
                });
            }
            //重新渲染
            form.render("select")
        }
    })


     table.render({
        elem: '#supplierList',
        height: 400,
        page : true,
        limits : [10,15,20,25],
        id: "supplierListTable",
        cols: [[
            {field: 'supplierName', title: '供应商', minWidth: 100, align: 'center'},
            {field: 'purchaseDate', title: '进货日期', minWidth: 50, align: "center"},
            {field: 'purchaseNumber', title: '单号', minWidth: 50, align: "center"},
            {field: 'amountPayable', title: '总金额', minWidth: 100, align: 'center'},
            {field: 'state', title: '支付状态', minWidth: 100, align: 'center',templet:function (d){
                if(d.state==0){
                    return "未支付"
                }else if(d.state==1){
                    return "已支付"
                }
                }},
            {field: 'userName', title: '操作员', minWidth: 100, align: 'center'},
            {field: 'remarks', title: '备注', minWidth: 100, align: 'center'},
            {title: '操作', minWidth: 150, templet: '#purchaseListBar', fixed: "right", align: "center"}
        ]],
        data:[]
    });

    table.render({
        elem: '#listGoods',
        //height: "full-125",
        height: 300,
        page : true,
        limits : [10,15,20,25],
        id: "listGoodsTable",
        cols: [[
            {field: 'code', title: '商品编码', minWidth: 50, align: "center"},
            {field: 'name', title: '商品名称', minWidth: 50, align: "center"},
            {field: 'model', title: '商品型号', minWidth: 100, align: 'center'},
            {field: 'price', title: '单价', minWidth: 100, align: 'center'},
            {field: 'num', title: '数量', minWidth: 100, align: 'center'},
            {field: 'unit', title: '单位', minWidth: 100, align: 'center'},
            {field: 'total', title: '总金额', minWidth: 100, align: 'center'}
        ]],
        data:[]
    });


    // 多条件搜索
    $(".search_btn").on("click",function(){
        search();
    });


    function search(){
        var startDate =  $("input[name='startDate']").val();
        var endDate = $("input[name='endDate']").val();
        var supplierId = $('select[name="supplierId"] option:selected').val();
        var state = $('select[name="state"] option:selected').val();
        $.ajax({
            type:"post",
            url:ctx+"/purchase/list",
            data:{
                startDate: startDate,
                endDate: endDate,
                state:state,
                supplierId:supplierId
            },
            success:function (datas){
                table.reload("supplierListTable",{
                    data:datas.data
                })
            }
        })

        table.reload("listGoodsTable",{
            data:[]
        })
    }


    /**
     * 行监听
     */
    table.on("tool(supplierList)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "search") {
            $.ajax({
                type:"post",
                url:ctx+"/purchaseListGoods/list",
                data:{
                    purchaseListId:obj.data.id
                },
                success:function (datas){
                    table.reload("listGoodsTable",{
                        data:datas.data
                    })
                }
            })
        }else if(layEvent === "del"){
            layer.confirm('确定支付？', {icon: 3, title: "供应商统计"}, function (index) {
                $.post(ctx+"/purchase/update",{id:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        search();
                    }else{
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });


})


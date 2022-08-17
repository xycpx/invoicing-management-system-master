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
        url: ctx + "/customer/allCustomers",
        success: function (data) {
            if (data !== null) {
                $.each(data, function (index, item) {
                    $("#customerId").append("<option value='" + item.id + "' >" + item.name + "</option>");
                });
            }
            //重新渲染
            form.render("select")
        }
    })


    var tableIns = table.render({
        elem: '#saleList',
        url: ctx + '/sale/list',
        height: "full-125",
        page : true,
        limits : [10,15,20,25],
        id: "saleListTable",
        cols: [[
            {field: "id", title: '编号', fixed: "true", width: 80},
            {field: 'saleNumber', title: '销售单号', minWidth: 50, align: "center"},
            {field: 'saleDate', title: '销售日期', minWidth: 50, align: "center"},
            {field: 'customerName', title: '客户', minWidth: 100, align: 'center'},
            {field: 'amountPayable', title: '销售金额', minWidth: 100, align: 'center'},
            {field: 'userName', title: '操作员', minWidth: 100, align: 'center'},
            {field: 'remarks', title: '备注', minWidth: 100, align: 'center'},
            {title: '操作', minWidth: 150, templet: '#purchaseListBar', fixed: "right", align: "center"}
        ]]
    });


    // 多条件搜索
    $(".search_btn").on("click",function(){
        table.reload("saleListTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                // 单据号
                saleNumber: $("input[name='saleNumber']").val(),
                customerId: $('select[name="customerId"] option:selected').val(),
                state: $('select[name="state"] option:selected').val(),
                startDate: $("input[name='startDate']").val(),
                endDate: $("input[name='endDate']").val(),
            }
        })
    });


    /**
     * 行监听
     */
    table.on("tool(saleList)", function (obj) {
        var layEvent = obj.event;
        if (layEvent === "search") {
            /**
             * 填充右侧输入框数据
             */
            $("#saleNumber_").val(obj.data.saleNumber);
            $("#customerName_").val(obj.data.customerName);
            $("#amountPayable_").val(obj.data.amountPayable);
            if(obj.data.state==1){
                $("#state_").val("已支付");
            }else{
                $("#state_").val("未支付");
            }

            $("#userName_").val(obj.data.userName);
            table.reload("saleListGoodsTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    saleListId:obj.data.id
                }
            })
        }else if(layEvent === "del"){
            layer.confirm('确定删除当前进货单？', {icon: 3, title: "销售单管理"}, function (index) {
                $.post(ctx+"/sale/delete",{id:obj.data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        tableIns.reload();
                        resetListGoodsTab();
                    }else{
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });



    var tableIns02 = table.render({
        elem: '#saleListGoods',
        url: ctx + '/saleListGoods/list',
        height: "full-125",
        page : true,
        limits : [10,15,20,25],
        id: "saleListGoodsTable",
        cols: [[
            {field: 'code', title: '商品编码', minWidth: 50, align: "center"},
            {field: 'name', title: '商品名称', minWidth: 50, align: "center"},
            {field: 'model', title: '商品型号', minWidth: 100, align: 'center'},
            {field: 'price', title: '单价', minWidth: 100, align: 'center'},
            {field: 'num', title: '数量', minWidth: 100, align: 'center'},
            {field: 'unit', title: '单位', minWidth: 100, align: 'center'},
            {field: 'total', title: '总金额', minWidth: 100, align: 'center'}
        ]]
    });

    // 重置
    $(".search_btn02").on("click",function(){
        resetListGoodsTab();
    });

    function resetListGoodsTab(){
        $("#saleNumber_").val("");
        $("#customerName_").val("");
        $("#amountPayable_").val("");
        $("#state_").val("");
        $("#userName_").val("");
        table.reload("saleListGoodsTable",{
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {
                saleListId:""
            }
        })
    }

})


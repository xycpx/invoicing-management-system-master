layui.use(['laydate','table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        form = layui.form
        table = layui.table;

    laydate.render({
        elem: '#customerReturnDate'
    });


    $.ajax({
        type:"post",
        url:ctx+"/customer/allCustomers",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    $("#customerId").append("<option value='"+item.id+"' >"+item.name+"</option>");
                });
            }
            //重新渲染
            form.render("select")
        }
    })




     var tableIns =table.render({
        elem: '#customerReturnList',
        height : "full-125",
        toolbar: "#toolbarDemo",
        id : "customerReturnListTable",
        cols : [[
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'price', title: '单价', minWidth:100, align:'center'},
            {field: 'num', title: '数量', minWidth:100, align:'center'},
            {field: 'unit', title: '单位', minWidth:100, align:'center'},
            {field: 'total', title: '总金额', minWidth:100, align:'center'},
            {title: '操作', minWidth:150, templet:'#goodsListBar',fixed:"right",align:"center"}
        ]],
        data:[]
    });

    //头工具栏事件
    table.on('toolbar(customerReturns)', function(obj){
        switch(obj.event){
            case "add":
                openGoodsDialog();
                break;

        };
    });

    function openGoodsDialog(){
        var url  =  ctx+"/common/toSelectGoodsPage";
        var title="客户退货商品选择";
        layui.layer.open({
            title : title,
            type : 2,
            area:["950px","600px"],
            maxmin:true,
            content : url
        });
    }

    /**
     * 行监听
     */
    table.on("tool(customerReturns)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openUpdateGoodsInfoDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定移除当前商品？', {icon: 3, title: "商品选择"}, function (index) {
                datas.forEach((item,i) => {
                    if(item.id === obj.data.id){
                        datas.splice(i,1);
                    }
                });
                reloadTableData();
                top.layer.close(index);
            })
        }
    });


    function openUpdateGoodsInfoDialog(id){
        var goods;
        for (let i = 0; i < datas.length; i++) {
            if(datas[i].id==id){
                goods= datas[i];
                break;
            }
        }
        var url  =  ctx+"/common/toUpdateGoodsInfoPage?id="
            +goods.goodsId+"&price="+goods.price+"&num="+goods.num+"&total="+goods.total;
        layui.layer.open({
            title : "进货入库商品更新",
            type : 2,
            area:["800px","550px"],
            maxmin:true,
            content : url
        });
    }


    form.on("submit(addCustomerReturnList)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        $.post(ctx + "/customerReturn/save", data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                   window.location.href=ctx+"/customerReturn/index";
                }, 500);
            } else {
                layer.msg(
                    res.message, {
                        icon: 5
                    }
                );
            }
        });
        return false;
    });



});

var datas=[];
 function getGoodsSelectInfo(gid,gname,code,price,num,model,unit,typeId,flag){
     if(flag){
         // 添加操作
         datas.push({
             "goodsId":gid,
             "code":code,
             "name":gname,
             "price":price,
             "num":num,
             "model":model,
             "unit":unit,
             "typeId":typeId,
             "total":price*num
         });
     }else{
         // 更新操作
         datas.forEach((item,i) => {
             if(item.goodsId == gid){
                 // 修改价格、数量与总金额即可
                 alert("执行修改");
                 item.price=price;
                 item.num=num;
                 item.total=price*num;
             }
         });
     }

     /**
      * 重载表格数据
      */
     reloadTableData();
 }


 function reloadTableData(){
     layui.table.reload("customerReturnListTable",{
         data:datas
     })
     var total=0;
     for (let i = 0; i < datas.length; i++) {
         total = total + datas[i].total;
     }
     layui.jquery("#amountPayable").val(total);
     layui.jquery("#amountPaid").val(total);
     // 设置选择商品json数据到隐藏域 便于后续表单提交
     layui.jquery("input[name='goodsJson']").val(JSON.stringify(datas));
 }



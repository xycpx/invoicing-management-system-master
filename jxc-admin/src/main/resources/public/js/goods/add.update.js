layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;


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
                }/*,
                callback: {
                    onClick: zTreeOnClick
                }*/
            };
            $.fn.zTree.init($("#treeDemo"), setting, data);
        }
    })


    /**
     * 商品单位下拉框展示
     */
    $.ajax({
        type:"post",
        url:ctx+"/goodsUnit/allGoodsUnits",
        success:function (data){
            if (data!== null) {
                $.each(data, function(index, item) {
                    //alert($("input[name='goodsUnit']").val()==item.id));
                    if($("input[name='goodsUnit']").val()==item.id){
                        $("#unit").append("<option value='"+item.id+"' selected='selected'>"+item.name+"</option>");
                    }else{
                        $("#unit").append("<option value='"+item.id+"' >"+item.name+"</option>");
                    }

                });
            }
            //重新渲染
            form.render("select")
        }
    })




    form.on("submit(addOrUpdateGoods)", function (data) {
        var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        //弹出loading
        var url=ctx + "/goods/save";
        if($("input[name='id']").val()){
            url=ctx + "/goods/update";
        }
        $.post(url, data.field, function (res) {
            if (res.code == 200) {
                setTimeout(function () {
                    top.layer.close(index);
                    top.layer.msg("操作成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
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


    $("#reloadGoodsType").click(function (){
        var url  =  ctx+"/goods/toGoodsTypePage?typeId="+$("input[name='typeId']").val();
        var title="商品管理-商品类别";
        layui.layer.open({
            title : title,
            type : 2,
            area:["600px","400px"],
            maxmin:true,
            content : url
        });
    })



    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })


});

/**
 * 子窗口调用方法  显示选中的商品类别
 * @param typeName
 * @param typeId
 */
function getVal(typeName,typeId){
    $("input[name='typeName']").val(typeName);
    $("input[name='typeId']").val(typeId);
}
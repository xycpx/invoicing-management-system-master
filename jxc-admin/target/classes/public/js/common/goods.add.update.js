layui.use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;



    $("#select").click(function (){
        var gid= $("input[name='id']").val();
        var gname = $("input[name='name']").val();
        var code = $("#code").val();
        var price=$("#price").val();
        var num =$("#num").val();
        var model=$("#model").val();
        var unit = $("#unitName").val();
        var typeId = $("input[name='typeId']").val();
        if($("input[name='flag']").val()==1){
            // 更新操作
            if(num==""||num==null) {
                num = 0;
            }
            parent.getGoodsSelectInfo(gid,gname,code,price,num,model,unit,typeId,false);
        }else{
            if(num==""||num==null){
                num=0;
            }
            // 添加操作
            parent.parent.getGoodsSelectInfo(gid,gname,code,price,num,model,unit,typeId,true);
        }

        parent.layer.closeAll();
        parent.parent.layer.closeAll();
    })

    $("#closeDlg").click(function (){
        // iframe 页面关闭 添加parent
        parent.layer.closeAll();
    })


});



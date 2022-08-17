var zTreeObj;
$(function () {
    loadGoodsTypeInfo();
});
function loadGoodsTypeInfo() {
    $.ajax({
        type:"post",
        url:ctx+"/goodsType/queryAllGoodsTypes?typeId="+$("input[name='typeId']").val(),
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
                    // showIcon: false
                },
                check: {
                    enable: true,
                    chkStyle: "radio",
                    radioType:"all",
                    chkboxType: { "Y": "p", "N": "ps" }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            zTreeObj=$.fn.zTree.init($("#goodsType"), setting, data);
        }
    })

}



function zTreeOnCheck(event, treeId, treeNode) {
   console.log(treeNode);
   if(treeNode.checked){
       // 如果节点被勾选 选择该节点
       parent.getVal(treeNode.name,treeNode.id);
   }else{
       parent.getVal("","",);
   }

}


$("#closeDlg").click(function (){
    // iframe 页面关闭 添加parent
    parent.layer.closeAll();
})


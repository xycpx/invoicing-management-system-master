layui.use(['element','table','layer'],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table,
        element = layui.element;




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
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "goodsListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: "id", title:'编号',fixed:"true", width:80},
            {field: 'code', title: '商品编码', minWidth:50, align:"center"},
            {field: 'name', title: '商品名称', minWidth:100, align:'center'},
            {field: 'model', title: '商品型号', minWidth:100, align:'center'},
            {field: 'typeName', title: '商品类别', minWidth:100, align:'center'},
            {field: 'unitName', title: '单位', minWidth:100, align:'center'},
            {field: 'purchasingPrice', title: '采购价', minWidth:100, align:'center'},
            {field: 'sellingPrice', title: '出售价', minWidth:100, align:'center'},
            {field: 'minNum', title: '库存下限', minWidth:100, align:'center'},
            {field: 'producer', title: '生产厂商', align:'center',minWidth:150},
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




    //头工具栏事件
    table.on('toolbar(goods)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case "add":
                openAddOrUpdateGoodsDialog();
                break;
            case "goodsType":
                addGoodsTypeManagerTab();

        };
    });


    function addGoodsTypeManagerTab(){
        //新增一个Tab项
        window.parent.layui.element.tabAdd('bodyTab', {
            title: '新选项'+ (Math.random()*1000|0) //用于演示
            ,content: '内容'+ (Math.random()*1000|0)
            ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
        })
    }


    /**
     * 行监听
     */
    table.on("tool(goods)", function(obj){
        var layEvent = obj.event;
        if(layEvent === "edit") {
            openAddOrUpdateGoodsDialog(obj.data.id);
        }else if(layEvent === "del") {
            layer.confirm('确定删除当前商品？', {icon: 3, title: "商品管理"}, function (index) {
                $.post(ctx+"/goods/delete",{id:obj.data.id},function (data) {
                        if(data.code==200){
                            layer.msg("操作成功！");
                            tableIns.reload();
                        }else{
                            layer.msg(data.message, {icon: 5});
                        }
                });
            })
        }
    });


    // 打开添加页面
    function openAddOrUpdateGoodsDialog(uid){
        var url  =  ctx+"/goods/addOrUpdateGoodsPage";
        var title="商品管理-添加商品";
        if(uid){
            url = url+"?id="+uid;
            title="商品管理-更新商品";
        }else{
            if(null !=$("input[name='typeId']").val()){
                url=url+"?typeId="+$("input[name='typeId']").val();
            }
        }

        layui.layer.open({
            title : title,
            type : 2,
            area:["800px","550px"],
            maxmin:true,
            content : url
        });
    }


});

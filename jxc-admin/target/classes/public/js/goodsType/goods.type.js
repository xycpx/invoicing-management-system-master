layui.use(['table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var treeTable = layui.treetable;

    // 渲染表格
    treeTable.render({
        treeColIndex: 1,
        treeSpid: -1,
        treeIdName: 'id',
        treePidName: 'pId',
        elem: '#goods-type-table',
        url: ctx+'/goodsType/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        page: true,
        cols: [[
            {type: 'numbers'},
            {field: 'name', minWidth: 100, title: '类别名称'},
            {field: 'icon', width: 100,title: '图标'},
            {
                field: 'state', width: 80, align: 'center', templet: function (d) {
                    if (d.state ==1 ) {
                        return '<span class="layui-badge layui-bg-blue">父节点</span>';
                    }

                    if (d.state == 0) {
                        return '<span class="layui-badge layui-bg-gray">子节点</span>';
                    }
                }, title: '类型'
            },
            {templet: '#auth-state',  align: 'center', title: '操作'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });


    //监听工具条
    table.on('tool(goods-type-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
            openAddGoodsTypeDialog(data.id);
        }  else if (layEvent === 'del') {
            layer.confirm('确定删除当前类别？', {icon: 3, title: "商品类别管理"}, function (index) {
                $.post(ctx+"/goodsType/delete",{id:data.id},function (data) {
                    if(data.code==200){
                        layer.msg("操作成功！");
                        window.location.reload();
                    }else{
                        layer.msg(data.message, {icon: 5});
                    }
                });
            })
        }
    });

    table.on('toolbar(goods-type-table)', function(obj){
        switch(obj.event){
            case "expand":
                treeTable.expandAll('#goods-type-table');
                break;
            case "fold":
                treeTable.foldAll('#goods-type-table');
                break;
        };
    });


    // 打开添加菜单对话框
    function openAddGoodsTypeDialog(parentId){
        var url  =  ctx+"/goodsType/addGoodsTypePage?pId="+parentId;
        var title="类别添加";
        layui.layer.open({
            title : title,
            type : 2,
            area:["700px","450px"],
            maxmin:true,
            content : url
        });
    }


});
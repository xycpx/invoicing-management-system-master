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
        elem: '#menu-table',
        url: ctx+'/menu/list',
        toolbar: "#toolbarDemo",
        treeDefaultClose:true,
        cols: [[
            {type: 'numbers'},
            {field: 'name', minWidth: 100, title: '菜单名称'},
            {field: 'aclValue', width: 100,title: '权限码'},
            {field: 'url', width: 120,title: '菜单url'},
            {
                field: 'grade', width: 80, align: 'center', templet: function (d) {
                    if (d.grade == -1 ) {
                        return '<span class="layui-badge layui-bg-blue">根菜单</span>';
                    }
                    if (d.grade == 0 ) {
                        return '<span class="layui-badge layui-bg-blue">一级菜单</span>';
                    }
                    if(d.grade==1){
                        return '<span class="layui-badge-rim">二级菜单</span>';
                    }
                    if (d.grade == 2) {
                        return '<span class="layui-badge layui-bg-gray">三级菜单</span>';
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
    table.on('tool(menu-table)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'add') {
            if(data.grade==2){
                layer.msg("暂不支持四级菜单添加操作!");
                return;
            }
            openAddMenuDialog(data.grade+1,data.id);
        } else if (layEvent === 'edit') {
            // 记录修改
            openUpdateMenuDialog(data.id);
        } else if (layEvent === 'del') {
            layer.confirm('确定删除当前菜单？', {icon: 3, title: "菜单管理"}, function (index) {
                $.post(ctx+"/menu/delete",{id:data.id},function (data) {
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

    table.on('toolbar(menu-table)', function(obj){
        switch(obj.event){
            case "expand":
                treeTable.expandAll('#menu-table');
                break;
            case "fold":
                treeTable.foldAll('#menu-table');
                break;
            case "add":
                openAddMenuDialog(0,1);
                break;
        };
    });


    // 打开添加菜单对话框
    function openAddMenuDialog(grade,parentId){
        var grade=grade;
        var url  =  ctx+"/menu/addMenuPage?grade="+grade+"&pId="+parentId;
        var title="菜单添加";
        layui.layer.open({
            title : title,
            type : 2,
            area:["700px","450px"],
            maxmin:true,
            content : url
        });
    }

    function openUpdateMenuDialog(id){
        var url  =  ctx+"/menu/updateMenuPage?id="+id;
        var title="菜单更新";
        layui.layer.open({
            title : title,
            type : 2,
            area:["700px","450px"],
            maxmin:true,
            content : url
        });
    }
});
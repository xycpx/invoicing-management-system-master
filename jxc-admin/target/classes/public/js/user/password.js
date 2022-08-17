layui.use(['form','layuimini','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        layuimini = layui.layuimini,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    //监听提交
    form.on('submit(saveBtn)', function (data) {
        $.ajax({
            type:"post",
            url:ctx+"/user/updateUserPassword",
            data:{
                oldPassword:data.field.old_password,
                newPassword:data.field.new_password,
                confirmPassword:data.field.again_password
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg("密码修改成功,系统将在3秒后自动退出...", function () {
                        setTimeout(function () {
                            window.parent.location.href=ctx+"/signout";
                        },3000);
                    });
                }else{
                    layer.msg(data.message);
                }
            }
        });
        return false;
    });

});
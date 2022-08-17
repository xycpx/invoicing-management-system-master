layui.use(['form','jquery','jquery_cookie'], function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);


    $("#refreshCaptcha").click(function (){
        $(this).attr("src", "/image?"+Math.floor(Math.random() * 100));
    })



    // 进行登录操作
    form.on('submit(login)', function (data) {
        data = data.field;
        if ( data.userName =="undefined" || data.username =="" || data.username.trim()=="") {
            layer.msg('用户名不能为空');
            return false;
        }
        if ( data.password =="undefined" || data.password =="" || data.password.trim()=="")  {
            layer.msg('密码不能为空');
            return false;
        }
        if ( data.captcha =="undefined" || data.captcha =="" || data.captcha.trim()=="")  {
            layer.msg('验证码不能为空');
            return false;
        }
        $.ajax({
            type:"post",
            url:ctx+"/login",
            data:{
                userName:data.username,
                password:data.password,
                captchaCode:data.captcha,
                rememberMe:$("#rememberMe").is(":checked")
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.msg('登录成功', function () {
                        window.location.href=ctx+"/main";
                    });
                }else{
                    $("#refreshCaptcha").attr("src", "/image?"+Math.floor(Math.random() * 100));
                    layer.msg(data.message);
                }
            }
        });
        return false;
    });
});
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <#include "../common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">管理账号</label>
                <div class="layui-input-block">
                    <input type="text" name="userName" lay-verify="required"  class="layui-input" value="${(user.username)!'xxx'}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label ">备注名</label>
                <div class="layui-input-block">
                    <input type="text" name="bz"  placeholder="请输入备注名"
                           value="${(user.bz)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">真实姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="trueName"  placeholder="请输入真实姓名"  value="${(user.trueName)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="remarks"  placeholder="请输入备注信息"  value="${(user.bz)!}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="hidden" name="id"   value="${(user.id)!}" >
                    <button class="layui-btn" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx.contextPath}/js/user/setting.js"></script>
</body>
</html>
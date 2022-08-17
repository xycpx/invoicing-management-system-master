<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>进销存管理系统</title>
    <#include "common.ftl">
</head>
<body class="layui-layout-body layuimini-all">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header">
        <div class="layui-logo" align="left">
            <a href="" >
                <img src="images/logo.png" alt="logo" >
                <h1 style="margin: 0 0 0 3px">进销存管理系统</h1>
            </a>
        </div>
        <a>
            <div class="layuimini-tool"><i title="展开" class="fa fa-outdent" data-side-fold="1"></i></div>
        </a>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;" data-check-screen="full"><i class="fa fa-arrows-alt"></i></a>
            </li>
            <li class="layui-nav-item layuimini-setting">
                <a href="javascript:;">${(Session.SPRING_SECURITY_CONTEXT.authentication.principal.username)!'lzj'}</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/setting" data-title="基本资料" data-icon="fa fa-gears">基本资料</a>
                    </dd>
                    <dd>
                        <a href="javascript:;" data-iframe-tab="${ctx.contextPath}/user/password" data-title="修改密码" data-icon="fa fa-gears">修改密码</a>
                    </dd>
                    <dd>
                        <a href="${ctx.contextPath}/signout" class="login-out">退出登录</a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layuimini-select-bgcolor mobile layui-hide-xs" lay-unselect>
                <a href="javascript:;"></a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-left-menu">
                <ul class="layui-nav layui-nav-tree layui-left-nav-tree layui-this" id="currency">

                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 进货管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="purchase/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 进货入库</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="return/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 退货出库</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="purchase/searchPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 进货单据查询</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="return/searchPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 退货单据查询</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="common/toGoodsStockPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 当前库存查询</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 销售管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="sale/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 销售出库</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="customerReturn/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户退货</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="sale/searchPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 销售单据查询</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="customerReturn/searchPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户退货查询</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="common/toGoodsStockPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 当前库存查询</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 库存管理</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="damage/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 商品报损</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="overflow/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品报溢</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="common/alarmPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 库存报警</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="common/toDamageOverflowSearchPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 报损报溢查询</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="common/toGoodsStockPage" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 当前库存查询</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 统计报表</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="report/countSupplier" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 供应商统计</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="report/countCustomer" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户统计</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/countPurchase" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品采购统计</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/countSale" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品销售统计</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/countDaySale" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 日销售统计</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="report/countMonthSale" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 月销售统计</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item">
                        <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 基础资料</span> <span class="layui-nav-more"></span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="supplier/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 供应商管理</span></a>
                            </dd>

                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="customer/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 客户管理</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="goods/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品管理</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="goodsType/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 商品分类管理</span></a>
                            </dd>
                            <dd class="">
                                <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="stock/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 期初库存</span></a>
                            </dd>
                        </dl>
                    </li>
                   <@security.authorize access="hasAnyAuthority('10')">
                       <li class="layui-nav-item">
                           <a href="javascript:;" class="layui-menu-tips"><i class="fa fa-gears"></i><span class="layui-left-nav"> 系统设置</span> <span class="layui-nav-more"></span></a>
                           <dl class="layui-nav-child">
                               <@security.authorize access="hasAnyAuthority('1010')">
                                   <dd>
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-11" data-tab="user/index" target="_self"><i class="fa fa-user"></i><span class="layui-left-nav"> 用户管理</span></a>
                                   </dd>
                               </@security.authorize>

                               <@security.authorize access="hasAnyAuthority('1020')">
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-12" data-tab="role/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 角色管理</span></a>
                                   </dd>
                               </@security.authorize>
                               <@security.authorize access="hasAnyAuthority('1030')">
                                   <dd class="">
                                       <a href="javascript:;" class="layui-menu-tips" data-type="tabAdd" data-tab-mpi="m-p-i-13" data-tab="menu/index" target="_self"><i class="fa fa-tachometer"></i><span class="layui-left-nav"> 菜单管理</span></a>
                                   </dd>
                               </@security.authorize>
                           </dl>
                       </li>
                   </@security.authorize>


                    <span class="layui-nav-bar" style="top: 201px; height: 0px; opacity: 0;"></span>
                </ul>
        </div>
    </div>

    <div class="layui-body">
        <div class="layui-tab" lay-filter="layuiminiTab" id="top_tabs_box">
            <ul class="layui-tab-title" id="top_tabs">
                <li class="layui-this" id="layuiminiHomeTabId" lay-id="welcome"><i class="fa fa-home"></i> <span>首页</span></li>
            </ul>


            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"> <i class="fa fa-dot-circle-o"></i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-page-close="other"><i class="fa fa-window-close"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" data-page-close="all"><i class="fa fa-window-close-o"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div id="layuiminiHomeTabIframe" class="layui-tab-item layui-show">
                </div>
            </div>
        </div>



    </div>

</div>

<script type="text/javascript" src="${ctx.contextPath}/js/main.js"></script>
</body>
</html>

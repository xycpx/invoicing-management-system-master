layui.use(['table','laydate','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;


    laydate.render({
        elem: '#startDate',
        value:getBeforeDate(-6)
    });
    laydate.render({
        elem: '#endDate',
        value:getBeforeDate(0)
    });

    var  tableIns = table.render({
        elem: '#daySale',
        url:ctx+"/sale/countSaleByDay",
        cellMinWidth : 95,
        height : "auto",
        toolbar: "#toolbarDemo",
        totalRow: true,
        id : "daySaleTable",
        cols : [[
            {field: "date", title:'销售日期',align:'center',totalRowText: '合计（￥）'},
            {field: 'amountCost', title: '成本金额(￥)', minWidth:80, align:"center",totalRow: true},
            {field: 'amountSale', title: '销售金额（￥）', minWidth:80, align:'center',totalRow: true},
            {field: 'amountProfit', title: '盈利金额(￥)', align:'center',minWidth:80,totalRow: true}
        ]]
    });

    // 多条件搜索
    $(".search_btn").on("click",function(){
        var  startDate= $("input[name='startDate']").val();
        var endDate= $("input[name='endDate']").val();
        table.reload("daySaleTable",{
            where: {
                begin:startDate,
                end:endDate
            }
        })
    });


    //n为你要传入的参数，当前为0，前一天为-1，后一天为1
    function getBeforeDate(n){
        var date = new Date() ;
        var year,month,day ;
        date.setDate(date.getDate()+n);
        year = date.getFullYear();
        month = date.getMonth()+1;
        day = date.getDate() ;
        s = year + '-' + ( month < 10 ? ( '0' + month ) : month ) + '-' + ( day < 10 ? ( '0' + day ) : day) ;
        return s ;
    }


});

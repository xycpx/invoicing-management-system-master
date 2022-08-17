layui.use(['table','laydate','layer',"form"],function(){
       var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        table = layui.table;


    laydate.render({
        elem: '#startDate',
        type: 'month',
        value:getBeforeMonth(-5)
    });
    laydate.render({
        elem: '#endDate',
        type: 'month',
        value:getBeforeMonth(0)
    });

    var  tableIns = table.render({
        elem: '#monthSale',
        url:ctx+"/sale/countSaleByMonth",
        cellMinWidth : 95,
        height : "auto",
        toolbar: "#toolbarDemo",
        totalRow: true,
        id : "monthSaleTable",
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
        table.reload("monthSaleTable",{
            where: {
                begin:startDate,
                end:endDate
            }
        })
    });

    function getBeforeMonth(n){
        var date = new Date() ;
        var year,month,day ;
        date.setMonth(date.getMonth()+n);
        year = date.getFullYear();
        month = date.getMonth()+1;
        s = year + '-' + ( month < 10 ? ( '0' + month ) : month );
        return s ;
    }




});

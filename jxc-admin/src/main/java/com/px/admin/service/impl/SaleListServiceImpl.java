package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.model.CountResultModel;
import com.px.admin.pojo.Goods;
import com.px.admin.pojo.SaleList;
import com.px.admin.mapper.SaleListMapper;
import com.px.admin.pojo.SaleListGoods;
import com.px.admin.query.SaleListQuery;
import com.px.admin.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.service.GoodsTypeService;
import com.px.admin.service.SaleListGoodsService;
import com.px.admin.service.SaleListService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.DateUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 销售单服务类
 */
@Service
public class SaleListServiceImpl extends ServiceImpl<SaleListMapper, SaleList> implements SaleListService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private SaleListGoodsService saleListGoodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;


    @Override
    public String getNextSaleNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("XS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextSaleNumber();
            if(null !=saleNumber){
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            }else{
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveSaleList(SaleList saleList, List<SaleListGoods> slgList) {
        AssertUtil.isTrue(saleList.getCustomerId()==0,"请选择客户");
        AssertUtil.isTrue(saleList.getAmountPayable()==null,"应付金额为空");
        AssertUtil.isTrue(saleList.getAmountPaid()==null,"实付金额为空");
        AssertUtil.isTrue(saleList.getSaleDate()==null,"日期为空");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(System.currentTimeMillis());
        AssertUtil.isTrue( 0!=formatter.format(saleList.getSaleDate()).compareTo(formatter.format(date1)),"请选择本日");
        AssertUtil.isTrue(!(this.save(saleList)),"记录添加失败!");
        SaleList temp = this.getOne(new QueryWrapper<SaleList>().eq("sale_number",saleList.getSaleNumber()));
        AssertUtil.isTrue(slgList==null,"请选择商品");
        slgList.forEach(slg->{
            slg.setSaleListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(saleListGoodsService.save(slg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        IPage<SaleList> page = new Page<SaleList>(saleListQuery.getPage(),saleListQuery.getLimit());
        page =  this.baseMapper.saleList(page,saleListQuery);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }
    @Override
    public void deletesaleList(Integer id) {
        /**
         * 1.销售单商品记录删除
         * 2.销售单记录删除
         */
        AssertUtil.isTrue(!(saleListGoodsService.remove(new QueryWrapper<SaleListGoods>().eq("sale_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");
    }

    @Override
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        /**
         * 分页查询
         *   查总数
         *   查当前页列表
         */
        if(null !=saleListQuery.getTypeId()){
            List<Integer> typeIds= goodsTypeService.queryAllSubTypeIdsByTypeId(saleListQuery.getTypeId());
            saleListQuery.setTypeIds(typeIds);
        }
        /**
         *  page
         *    1-->0
         *    2-->10
         *    3-->20
         */
        saleListQuery.setIndex((saleListQuery.getPage()-1)*saleListQuery.getLimit());
        Long count  = this.baseMapper.countSaleTotal(saleListQuery);
        List<CountResultModel> list =this.baseMapper.saleListQueryList(saleListQuery);
        return PageResultUtil.setResult(count,list);
    }

    @Override
    public List<Map<String, Object>> countDaySale(String begin, String end) {
        return this.baseMapper.countDaySale(begin,end);
    }

    @Override
    public List<Map<String, Object>> countMonthSale(String begin, String end) {
        return this.baseMapper.countMonthSale(begin,end);
    }
}

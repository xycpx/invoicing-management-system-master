package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.model.CountResultModel;
import com.px.admin.pojo.Goods;
import com.px.admin.pojo.PurchaseList;
import com.px.admin.mapper.PurchaseListMapper;
import com.px.admin.pojo.PurchaseListGoods;
import com.px.admin.query.PurchaseListQuery;
import com.px.admin.service.GoodsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.service.GoodsTypeService;
import com.px.admin.service.PurchaseListGoodsService;
import com.px.admin.service.PurchaseListService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.DateUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务实现类
 * </p>
 *
 */
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements PurchaseListService {


    @Resource
    private PurchaseListGoodsService purchaseListGoodsService;

    @Resource
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;


    @Override
    public String getNextPurchaseNumber() {
        // JH20210101000X
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("JH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String purchaseNumber = this.baseMapper.getNextPurchaseNumber();
            if(null !=purchaseNumber){
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList) {
        AssertUtil.isTrue(purchaseList.getSupplierId().equals(0),"请添加供应商");
        AssertUtil.isTrue(purchaseList.getAmountPayable()==null,"应付金额为空");
        AssertUtil.isTrue(purchaseList.getAmountPaid()==null,"实付金额为空");
        AssertUtil.isTrue(purchaseList.getPurchaseDate()==null,"日期不能为空");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        AssertUtil.isTrue(0!= formatter.format(purchaseList.getPurchaseDate()).compareTo(formatter.format(date)),"请输入今日时间");
        AssertUtil.isTrue(!(this.save(purchaseList)),"记录添加失败!");
        PurchaseList temp = this.getOne(new QueryWrapper<PurchaseList>().eq("purchase_number",purchaseList.getPurchaseNumber()));
        AssertUtil.isTrue(plgList==null,"请选择货物");
        plgList.forEach(plg->{
            plg.setPurchaseListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+plg.getNum());
            goods.setLastPurchasingPrice(plg.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
        });

        AssertUtil.isTrue(!(purchaseListGoodsService.saveBatch(plgList)),"记录添加失败!");


    }

    @Override
    public Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery) {
        IPage<PurchaseList> page = new Page<PurchaseList>(purchaseListQuery.getPage(),purchaseListQuery.getLimit());
        page =  this.baseMapper.purchaseList(page,purchaseListQuery);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deletePurchaseList(Integer id) {
        /**
         * 1.进货单商品记录删除
         * 2.进货单记录删除
         */
        AssertUtil.isTrue(!(purchaseListGoodsService.remove(new QueryWrapper<PurchaseListGoods>().eq("purchase_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");


    }
    @Override
    public Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery) {
        /**
         * 分页查询
         *   查总数
         *   查当前页列表
         */
        if(null !=purchaseListQuery.getTypeId()){
            List<Integer> typeIds= goodsTypeService.queryAllSubTypeIdsByTypeId(purchaseListQuery.getTypeId());
            purchaseListQuery.setTypeIds(typeIds);
        }
        /**
         *  page
         *    1-->0
         *    2-->10
         *    3-->20
         */
        purchaseListQuery.setIndex((purchaseListQuery.getPage()-1)*purchaseListQuery.getLimit());
        Long count  = this.baseMapper.countPurchaseTotal(purchaseListQuery);
        List<CountResultModel> list =this.baseMapper.countPurchaseList(purchaseListQuery);
        return PageResultUtil.setResult(count,list);
    }
}

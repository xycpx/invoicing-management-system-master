package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.pojo.*;
import com.px.admin.mapper.CustomerReturnListMapper;
import com.px.admin.pojo.CustomerReturnList;
import com.px.admin.pojo.CustomerReturnListGoods;
import com.px.admin.pojo.Goods;
import com.px.admin.query.CustomerReturnListQuery;
import com.px.admin.service.CustomerReturnListGoodsService;
import com.px.admin.service.CustomerReturnListService;
import com.px.admin.service.GoodsService;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.DateUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客户退货单表
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements CustomerReturnListService {

    @Resource
    private GoodsService goodsService;

    @Resource
    private CustomerReturnListGoodsService customerReturnListGoodsService;

    @Override
    public String getNextCustomerReturnNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("XT");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String customerReturnNumber = this.baseMapper.getNextCustomerReturnNumber();
            if(null !=customerReturnNumber){
                stringBuffer.append(StringUtil.formatCode(customerReturnNumber));
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
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> slgList) {
        AssertUtil.isTrue(customerReturnList.getCustomerId()==0,"请选择客户");
        AssertUtil.isTrue(customerReturnList.getAmountPaid()==null,"应付金额为空");
        AssertUtil.isTrue(customerReturnList.getAmountPayable()==null,"实付金额为空");
        AssertUtil.isTrue(customerReturnList.getCustomerReturnDate()==null,"请选择日期");

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(System.currentTimeMillis());
        AssertUtil.isTrue(!formatter.format(customerReturnList.getCustomerReturnDate()).equals(formatter.format(date1)),"请选择本日");
        AssertUtil.isTrue(!(this.save(customerReturnList)),"记录添加失败!");
        CustomerReturnList temp = this.getOne(new QueryWrapper<CustomerReturnList>().eq("customer_return_number",customerReturnList.getCustomerReturnNumber()));
        slgList.forEach(slg->{
            slg.setCustomerReturnListId(temp.getId());
            Goods goods = goodsService.getById(slg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+slg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(slg.getNum()==0,"请选择数量");
            AssertUtil.isTrue(!(customerReturnListGoodsService.save(slg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        IPage<CustomerReturnList> page = new Page<CustomerReturnList>(customerReturnListQuery.getPage(),customerReturnListQuery.getLimit());
        page =  this.baseMapper.customerReturnList(page,customerReturnListQuery);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteCustomerReturn(Integer id) {
            /**
             * 1.退货单商品记录删除
             * 2.退货单单记录删除
             */
            AssertUtil.isTrue(!(customerReturnListGoodsService.remove(new QueryWrapper<CustomerReturnListGoods>().eq("customer_return_list_id",id))),
                    "记录删除失败!");
            AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");


        }
}

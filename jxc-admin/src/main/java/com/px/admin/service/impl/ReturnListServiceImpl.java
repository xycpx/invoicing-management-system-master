package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.pojo.*;
import com.px.admin.mapper.ReturnListMapper;
import com.px.admin.pojo.Goods;
import com.px.admin.pojo.ReturnList;
import com.px.admin.pojo.ReturnListGoods;
import com.px.admin.query.ReturnListQuery;
import com.px.admin.service.GoodsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.service.ReturnListGoodsService;
import com.px.admin.service.ReturnListService;
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
 * <p>
 * 退货单表 服务实现类
 * </p>
 */
@Service
public class ReturnListServiceImpl extends ServiceImpl<ReturnListMapper, ReturnList> implements ReturnListService {

    @Resource
    private ReturnListGoodsService returnListGoodsService;

    @Resource
    private GoodsService goodsService;

    @Override
    public String getNextReturnNumber() {
        // TH20210101000X
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("TH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String returnNumber = this.baseMapper.getNextReturnNumber();
            if(null !=returnNumber){
                stringBuffer.append(StringUtil.formatCode(returnNumber));
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
    public void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList) {
        AssertUtil.isTrue(returnList.getSupplierId()==0,"供应商为空");
        AssertUtil.isTrue(returnList.getAmountPayable()==null,"应付金额不能为空");
        AssertUtil.isTrue(returnList.getAmountPaid()==null,"实付金额不能为空");
        AssertUtil.isTrue(returnList.getReturnDate()==null,"请选择日期");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date(System.currentTimeMillis());
        formatter.format(returnList.getReturnDate()).compareTo(formatter.format(date1));
        AssertUtil.isTrue(formatter.format(returnList.getReturnDate()).compareTo(formatter.format(date1))!=0,"请选择本日时间");
        AssertUtil.isTrue(!(this.save(returnList)),"记录添加失败!");
        ReturnList  temp = this.getOne(new QueryWrapper<ReturnList>().eq("return_number",returnList.getReturnNumber()));
        AssertUtil.isTrue(rlgList==null,"请选择商品");
        rlgList.forEach(rlg->{
            rlg.setReturnListId(temp.getId());
            Goods goods =goodsService.getById(rlg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-rlg.getNum());
            goods.setState(2);
            goodsService.updateById(goods);

        });
        AssertUtil.isTrue(!(returnListGoodsService.saveBatch(rlgList)),"记录添加失败!");
    }

    @Override
    public Map<String, Object> returnList(ReturnListQuery returnListQuery) {
        IPage<ReturnList> page = new Page<ReturnList>(returnListQuery.getPage(),returnListQuery.getLimit());
        page =  this.baseMapper.returnList(page,returnListQuery);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteReturnList(Integer id) {
        /**
         * 1.退货单商品记录删除
         * 2.退货单记录删除
         */
        AssertUtil.isTrue(!(returnListGoodsService.remove(new QueryWrapper<ReturnListGoods>().eq("return_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");
    }
}

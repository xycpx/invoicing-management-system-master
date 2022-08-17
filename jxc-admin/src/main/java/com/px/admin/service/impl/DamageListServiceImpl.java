package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.pojo.*;
import com.px.admin.mapper.DamageListMapper;
import com.px.admin.pojo.DamageList;
import com.px.admin.pojo.DamageListGoods;
import com.px.admin.pojo.Goods;
import com.px.admin.query.DamageListQuery;
import com.px.admin.service.DamageListGoodsService;
import com.px.admin.service.DamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.service.GoodsService;
import com.px.admin.utils.AssertUtil;
import com.px.admin.utils.DateUtil;
import com.px.admin.utils.PageResultUtil;
import com.px.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报损表单类
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements DamageListService {


    @Resource
    private GoodsService goodsService;

    @Resource
    private DamageListGoodsService damageListGoodsService;

    @Override
    public String getNextDamageNumber() {
        try {
            StringBuffer stringBuffer =new StringBuffer();
            stringBuffer.append("BS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String saleNumber = this.baseMapper.getNextDamageNumber();
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
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList) {
        AssertUtil.isTrue(damageList.getDamageDate()==null,"请填写日期");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        AssertUtil.isTrue(0!= formatter.format(damageList.getDamageDate()).compareTo(formatter.format(date)),"请输入今日时间");
        AssertUtil.isTrue(!(this.save(damageList)),"记录添加失败!");
        DamageList temp = this.getOne(new QueryWrapper<DamageList>().eq("damage_number",damageList.getDamageNumber()));
        AssertUtil.isTrue(plgList==null,"请选择商品");
        plgList.forEach(plg->{
            plg.setDamageListId(temp.getId());
            Goods goods = goodsService.getById(plg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-plg.getNum());
            goods.setState(2);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(damageListGoodsService.save(plg)),"记录添加失败!");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList> page = new Page<DamageList>(damageListQuery.getPage(),damageListQuery.getLimit());
        page =  this.baseMapper.damageList(page,damageListQuery);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void deletedamageList(Integer id) {
        /**
         * 1.报损单商品记录删除
         * 2.报损单记录删除
         */
        AssertUtil.isTrue(!(damageListGoodsService.remove(new QueryWrapper<DamageListGoods>().eq("damage_list_id",id))),
                "记录删除失败!");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败!");
    }
}

package com.px.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.px.admin.pojo.ReturnListGoods;
import com.px.admin.mapper.ReturnListGoodsMapper;
import com.px.admin.query.ReturnListGoodsQuery;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.px.admin.service.ReturnListGoodsService;
import com.px.admin.utils.PageResultUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务实现类
 * </p>
 */
@Service
public class ReturnListGoodsServiceImpl extends ServiceImpl<ReturnListGoodsMapper, ReturnListGoods> implements ReturnListGoodsService {

    @Override
    public Map<String, Object> returnListGoodsList(ReturnListGoodsQuery returnListGoodsQuery) {
        IPage<ReturnListGoods> page = new Page<ReturnListGoods>(returnListGoodsQuery.getPage(),returnListGoodsQuery.getLimit());
        QueryWrapper<ReturnListGoods> queryWrapper =new QueryWrapper<ReturnListGoods>();
        if(null != returnListGoodsQuery.getReturnListId()){
            queryWrapper.eq("return_list_id",returnListGoodsQuery.getReturnListId());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtil.setResult(page.getTotal(),page.getRecords());
    }
}

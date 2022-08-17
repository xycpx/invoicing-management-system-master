package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.dto.TreeDto;
import com.px.admin.pojo.GoodsType;

import java.util.List;

public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    public List<TreeDto> queryAllGoodsTypes();
}

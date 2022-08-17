package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.dto.TreeDto;
import com.px.admin.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    public List<TreeDto> queryAllMenu();
}

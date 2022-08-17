package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.px.admin.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.query.DamageListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * 报损单 Mapper接口
 * @author TianTian
 * @date 2022/1/21 14:03
 */
public interface DamageListMapper extends BaseMapper<DamageList> {

    String  getNextDamageNumber();

    IPage<DamageList>  damageList(IPage<DamageList> page, @Param("damageListQuery") DamageListQuery damageListQuery);
}

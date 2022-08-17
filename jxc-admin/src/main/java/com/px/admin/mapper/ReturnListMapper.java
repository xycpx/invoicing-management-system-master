package com.px.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.px.admin.pojo.ReturnList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.px.admin.query.ReturnListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 退货单表 Mapper 接口
 * </p>
 *
 * @author 老李
 * @since 2021-03-27
 */
public interface ReturnListMapper extends BaseMapper<ReturnList> {

    String  getNextReturnNumber();

    IPage<ReturnList>  returnList(IPage<ReturnList> page, @Param("returnListQuery") ReturnListQuery returnListQuery);
}

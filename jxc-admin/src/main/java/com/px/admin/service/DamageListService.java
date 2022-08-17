package com.px.admin.service;

import com.px.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.admin.pojo.DamageListGoods;
import com.px.admin.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * 报损单服务类
 */
public interface DamageListService extends IService<DamageList> {

    String getNextDamageNumber();

    void saveDamageList(DamageList damageList, List<DamageListGoods> plgList);

    Map<String, Object> damageList(DamageListQuery damageListQuery);

    void deletedamageList(Integer id);
}

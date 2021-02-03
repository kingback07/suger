package com.suger.is.permissionengine.v4.vo;

import lombok.Data;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
@Data
public class CalculateRule {

    private Long unitId;
    private String ruleStr;
    private Long startTime;
    private Long endTime;

    // cache username+ruleStr 的 Hash和计算结果

    public boolean matchUser(String username,UserCacheSource cacheSource){
        return false;
    }

}

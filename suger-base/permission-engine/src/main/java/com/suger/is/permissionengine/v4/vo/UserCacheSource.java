package com.suger.is.permissionengine.v4.vo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.suger.is.permissionengine.v2.domain.User;

/**
 * 缓存管理对象
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public class UserCacheSource {

    ConcurrentHashMap<String, List<String>> userkeysCache; //用户相关缓存key管理，用户缓存信息存放于redis， 如果用户信息发生变化，可以主动提取对应的所有相关key进行删除

    User getUserInfo(String username) {
        /**
         * 1. 用户主数据信息缓存24h
         * 2. 如果用户主数据信息发生变化(接口触发｜消息消费) 即时更新用户主数据信息并删除所有相关key(async)
         */
        return null;
    }

    boolean getUserRuleMatchedCache(String userName, String ruleStr) {
        return false;
    }


}

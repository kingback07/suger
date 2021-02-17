package com.suger.is.permissionengine.v4.service.appRole;

import java.util.List;

import com.suger.is.permissionengine.v2.domain.App;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public class BaseAppRole implements AppRole {

    App curApp;

    @Override
    public List<Long> getEnabledRoleIds(Long appId) {
        return null;
    }

    protected App getCurApp(){
        return curApp;
    }
}

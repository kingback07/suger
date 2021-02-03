package com.suger.is.permissionengine.v4.service.appRole;

import java.util.List;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public interface AppRole {

    List<Long> getEnabledRoleIds(Long appId);

}


package com.suger.is.permissionengine.v1.service;

import java.util.Set;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */
public interface EnableRoleFetchService {
    Set<Long> fetchEnableRoleIds(Long appId);
}

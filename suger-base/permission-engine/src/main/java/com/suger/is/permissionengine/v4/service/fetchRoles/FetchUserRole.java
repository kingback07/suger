package com.suger.is.permissionengine.v4.service.fetchRoles;

import java.util.List;

import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public interface FetchUserRole {
    List<GrantSourceInfo> fetchUserRole(String username, List<Long> selRoleIds);
}

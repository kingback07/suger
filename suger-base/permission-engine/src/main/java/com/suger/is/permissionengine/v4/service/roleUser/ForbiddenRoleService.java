package com.suger.is.permissionengine.v4.service.roleUser;

import java.util.List;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public interface ForbiddenRoleService {
    List<Long> listForbiddenRoles(String userName,Long appId);
}

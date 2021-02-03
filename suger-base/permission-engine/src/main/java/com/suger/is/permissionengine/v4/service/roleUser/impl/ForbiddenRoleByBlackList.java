package com.suger.is.permissionengine.v4.service.roleUser.impl;

import java.util.List;

import com.suger.is.permissionengine.v4.service.roleUser.ForbiddenRoleService;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public class ForbiddenRoleByBlackList implements ForbiddenRoleService {
    @Override
    public List<Long> listForbiddenRoles(String userName,Long appId) {
        return null;
    }
}

package com.suger.is.permissionengine.v4.service.fetchRoles.impl;

import java.util.List;
import java.util.Map;

import com.suger.is.permissionengine.v4.service.fetchRoles.FetchUserRole;
import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public class FetchByGroup implements FetchUserRole {

    @Override
    public List<GrantSourceInfo> fetchUserRole(String username, List<Long> selRoleIds) {

        // fetch enabled group by group_user

        // fetch enabled group by group_rule

        return null;
    }

    private List<Long> fetchMatchedRules(String username, Map<Long, String> ruleMap) {
        return null;
    }
}

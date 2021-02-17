package com.suger.is.permissionengine.v2.service.usermatch.impl;

import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;
import com.suger.is.permissionengine.v2.domain.User;
import com.suger.is.permissionengine.v2.service.usermatch.IFetchUserGrantInfo;

import lombok.Data;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-27
 */
@Data
public class RoleRuleMatch implements IFetchUserGrantInfo {

    String ruleStr;

    @Override
    public GrantSourceInfo getGrantInfo(User user) {
        return null;
    }
}

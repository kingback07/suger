package com.suger.is.permissionengine.v2.factory;

import java.util.List;

import com.suger.is.permissionengine.v2.domain.UserGroup;
import com.suger.is.permissionengine.v2.service.usermatch.IFetchUserGrantInfo;
import com.suger.is.permissionengine.v2.service.usermatch.impl.RoleGroupMatch;
import com.suger.is.permissionengine.v2.service.usermatch.impl.RoleRuleMatch;
import com.suger.is.permissionengine.v2.service.usermatch.impl.RoleUserMatch;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-27
 */
public class UserMatchFactory {

    public static IFetchUserGrantInfo createRoleUserMatch(List<String> usernames) {
        RoleUserMatch roleUserMatch = new RoleUserMatch();
        roleUserMatch.setUsername(usernames);
        return roleUserMatch;
    }

    public static IFetchUserGrantInfo createRoleRuleMatch(String ruleStr) {
        RoleRuleMatch roleRuleMatch = new RoleRuleMatch();
        roleRuleMatch.setRuleStr(ruleStr);
        return roleRuleMatch;
    }

    public static RoleGroupMatch createRoleGroupMatch(UserGroup userGroup) {
        RoleGroupMatch roleRuleMatch = new RoleGroupMatch(userGroup);
        return roleRuleMatch;
    }

}

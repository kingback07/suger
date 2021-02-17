package com.suger.is.permissionengine.v2.domain;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.suger.is.permissionengine.v2.service.fetch.FetchGrantSourceAdapter;
import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;

/**
 * 角色对象
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-26
 */
@Data
public class Role {

    private Long roleId;

    private List<String> grantUserNames;
    private List<String> forbiddenUserNames;
    private String roleRuleStr;
    private List<UserGroup> grantGroups;

    private FetchGrantSourceAdapter fetchGrantSourceAdapter;

    public Role(Long roleId) {
        /**
         * 初始化角色关联的相关属性
         *
         */

    }

    public Map<Long, List<GrantSourceInfo>> fetchGranInfo(User user) {
        //过滤黑名单用户
        if (CollectionUtils.isNotEmpty(forbiddenUserNames) && forbiddenUserNames.contains(user.getUsername())) {
            return new HashMap<>();
        }
        List<GrantSourceInfo> grantSourceInfos = fetchGrantSourceAdapter.fetchGrantSource(user);
        HashMap res = new HashMap();
        res.put(roleId, grantSourceInfos);
        return res;
    }

}

package com.suger.is.permissionengine.v2.service.fetch;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;
import com.suger.is.permissionengine.v2.domain.Role;
import com.suger.is.permissionengine.v2.domain.User;
import com.suger.is.permissionengine.v2.domain.UserGroup;
import com.suger.is.permissionengine.v2.factory.UserMatchFactory;
import com.suger.is.permissionengine.v2.service.usermatch.IFetchUserGrantInfo;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-27
 */
public class FetchGrantSourceAdapter {

    private List<IFetchUserGrantInfo> matchSelector;

    public List<GrantSourceInfo> fetchGrantSource(User user) {

        if (CollectionUtils.isEmpty(matchSelector)) {
            /**
             * 没有支持的处理方式
             */
            return null;
        }


        /**
         * 并行计算matchSelector 的计算结果后合并
         */

//        Executor executor = Executors.newFixedThreadPool(50);
        List<CompletableFuture<GrantSourceInfo>> asyncMatchTask = matchSelector.stream().map(
                fetch -> CompletableFuture.supplyAsync(() -> fetch.getGrantInfo(user))
        ).collect(Collectors.toList());


        return null;
    }

    public FetchGrantSourceAdapter(Role role) {
        if (CollectionUtils.isNotEmpty(role.getGrantUserNames())) {
            matchSelector.add(UserMatchFactory.createRoleUserMatch(role.getGrantUserNames()));
        }
        if (CollectionUtils.isNotEmpty(role.getGrantGroups())) {
            for (UserGroup userGroup : role.getGrantGroups()) {
                matchSelector.add(UserMatchFactory.createRoleGroupMatch(userGroup));
            }
        }
        if (!StringUtils.isEmpty(role.getRoleRuleStr())) {
            matchSelector.add(UserMatchFactory.createRoleRuleMatch(role.getRoleRuleStr()));
        }
    }

}

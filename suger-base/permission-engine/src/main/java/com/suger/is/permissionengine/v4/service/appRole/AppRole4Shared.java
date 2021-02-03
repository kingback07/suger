package com.suger.is.permissionengine.v4.service.appRole;

import java.util.List;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
public class AppRole4Shared extends BaseAppRole {
    @Override
    public List<Long> getEnabledRoleIds(Long appId) {

        return null;
    }

    private List<Long> getSharedRoleIds(Long curAppId, Long parentAppId) {
        return null;
    }

    private List<Long> getCurAppRoleIds(Long appId) {
        return super.getEnabledRoleIds(appId);
    }

    private List<Long> mergeResult(List<Long> curRoleIds, List<Long> sharedRoleIds) {
        return null;
    }


}

package com.suger.is.permissionengine.v1.engine;

import lombok.Data;

import java.util.List;
import java.util.Set;

import com.suger.is.app.permission.common.dto.mdata.DetailUserInfoDTO;
import com.suger.is.permissionengine.v1.service.EnableRoleFetchService;

/**
 * 应用可用角色列表计算引擎
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */

@Data
public class EnableRoleListEngine {

    private DetailUserInfoDTO userInfoDTO;

    private List<EnableRoleFetchService> enableRoleFetchPool;//可用角色策略池

    public Set<Long> getEnabledRoles(Long appId){

        //TODO: 读取策略池，异步执行 fetchEnableRoleIds 方法后，计算用户黑名单，merge

        return null;
    }

    public void initEngine(String username,Long appId){
        //TODO: 初始化操作，异步获取用户对象信息，构建enableRoleFetchPool
    }

}

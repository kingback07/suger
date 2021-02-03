package com.suger.is.permissionengine.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.suger.is.app.permission.common.dto.mdata.DetailUserInfoDTO;
import com.suger.is.app.permission.common.dto.role.RoleSourceInfoDTO;
import com.suger.is.permissionengine.v1.engine.RoleMatchEngine;
import com.suger.is.permissionengine.v1.model.AccessibleRoleInfo;
import com.suger.is.permissionengine.v1.engine.EnableRoleListEngine;

/**
 * 用户角色计算引擎
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */
public class UserRoleComputeservice {


    private EnableRoleListEngine enableRoleListEngine;

    private Executor executor;


    /**
     * 权限计算入口
     *
     * @Author: wangyang09
     * @Date 2020/11/24
     **/
    public List<AccessibleRoleInfo> computeUserRoles(String username, Long appId) {

        //TODO：权限计算入口

        enableRoleListEngine.initEngine(username, appId);
        Set<Long> enableRoleIds = enableRoleListEngine.getEnabledRoles(appId);
        if(CollectionUtils.isEmpty(enableRoleIds)){
            return new ArrayList<>();
        }

        //TODO: 对enableRoleIds 创建线程池，进行并发计算。每一个roleId 对应创建对应的RoleMatchEngine
        DetailUserInfoDTO userInfo=enableRoleListEngine.getUserInfoDTO();
        List<CompletableFuture<List<RoleSourceInfoDTO>>> asyncMatchTask=enableRoleIds.stream().map(
                roleId->CompletableFuture.supplyAsync(() -> doMatchUserRole(userInfo, roleId), executor)
        ).collect(Collectors.toList());

        List<List<RoleSourceInfoDTO>> asyncMatchTaskResult=asyncMatchTask.stream().map(CompletableFuture::join).collect(Collectors.toList());


        //TODO: afterprocess: merge & converter


        return null;
    }

    private List<RoleSourceInfoDTO> doMatchUserRole(DetailUserInfoDTO userInfo,Long roleId){
        RoleMatchEngine roleMatchEngine=new RoleMatchEngine();
        roleMatchEngine.initEngine(enableRoleListEngine.getUserInfoDTO(),roleId);
        List<RoleSourceInfoDTO> matchResult=roleMatchEngine.fetchMatchedRole(userInfo.getUsername(),roleId);
        return matchResult;
    }


}

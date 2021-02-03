package com.suger.is.permissionengine.v1.engine;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import com.suger.is.app.permission.common.dto.mdata.DetailUserInfoDTO;
import com.suger.is.app.permission.common.dto.role.RoleSourceInfoDTO;
import com.suger.is.permissionengine.v1.service.MatchRoleService;

/**
 * 用户角色匹配引擎
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */
public class RoleMatchEngine {

    private List<MatchRoleService> matchRoleServicePool;

    private Executor executor;

    public List<RoleSourceInfoDTO> fetchMatchedRole(String username, Long roleId) {

        List<CompletableFuture<List<RoleSourceInfoDTO>>> asyncFetchTask = matchRoleServicePool.stream().map(
                matchRoleService -> CompletableFuture.supplyAsync(() -> matchRoleService.matchRole(username, roleId), executor)
        ).collect(Collectors.toList());

        List<List<RoleSourceInfoDTO>> asyncFetchTaskResult = asyncFetchTask.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return merge(asyncFetchTaskResult);
    }

    public void initEngine(DetailUserInfoDTO userInfo, Long roleId) {

    }

    private List<RoleSourceInfoDTO> merge(List<List<RoleSourceInfoDTO>> asyncFetchTaskResult) {
        return null;
    }


}

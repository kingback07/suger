package com.suger.is.permissionengine.v4.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suger.is.permissionengine.v4.service.appRole.AppRole;
import com.suger.is.permissionengine.v4.service.fetchRoles.FetchUserRole;
import com.suger.is.permissionengine.v4.service.roleUser.ForbiddenRoleService;
import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-12-01
 */
@Service
public class CalculateUserRoleService {

    @Autowired
    List<FetchUserRole> fetchUserRoleServices;



    List<GrantSourceInfo> calculateUserRole(String userName, Long appId) {

        //Thread A
        AppRole appRole = selectAppRole(appId);
        List<Long> enabledAppRoleIds = appRole.getEnabledRoleIds(appId);

        //Thread B
        ForbiddenRoleService forbiddenRoleService = selectForbiddenRole();
        List<Long> forbiddenAppRoleIds = forbiddenRoleService.listForbiddenRoles(userName, appId);

        //synchronized A & B
        List<Long> selectedRoleIds = getEnabledRoles(enabledAppRoleIds, forbiddenAppRoleIds);

        //async fetchUserRoleServices;
//        Executor executor = Executors.newFixedThreadPool(5);
        List<CompletableFuture<List<GrantSourceInfo>>> asyncFetchTasks = fetchUserRoleServices.stream().map(
                fetchUserRoleService -> CompletableFuture.supplyAsync(() -> fetchUserRoleService.fetchUserRole(userName, selectedRoleIds))
        ).collect(Collectors.toList());

        List<List<GrantSourceInfo>> asyncFetchTaskResult = asyncFetchTasks.stream().map(CompletableFuture::join).collect(Collectors.toList());

        return processAsyncTask(asyncFetchTaskResult);
    }

    private List<Long> getEnabledRoles(List<Long> enabledAppRoleIds, List<Long> forbiddenAppRoleIds) {
        return null;
    }

    private AppRole selectAppRole(Long appId) {
        return null;
    }

    private ForbiddenRoleService selectForbiddenRole() {
        return null;
    }

    private List<GrantSourceInfo> processAsyncTask(List<List<GrantSourceInfo>> asyncFetchTaskResult) {
        return null;
    }

}

package com.suger.is.permissionengine.v2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;

/**
 * 应用对象
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-26
 */
public class App {

    private App parentApp;

    private Long appId;

    //通过appId 构造对象
    public App(Long appid) {
        // getFromCache or Remote AppService
    }

    /**
     * 此应用下获取用户拥有的角色(包括父应用)
     *
     * @Author: wangyang09
     * @Date 2020/11/26
     **/
    public Map<Long, List<GrantSourceInfo>> getRolesByUser(User user) {

        List<Role> enableRoles = getEnableRoles();
        if (CollectionUtils.isEmpty(enableRoles)) {
            //TODO: 应用下无角色的返回处理
            return null;
        }
        /**
         *  异步任务：匹配每个Role方法
         */
//        Executor executor = Executors.newFixedThreadPool(50);
        List<CompletableFuture<Map<Long, List<GrantSourceInfo>>>> asyncFetchTask = enableRoles.stream().map(
                role -> CompletableFuture.supplyAsync(() -> role.fetchGranInfo(user))
        ).collect(Collectors.toList());

        /**
         * 异步结果后处理
         */

        return null;
    }

    public List<Role> getEnableRoles() {

        List<Role> enableRoles = new ArrayList<>();

        /**
         * fetch enable roles by appid
         * AsyncTask to convert Role
         */

        if (parentApp != null) {
            enableRoles.addAll(parentApp.getSharedRoles(appId));
        }

        return enableRoles;
    }

    public List<Role> getSharedRoles(Long childAppId) {
        /**
         *  fetch shared Roles
         */
        return null;
    }

    private Role fetchRole(Object dbRoleInfo) {

        /**
         * 1. fetch from cache
         *
         * 2. construct by dbInfo
         */


        return null;
    }


}

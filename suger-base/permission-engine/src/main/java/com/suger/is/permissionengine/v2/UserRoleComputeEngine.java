package com.suger.is.permissionengine.v2;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suger.is.permissionengine.v2.domain.App;
import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;
import com.suger.is.permissionengine.v2.domain.User;
import com.suger.is.permissionengine.v2.factory.UserFactory;

/**
 * 方法入口
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-27
 */
@Service
public class UserRoleComputeEngine {

    @Autowired
    UserFactory userFactory;

    Map<Long, List<GrantSourceInfo>> getUserRoles(String username, Long appId) {
        App app = new App(appId);
        User curUser = userFactory.getUserByName(username);
        return app.getRolesByUser(curUser);
    }

}

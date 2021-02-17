package com.suger.is.permissionengine.v2.service.usermatch;

import com.suger.is.permissionengine.v4.vo.GrantSourceInfo;
import com.suger.is.permissionengine.v2.domain.User;

/**
 * 用户匹配接口，判断用户是否属于此角色
 *
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-27
 */
public interface IFetchUserGrantInfo {

    GrantSourceInfo getGrantInfo(User user);

}

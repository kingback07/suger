package com.suger.is.permissionengine.v1.service;

import java.util.List;

import com.suger.is.app.permission.common.dto.role.RoleSourceInfoDTO;

/**
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */
public interface MatchRoleService {
    List<RoleSourceInfoDTO> matchRole(String username, Long roleId);
}

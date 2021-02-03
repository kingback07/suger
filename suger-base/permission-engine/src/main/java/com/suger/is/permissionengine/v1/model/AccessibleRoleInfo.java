package com.suger.is.permissionengine.v1.model;

import lombok.Data;

import java.util.List;

import com.suger.is.app.permission.common.dto.role.RoleSourceInfoDTO;

/**
 * 角色
 * @author wangyang09 wangyang09@suger.com>
 * Created on 2020-11-24
 */
@Data
public class AccessibleRoleInfo {

    private Long roleId;
    private List<RoleSourceInfoDTO> roleSourceList;

}

package com.nantian.daoExtend;

import java.util.List;

public interface SecurityMapper {
    List<SecurityUser> getUserById(String userId);
    List<SecurityUser> getUsersByRoleId(String roleId);
}

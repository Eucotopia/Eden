package top.easylove.service.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.easylove.enums.ResultEnum;
import top.easylove.pojo.Role;
import top.easylove.repository.RoleRepository;
import top.easylove.service.IRoleService;
import top.easylove.util.ResultResponse;

@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleRepository roleRepository;

    @Override
    public ResultResponse<String> addRole(Role role) {
        if (StrUtil.isEmpty(role.getName())) {
            return ResultResponse.error(ResultEnum.ERROR);
        }

        if (StrUtil.isEmpty(role.getDescription())) {
            return ResultResponse.error(ResultEnum.ERROR);
        }

        roleRepository.saveAndFlush(role);

        return ResultResponse.success(ResultEnum.SUCCESS,"su");
    }
}

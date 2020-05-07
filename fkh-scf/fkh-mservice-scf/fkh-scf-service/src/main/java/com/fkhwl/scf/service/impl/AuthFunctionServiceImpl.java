package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.po.AuthFunction;
import com.fkhwl.scf.service.AuthFunctionRepositoryService;
import com.fkhwl.scf.service.AuthFunctionService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.AuthFunctionServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthFunctionServiceImpl  implements AuthFunctionService {

    private final AuthFunctionRepositoryService authFunctionRepositoryService;

    @Override
    public IPage<AuthFunctionDTO> listPage(IPage<AuthFunctionDTO> page, Map<String, Object> params) {
        return authFunctionRepositoryService.listPage(page, params);
    }

    @Override
    public AuthFunctionDTO getDetail(Long id) {
        return AuthFunctionServiceConverterWrapper.INSTANCE.dto(authFunctionRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(AuthFunctionDTO authFunctionDTO) {
        Date currentDate = new Date();
        AuthFunction authFunction = AuthFunctionServiceConverterWrapper.INSTANCE.po(authFunctionDTO);
        authFunction.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(authFunctionDTO.getId())) {
            authFunction.setDeleted(DeleteEnum.N);
            authFunction.setCreateTime(currentDate);
        }
        authFunctionRepositoryService.saveOrUpdate(authFunction);
    }

    @Override
    public void delete(Long id) {
        //逻辑删除权限
        authFunctionRepositoryService.removeById(id);
        //todo add-by chenli 是否逻辑删除对应的“角色-权限”中间表
    }

    @Override
    public List<String> listFuncKey(Long roleId) {
        return authFunctionRepositoryService.listFuncKey(roleId);
    }
}

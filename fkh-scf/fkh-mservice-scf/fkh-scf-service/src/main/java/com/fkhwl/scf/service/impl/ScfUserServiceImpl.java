package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.po.ScfUser;
import com.fkhwl.scf.service.ScfUserRepositoryService;
import com.fkhwl.scf.service.ScfUserService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.ScfUserServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.StringUtils;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class ScfUserServiceImpl implements ScfUserService {

    /** User repository service */
    private final ScfUserRepositoryService scfUserRepositoryService;

    @Override
    public List<ScfUserDTO> listScfUser() {
        List<ScfUser> list = scfUserRepositoryService.list();
        return list.stream().map(ScfUserServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }

    @Override
    public Optional<ScfUserDTO> getDetail(Long userId) {
        return Optional.ofNullable(ScfUserServiceConverterWrapper.INSTANCE.dto(scfUserRepositoryService.getById(userId)));
    }
    @Override
    public Optional<ScfUserDTO> getByUserName(String username) {
        return Optional.ofNullable(ScfUserServiceConverterWrapper.INSTANCE.dto(scfUserRepositoryService.getOne(new LambdaQueryWrapper<ScfUser>().eq(ScfUser::getUsername,username))));
    }

    @Override
    public Optional<ScfUserDTO> getByUserNameAndPassword(String username, String password) {
        return Optional.ofNullable(ScfUserServiceConverterWrapper.INSTANCE.dto(scfUserRepositoryService.getOne(new LambdaQueryWrapper<ScfUser>().eq(ScfUser::getUsername,username).eq(ScfUser::getUserPassword,password))));
    }

    @Override
    public void modifyLoginPwd(Long id, String oldPassword, String newPassword) {
        if (StringUtils.isNotBlank(oldPassword)) {
            ScfUser user = scfUserRepositoryService.getById(id);
            boolean flag =  oldPassword.equals(user.getUserPassword());
            AssertUtils.isTrue(flag, "旧密码错误!");
        }
        ScfUser scfUser =new ScfUser();
        scfUser.setId(id);
        scfUser.setUserPassword(newPassword);
        scfUser.setUpdateTime(new Date());
        scfUserRepositoryService.updateById(scfUser);
    }

    @Override
    public IPage<ScfUserDTO> listPage(IPage<ScfUserDTO> page, Map<String, Object> params) {
        Integer userType = (Integer)params.get("userType");
        if (userType == 3) {//查询客户
            return scfUserRepositoryService.listPageCustomer(page, params);
        } else {//查询用户
            return scfUserRepositoryService.listPage(page, params);
        }
    }

    @Override
    public void saveOrUpdate(ScfUserDTO scfUserDTO) {
        Date currentDate = new Date();
        ScfUser user = ScfUserServiceConverterWrapper.INSTANCE.po(scfUserDTO);
        user.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(scfUserDTO.getId())) {
            //查询用户名是否已存在
            ScfUser dbUser = scfUserRepositoryService.getOne(new LambdaQueryWrapper<ScfUser>().eq(ScfUser::getUsername, scfUserDTO
                .getUsername()));
            AssertUtils.isNull(dbUser, "登录账号已存在");
            //todo chenli 2020-03-06 密码再次加密问题
//            user.setUserPassword(DigestUtils.md5Hex(scfUserDTO.getUserPassword()));
            user.setDeleted(DeleteEnum.N);
            user.setCreateTime(currentDate);
        }
        scfUserRepositoryService.saveOrUpdate(user);
        //回填id
        scfUserDTO.setId(user.getId());
    }

    @Override
    public void delete(Long id) {
        scfUserRepositoryService.removeById(id);
    }
}

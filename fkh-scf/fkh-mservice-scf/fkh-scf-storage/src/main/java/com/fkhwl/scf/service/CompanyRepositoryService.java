package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.starter.mybatis.service.BaseService;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 企业表 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface CompanyRepositoryService extends BaseService<Company> {

    /**
     * 分页查询企业
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<CompanyDTO> listPage(IPage<CompanyDTO> page, Map<String, Object> params);
    /**
     * 根据资方id查询所有借款方负责人id
     * @param ownerId
     * @return: java.util.List<java.lang.Long>
     * @Author: liuan
     * @Date: 2020/3/15 17:16
     */
    List<Long> getBorrowerOwnerIdsByCapital(Long ownerId);

    /**
     * 根据ownerId获取企业信息
     *
     * @param ownerId  资方企业主账号id/借款方主账号id
     * @return 企业信息
     */
    CompanyDTO getByOwnerId(Long ownerId);

    /**
     * 根据companyName获取企业信息
     *
     * @param params  companyName
     * @return 企业信息
     */
    CompanyDTO getByParams(@Param("map")Map<String, Object> params);
}


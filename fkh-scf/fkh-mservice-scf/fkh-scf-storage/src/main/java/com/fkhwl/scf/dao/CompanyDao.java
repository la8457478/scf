package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 企业表 Dao 接口  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Mapper
public interface CompanyDao extends BaseDao<Company> {

    /**
     * 分页查询企业列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 企业列表 page
     */
    IPage<CompanyDTO> listPage(@Param("page") IPage<CompanyDTO> page,
                               @Param("map") Map<String, Object> params);
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
    CompanyDTO getByOwnerId(@Param("ownerId")Long ownerId);

    /**
     * 根据companyName获取企业信息
     *
     * @param params  companyName
     * @return 企业信息
     */
    CompanyDTO getByParams(@Param("map")Map<String, Object> params);
}

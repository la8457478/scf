package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyDTO;

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
public interface CompanyService {
    /**
     * 分页查询企业
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页企业数据 page
     */
    IPage<CompanyDTO> listPage(IPage<CompanyDTO> page, Map<String, Object> params);

    /**
     * 获取企业情
     *
     * @param id  企业id
     * @return 企业详情DTO optional
     */
    CompanyDTO getDetail(Long id);

    /**
     * 新增和更新企业
     *
     * @param companyDTO 企业实体
     */
    void saveOrUpdate(CompanyDTO companyDTO);

    /**
     * 删除企业
     *
     * @param id 企业id
     */
    void delete(Long id);
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
    * 更新账单提醒电话号码:
     * @param companyId
     * @param mobileNos
    * @return: void
    * @Author: liuan
    * @Date: 2020/4/7 11:52
    */
    void updateAccountMobileNos(Long companyId, String mobileNos);
}


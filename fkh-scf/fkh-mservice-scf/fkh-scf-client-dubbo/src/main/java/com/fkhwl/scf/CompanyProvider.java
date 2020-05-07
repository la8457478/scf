package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf企业 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface CompanyProvider {

    /**
     * 分页查询企业
     *
     * @param params 查询参数
     * @return 返回分页企业数据 page
     */
    IPage<CompanyDTO> listPage(Map<String, Object> params);
    /**
     * 用户分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<CompanyDTO> findPage(IPage<CompanyDTO> page, Map<String, Object> params);
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
    * 资方ownerId查询所有借款方ownerid:
     * @param ownerId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/15 17:14
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
    * 更新账单提醒接收电话号码:
     * @param companyId
    * @param mobileNos
     * @return: void
    * @Author: liuan
    * @Date: 2020/4/7 11:49
    */
    void updateAccountMobileNos(Long companyId, String mobileNos);
}

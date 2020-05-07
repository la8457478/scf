package com.fkhwl.scf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/26
 */
public interface CounterpartyProvider {
    /**
     * List by company id list.
     * @param companyId the company id
     * @return the list
     */
    List<CounterpartyDTO> listByCompanyId(Long companyId);

    /**
     * 获取用户详情
     * @param userId 用户ID
     * @return 用户详情DTO optional
     */
    CounterpartyDTO findInfo(Long userId);

    /**
     * 获取用户列表
     * @return 用户列表 list
     */
    List<CounterpartyDTO> findList();

    /**
     * 用户分页查询
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params);

    /**
     * 用户新增和更新
     * @param userDTO 用户实体
     */
    void save(CounterpartyDTO userDTO);
    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    void updateBaseConfig(CounterpartyDTO userDTO);
    /**
     * 按照userId批量删除用户信息，用户ID用逗号隔开
     * @param ids ids
     */
    void delete(List<Long> ids);
    /**
     * 审核
     * @param params( id = 交易对手ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    int review(Map<String, Object> params);
}

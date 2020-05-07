package com.fkhwl.template;

import com.fkhwl.starter.core.util.SystemUtils;
import com.fkhwl.starter.devtools.AutoGeneratorCodeBuilder;
import com.fkhwl.starter.devtools.TemplatesConfig;

import org.junit.Test;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: </p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dongshijie@fkhwl.com"
 * @date 2019.12.26 21:45
 */
public class AutoCodeTest {
    /**
     * Simple auto generator code
     */
    @Test
    public void simpleAutoGeneratorCode() {
        AutoGeneratorCodeBuilder.onAutoGeneratorCode()
            // 设置存放自动生成的代码路径, 不填则默认当前项目下
            .withModelPath("")
//            .withModelPath("F:\\codespace\\supply-chain-finance\\fkh-scf\\fkh-mservice-scf\\fkh-scf-common")
//            .withVersion("1.0.0")
            // 设置谁作者名, 默认读取 user.name 变量
            .withAuthor(SystemUtils.USER_NAME)
            // 设置包名 (前缀默认为 com.fkhwl, 因此最终的包名为: com.fkhwl.${packageName})
            .withPackageName("scf")
            // 设置根据哪张表生成代码, 可写多张表
            // 忽略前缀
            //.withPrefix(new String[] {"sys_"})
//            .withTables(new String[] {"waybill_bill", "waybill_bill_data"})
            .withTables(new String[] {"waybill_check_record"})
            // 设置需要生成的模板 不设置则全部生成
            .withTemplate(
                 TemplatesConfig.DAO,
                 TemplatesConfig.XML,
                TemplatesConfig.SERVICE,
                TemplatesConfig.IMPL

//
//                                TemplatesConfig.ENTITY,
//                                TemplatesConfig.DTO,
//                                 TemplatesConfig.VO


//                 TemplatesConfig.WRAPPER,
//                TemplatesConfig.CONTROLLER
                         )

            // 设置需要生成的配置
            .withComponets(

                          )
            .build();
    }
}

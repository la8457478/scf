package com.fkhwl.template.provider;

import com.fkhwl.scf.ScfUserProvider;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.dto.TemplateUserDTO;
import com.fkhwl.starter.test.FkhBaseTest;
import com.fkhwl.starter.test.FkhBootTest;

import org.junit.Test;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@FkhBootTest
public class UserDubboProviderTest extends FkhBaseTest {

    @Resource
    private ScfUserProvider provider;

    @Test
    public void findInfo() {
        ScfUserDTO info = provider.getByUserName("admin");
        log.info("[{}]", info);
    }
}

package com.jinhui.test;


import com.jinhui.domain.BatchImportHouse;
import com.jinhui.service.batch.DefaultBatchService;
import com.jinhui.service.batch.HouseExcelRowReader;
import com.jinhui.service.batch.ItemWriter;
import com.jinhui.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

/**
 * Created by jinhui on 2017/10/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:springmvc-servlet.xml" })
public class AppTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void Test1(){
        String groupId = "groupId";
        String key = "key";
        RedisUtils.setValue(key, groupId, "test");
        System.out.println(RedisUtils.getValue(key, groupId));
    }

    @Qualifier("HouseDBWriter")
    @Autowired
    private ItemWriter<BatchImportHouse> itemWriter;

    @Test
    public void excelTest() {
        File file = new File("src/test/resources/信息录入模版V2.xls");
        DefaultBatchService.excelExecute(file, new HouseExcelRowReader(), itemWriter, 5);
    }



}

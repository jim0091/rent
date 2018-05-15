package com.jinhui.service.batch;

import com.jinhui.domain.BatchImportHouse;
import com.jinhui.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jinhui on 2018/5/3.
 */
@Service("HouseDBWriter")
public class HouseDBWriter implements ItemWriter<BatchImportHouse> {

    private HouseMapper houseMapper;

    @Autowired
    public HouseDBWriter(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    @Override
    @Transactional
    public void write(List<BatchImportHouse> items) {
        try {
            houseMapper.batchAddHouses(items);
        } catch (DuplicateKeyException ex) {
            //ignored
        }
    }
}

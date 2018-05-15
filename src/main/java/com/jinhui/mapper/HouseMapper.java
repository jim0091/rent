package com.jinhui.mapper;

import com.jinhui.domain.*;

import java.util.List;
import java.util.Map;

/**
 * Created by jinhui on 2018/4/3.
 */
public interface HouseMapper {

    void saveHouse(House house);
    void saveContract(Contract contract);
    void saveTrusteeship(Trusteeship trusteeship);
    void saveModifiedRecord(ModifiedRecord modified);

    void batchAddHouses(List<BatchImportHouse> houses);

    List<House> findLastModifiedHouses(Long uid);
    House findLastModifiedHouse(Map paras);
    List<House> findHouseModifiedList(String houseId);
}

package com.jinhui.service.batch;

import org.apache.poi.ss.usermodel.Row;

/**
 * Created by jinhui on 2018/5/3.
 */
public interface ExcelRowReader<T> {

    T readRow(Row row);

}

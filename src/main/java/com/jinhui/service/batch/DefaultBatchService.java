package com.jinhui.service.batch;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinhui on 2018/5/4.
 */
public class DefaultBatchService {

    public static <T> void excelExecute(File excel, ExcelRowReader<T> reader,
                                    ItemWriter<T> writer, int commitNumber) {
        try(FileInputStream inputStream = new FileInputStream(excel)) {
            Workbook workbook = null;
            try {
               workbook = new HSSFWorkbook(inputStream);
            } catch (IOException ex) {
                workbook = new XSSFWorkbook(inputStream);
            }
            Sheet sheet = workbook.getSheet("Sheet1");
            List<T> records = new ArrayList<>(commitNumber);
            //skip eight rows
            for(int rowIndex=9;rowIndex<sheet.getLastRowNum();) {
                for(int num=0;num<commitNumber;num++) {
                    T t = reader.readRow(sheet.getRow(rowIndex++));
                    records.add(t);
                }
                writer.write(records);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

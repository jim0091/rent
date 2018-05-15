package com.jinhui.service.batch;

import com.jinhui.domain.BatchImportHouse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * Created by jinhui on 2018/5/3.
 */
public class HouseExcelRowReader implements ExcelRowReader<BatchImportHouse> {

    @Override
    public BatchImportHouse readRow(Row row) {
        BatchImportHouse house = new BatchImportHouse();
        RowIterator iter = new RowIterator(row);
        house.baseInfo.setProvince(iter.nextStringCellValue());
        house.baseInfo.setCity(iter.nextStringCellValue());
        house.baseInfo.setRegion(iter.nextStringCellValue());
        house.baseInfo.setPropertyAddr(iter.nextStringCellValue());
        house.baseInfo.setPropertyType(iter.nextStringCellValue());
        house.baseInfo.setCommunityLayout(iter.nextStringCellValue());
        house.baseInfo.setHouseholds(iter.nextStringCellValue());
        house.baseInfo.setRentalRatio(iter.nextStringCellValue());
        //traffic
        house.trafficInfo.setMetro(iter.nextStringCellValue());
        house.trafficInfo.setMetroDistance(iter.nextStringCellValue());
        house.trafficInfo.setBus(iter.nextStringCellValue());
        house.trafficInfo.setBusDistance(iter.nextStringCellValue());
        //house type
        house.houseType.setBuildingNo(iter.nextStringCellValue());
        house.houseType.setCellNo(iter.nextStringCellValue());
        house.houseType.setDoorNo(iter.nextStringCellValue());
        house.houseType.setArea(iter.nextStringCellValue());
        house.houseType.setType(iter.nextStringCellValue());
        house.houseType.setFloors(iter.nextStringCellValue());
        //decoration
        house.decorationInfo.setYear(iter.nextStringCellValue());
        //usable
        house.usableInfo.setBedroom(iter.nextStringCellValue());
        house.usableInfo.setDrawingRoom(iter.nextStringCellValue());
        house.usableInfo.setBathroom(iter.nextStringCellValue());
        house.usableInfo.setBalcony(iter.nextStringCellValue());
        //rental
        house.rentalInfo.setRent(iter.nextStringCellValue());
        house.rentalInfo.setPayType(iter.nextStringCellValue());
        house.rentalInfo.setTenancy(iter.nextStringCellValue());
        house.rentalInfo.setVacancy(iter.nextStringCellValue());
        //management
        house.managementInfo.setType(iter.nextStringCellValue());
        house.managementInfo.setHasSys(iter.nextStringCellValue());
        house.managementInfo.setHasSmartMeter(iter.nextStringCellValue());
        house.managementInfo.setHasSmartLock(iter.nextStringCellValue());
        house.managementInfo.setHasSmartRouter(iter.nextStringCellValue());
        //renter
        house.renterInfo.setAge(iter.nextStringCellValue());
        house.renterInfo.setGender(iter.nextStringCellValue());
        house.renterInfo.setIncome(iter.nextStringCellValue());
        house.renterInfo.setMarital(iter.nextStringCellValue());
        house.renterInfo.setHasChild(iter.nextStringCellValue());
        house.renterInfo.setBankCreditReport(iter.nextStringCellValue());
        house.renterInfo.setOtherCreditReport(iter.nextStringCellValue());
        house.renterInfo.setJudicalRecords(iter.nextStringCellValue());
        house.renterInfo.setEstateRatio(iter.nextStringCellValue());
        house.renterInfo.setOtherPropertyRatio(iter.nextStringCellValue());
        //tenant
        house.tenantInfo.setAge(iter.nextStringCellValue());
        house.tenantInfo.setGender(iter.nextStringCellValue());
        house.tenantInfo.setProfession(iter.nextStringCellValue());
        house.tenantInfo.setMarital(iter.nextStringCellValue());
        house.tenantInfo.setHasChild(iter.nextStringCellValue());
        house.tenantInfo.setBankCreditReport(iter.nextStringCellValue());
        house.tenantInfo.setOtherCreditReport(iter.nextStringCellValue());
        house.tenantInfo.setJudicalRecords(iter.nextStringCellValue());
        house.tenantInfo.setEstateRatio(iter.nextStringCellValue());
        house.tenantInfo.setOtherPropertyRatio(iter.nextStringCellValue());
        return house;
    }
    
    private final class RowIterator {
        private final Iterator<Cell> iterator;

        public RowIterator(Row row) {
            this.iterator = row.cellIterator();
            //skip two cells
            iterator.next();iterator.next();
        }
        public String nextStringCellValue() {
            if(!iterator.hasNext())
                return null;
            DecimalFormat df = new DecimalFormat("0");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Cell cell = iterator.next();
            Object value = null;
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        value = sdf.format(cell.getDateCellValue());
                    } else {
                        value = df.format(cell.getNumericCellValue());
                    }
                    break;
                default:
                    value = cell.getStringCellValue();
                    break;
            }
            return String.valueOf(value);
        }
    }
}

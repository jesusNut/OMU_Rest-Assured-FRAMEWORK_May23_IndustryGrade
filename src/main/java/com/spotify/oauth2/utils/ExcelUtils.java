package com.spotify.oauth2.utils;

import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.exception.FrameworkException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {

    private ExcelUtils() {
    };

    // to get entire excel data in form of List of Maps

    public static List<Map<String, String>> getExcelData(String sheetName, String workBookName) {

        String excel_sheet_path = FrameworkConstants.DATAEXCEL_LOCATION + workBookName + ".xlsx";

        FileInputStream fis;
        try {
            fis = new FileInputStream(excel_sheet_path);
        } catch (FileNotFoundException e) {

            throw new FrameworkException(
                    FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE + "==> Excel Data File could not be found !!!. Check path of Excel Data File");

        }

        Workbook workbook = null;

        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {

            throw new FrameworkException(FrameworkConstants.FRAMEWORK_EXCEPTION_GENERIC_MESSAGE
                    + "==> Exception while reading Excel Data sheet.");

        }

        Sheet sheet = workbook.getSheet(sheetName);

        // get last row number

        int total_rows = sheet.getLastRowNum();

        // get last column number

        Row row = sheet.getRow(0);

        int total_cols = row.getLastCellNum();

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> datamap = null;

        for (int i = 1; i <= total_rows; i++) {

            datamap = new HashMap<String, String>();

            for (int j = 0; j < total_cols; j++) {

                String key = sheet.getRow(0).getCell(j).getStringCellValue();

                String value = sheet.getRow(i).getCell(j).getStringCellValue();

                datamap.put(key, value);

            }

            list.add(datamap);

        }

        return list;
    }

}

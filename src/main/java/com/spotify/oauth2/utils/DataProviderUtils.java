package com.spotify.oauth2.utils;

import com.spotify.oauth2.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {

    private DataProviderUtils() {
    }

    // DATA PROVIDER with return type as a MAP : can be used
    // for any test case
    // using 'testname' column of Excel sheet.

    @DataProvider(name = "getData")
    public static Object[] getData(Method method) throws IOException {

        //getting data from ExcelUtils class method name :: getExcelData()

        List<Map<String, String>> allDatalist =
                ExcelUtils.getExcelData(FrameworkConstants.DATAEXCEL_DATASHEET_NAME,FrameworkConstants.DATAEXCEL_WORKBOOK_NAME);

        // filter the data based on TestCase name and execute =yes/no.

        List<Map<String, String>> individualTestCaselist = new ArrayList<Map<String, String>>();

        for (int i = 0; i < allDatalist.size(); i++) {

            if (isCallingMethodNameEqualsTestCaseName(method, allDatalist, i) && isTestCaseDataExecutable(allDatalist, i)) {

                individualTestCaselist.add(allDatalist.get(i));

            }

        }

        return individualTestCaselist.toArray();
    }

    //method to check if data for the test case that needs to be executed is marked as yes/no in Excel

    private static boolean isTestCaseDataExecutable(List<Map<String, String>> allDatalist, int i) {
        return allDatalist.get(i).get("execute").equalsIgnoreCase("yes");
    }

    //method to check if the calling method of DP has same name as testcase in Excel sheet or not

    private static boolean isCallingMethodNameEqualsTestCaseName(Method method, List<Map<String, String>> allDatalist, int i) {
        return method.getName().equalsIgnoreCase(allDatalist.get(i).get("testCaseName"));
    }
}

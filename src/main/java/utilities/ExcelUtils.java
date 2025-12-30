package utilities;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtils {

    static String path = System.getProperty("user.dir")
            + "/src/test/resources/testdata/LoginDatas.xlsx";

    public static Object[][] getExcelData() throws Exception {
        FileInputStream fis = new FileInputStream(new File(path));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        wb.close();
        return data;
    }
}

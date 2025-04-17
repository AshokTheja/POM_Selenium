package com.Sauce.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Test_Utils {

	public static List<String> getTestDataByTestCaseName(String testCaseName) throws IOException {
		List<String> rowData = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(
				"C:\\Users\\ashok\\eclipse-workspace\\POM_Selenium\\src\\main\\java\\com\\Sauce\\TestData\\Test_Data.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

			XSSFSheet sheet = workbook.getSheetAt(0);

			// Find the column index of "TC_Name" (assuming it's in the first row)
			Row headerRow = sheet.getRow(0);
			int testCaseIdCol = -1;

			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().equalsIgnoreCase("TC_Name")) {
					testCaseIdCol = cell.getColumnIndex();
					break;
				}
			}

			if (testCaseIdCol == -1) {
				throw new RuntimeException("TC_Name column not found in Excel");
			}

			// Find the row with matching test case name
			for (Row row : sheet) {
				Cell testCaseCell = row.getCell(testCaseIdCol);
				if (testCaseCell != null && testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
					// Get all data from this row
					for (Cell cell : row) {
						switch (cell.getCellType()) {
						case STRING:
							rowData.add(cell.getStringCellValue());
							break;
						case NUMERIC:
							rowData.add(String.valueOf(cell.getNumericCellValue()));
							break;
						case BOOLEAN:
							rowData.add(String.valueOf(cell.getBooleanCellValue()));
							break;
						default:
							rowData.add("");
						}
					}
					return rowData;
				}
			}
		}

		throw new RuntimeException("Test case '" + testCaseName + "' not found in Excel");
	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		String path = "test-output/screenshots/" + testName + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}

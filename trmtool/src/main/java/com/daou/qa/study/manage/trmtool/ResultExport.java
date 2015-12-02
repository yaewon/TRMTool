package com.daou.qa.study.manage.trmtool;

import java.io.FileInputStream;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ResultExport {

	static String[] testcase = new String[50];
	static String[] testresult = new String[50];
	
	public static void main(String[] args) {

		try {

			FileInputStream fis = new FileInputStream("C:\\Users\\intern\\Desktop\\test.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			int rowindex = 0;
			int search_row = 0;
			XSSFRow row_total;
			XSSFCell cell_jira;
			XSSFCell cell_case;
			// 시트 수 (첫번째에만 존재하므로 0을 준다)
			// 만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
			XSSFSheet sheet = workbook.getSheetAt(1);

			// 행의 수
			int rows = sheet.getPhysicalNumberOfRows();

			String jira_key = "";
			Scanner scanner = new Scanner(System.in);
			System.out.print("JIRA KEY를 입력하세요: ");
			jira_key = scanner.next();
			
			int i = 0; // 테스트케이스배열
			// System.out.println("JIRA KEY를 입력하세요: ");

			for (rowindex = 0; rowindex < rows; rowindex++) {
				// 행을읽는다
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					XSSFCell cell = row.getCell(1);
					if (cell == null) {
						continue;
					} else {
						if (cell.getStringCellValue().equals(jira_key)) {
							search_row = rowindex;
							row_total = sheet.getRow(search_row); // 지라키로 찾은 row 전체
							do {
								testcase[i] = row_total.getCell(3).getStringCellValue(); // crow의
								testresult[i] = row_total.getCell(4).getStringCellValue();
								search_row++;// 다음 row로 넘어감
								
								//다음 row비교
								i++;
								row_total = sheet.getRow(search_row); 
								cell_jira = row_total.getCell(1);
								cell_case = row_total.getCell(3);
							} while ((cell_jira.toString() == "") && (cell_case.toString() != ""));
							printFormat();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printCase() {
		for (int j = 0; j < testcase.length; j++) {
			if (testcase[j] == null) {
			} else
				System.out.println("* " + testcase[j] + " --> " + testresult[j]);
		}
	}
	
	public static void printFormat() {
		System.out.println("========== Beta Tested ==========");
		System.out.println("안녕?????????????");
		printCase();
	}

}
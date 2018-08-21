package gov.nih.nci.evs.protege;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelReport {
	private File file;
	private FileOutputStream out;
	private Workbook wb;
	private Sheet sheet;
	private Row r;
	private Cell c;
	private CellStyle cs;
	private CellStyle cs2;
	private Font f;
	private Font f2;
	private int rownum;
	private PrintWriter pw = null;
	private String outputFileText = null;
	private ArrayList<String> columnHeadings = null;
	
	public ExcelReport(String filename) {
		try {
			file = new File(filename);			
			out = new FileOutputStream(file);
			outputFileText = filename.replace(".xls", ".txt");
			wb = new HSSFWorkbook();
			sheet = wb.createSheet();
			r = null;
			c = null;
			cs = wb.createCellStyle();
			cs2 = wb.createCellStyle();
			f = wb.createFont();
			f2 = wb.createFont();
			f.setFontHeightInPoints((short) 10);
			f2.setFontHeightInPoints((short) 10);
			f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cs.setFont(f);
			cs2.setFont(f2);
			rownum = 0;
			columnHeadings = new ArrayList<String>();
			//Thanks John C.
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(outputFileText)),StandardCharsets.UTF_8),true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void printHeader(Vector<String> values) {
		r = sheet.createRow(rownum);
		for(int i=0; i < values.size(); i++) {
			columnHeadings.add(values.elementAt(i));
			c = r.createCell(i);
			c.setCellValue(values.elementAt(i));
			c.setCellStyle(cs2);
		}
		pw.write(String.join("\t",columnHeadings) + "\n");
		pw.flush();
		rownum++;		
	}
	
	public void printValues(Vector<String> values) {
		r = sheet.createRow(rownum);
		for(int i=0; i < values.size(); i++) {
			c = r.createCell(i);
			c.setCellValue(values.elementAt(i));
			c.setCellStyle(cs);
		}
		pw.write(String.join("\t", values) + "\n");
		pw.flush();
		rownum++;				
	}
	
	public void close() {
		try {
			wb.write(out);
			out.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}

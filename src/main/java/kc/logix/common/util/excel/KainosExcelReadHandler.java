package kc.logix.common.util.excel;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.binary.XSSFBSheetHandler.SheetContentsHandler;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import kainos.framework.core.support.excel.annotations.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@SuppressWarnings("resource")
public class KainosExcelReadHandler implements SheetContentsHandler {

	@Builder.Default
	private List<Map<String, String>> rows = new ArrayList<>();
	@Builder.Default
    private Map<String, String> row = new HashMap<>();
	@Builder.Default
    private int startRowNum = 0;   
    private InputStream excel;
    @Builder.Default
    private boolean rowSpan = false;
    @Builder.Default
    private List<ExcelReadRowSpan> rowSpanList = new ArrayList<>();
    @Builder.Default
	public Map<Integer, String> indexCell = new HashMap<>();
    
    /**
     * 
     * @param is
     * @param startRow
     * @return
     * @throws Exception
     */
	public KainosExcelReadHandler readExcel() throws Exception {
		IOUtils.setByteArrayMaxOverride(1000000000);
        try {
            // org.apache.poi.openxml4j.opc.OPCPackage
            OPCPackage opc = OPCPackage.open(excel);
            // org.apache.poi.xssf.eventusermodel.XSSFReader
            XSSFReader xssfReader = new XSSFReader(opc);
            // org.apache.poi.xssf.model.StylesTable
            StylesTable styles = xssfReader.getStylesTable();
            // org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opc);
            // 엑셀의 시트를 하나만 가져오기입니다.
            InputStream inputStream = xssfReader.getSheetsData().next();
            // org.xml.sax.InputSource
            InputSource inputSource = new InputSource(inputStream);
            // org.xml.sax.Contenthandler
            ContentHandler handle = new XSSFSheetXMLHandler(styles, strings, KainosExcelReadHandler.this, false);
            // XMLReader xmlReader = SAXHelper.newXMLReader(); // deprecated
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setNamespaceAware(true);
            SAXParser parser    = saxParserFactory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();
            xmlReader.setContentHandler(handle);
            xmlReader.parse(inputSource);
            
            if(rowSpan) {
            	indexCellSetting();
				XSSFSheet sheet = new XSSFWorkbook(opc).getSheetAt(0);
    	        Iterator<Row> rowIterator = sheet.iterator();
    	        while (rowIterator.hasNext()) {
    	        	Row row = rowIterator.next();
    	        	Iterator<Cell> cellIterator = row.cellIterator();
    	        	 while (cellIterator.hasNext()) {
    	        		 Cell cell = cellIterator.next();
    	        		 for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
    	 	        		CellRangeAddress region = sheet.getMergedRegion(i);
    	 	        		int colIndex = region.getFirstColumn();
    	 	                int rowNum = region.getFirstRow(); 
    	 	                if (rowNum == cell.getRowIndex() && colIndex == cell.getColumnIndex()) {
    	 	                	rowSpanList.add(ExcelReadRowSpan.builder()
    	 	                			.ColumnIndex(colIndex)
    	 	                			.rowIndex(rowNum)
    	 	                			.ColumnValue(sheet.getRow(rowNum).getCell(colIndex))
    	 	                			.startRowNum(region.getFirstRow() - startRowNum)
    	 	                			.rowspanCnt(region.getLastRow() - region.getFirstRow())
    	 	                			.build());
    	 	                }
    	 	        	}
    	        	 }
    	        }
            }
            
            inputStream.close();
            opc.close();
        } catch (Exception e) {
            throw e;
        }
        return KainosExcelReadHandler.this;
    }
	
	@Override
	public void endRow(int rowNum) {
		if ( rowNum >= startRowNum ) {
			Map<String, String> map = new HashMap<>();
			map.putAll(row);
			rows.add(map);
		}
		row.clear();
	}

	@Override
	public void cell(String cellReference, String formattedValue, XSSFComment comment) {
		row.put(cellReference.replaceAll("[^a-zA-Z]", "").toUpperCase(), formattedValue);
	}

	@Override
	public void hyperlinkCell(String cellReference, String formattedValue, String url, String toolTip, XSSFComment comment) {
	}

	public List<Map<String, String>> getRows() {
		return rows;
	}
	
	/**
	 * 데이터 바인딩
	 * @param <T>
	 * @param dateRow
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> T objectCoyp(Map<String, String> dateRow, Class<?> clazz) throws Exception {
		Object t = createResultInstance(clazz);
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for (java.lang.reflect.Field field : fields) {
			field.setAccessible(true);
			Field annotation = field.getAnnotation(Field.class);
			if ( annotation == null ) continue;
			String index = annotation.value();
			if(dateRow != null) {
				try {
					/* 포멧 변경 하겠다. */
					String data = dateRow.get(index.toUpperCase());
//					if(!annotation.format().equalsIgnoreCase("")) {
//						Class<?> formatTypeCls = annotation.formatType();
//						if(formatTypeCls.equals(Date.class)){
//							SimpleDateFormat formatter = new SimpleDateFormat(annotation.format());
//							Class<?> returnClss = annotation.returnType();
//							if(returnClss.equals(Date.class))
//								field.set(t, formatter.parse(data));
//						}
//					} else {
						field.set(t, dateRow.get(index.toUpperCase()));
//					}
				}catch (Exception e) {
					
				}
			}
		}
		return (T) t;
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private Object createResultInstance(Class<?> clazz) throws InvocationTargetException, NoSuchMethodException {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param excelData
	 * @throws Exception
	 */
	public <T> void rowSapnCoyp(List<T> excelData) throws Exception {
		for (Iterator<ExcelReadRowSpan> iterator = rowSpanList.iterator(); iterator.hasNext();) {
			ExcelReadRowSpan excelReadRowSpan = (ExcelReadRowSpan) iterator.next();
			int roopCnt = excelReadRowSpan.getStartRowNum() + excelReadRowSpan.getRowspanCnt();
			for (int i = excelReadRowSpan.getStartRowNum(); i <= roopCnt; i++) {
				Object excel = excelData.get(i);
				java.lang.reflect.Field[] fields = excel.getClass().getDeclaredFields();
				for (java.lang.reflect.Field field : fields) {
					if(field.isAnnotationPresent(kainos.framework.core.support.excel.annotations.Field.class)) {
						kainos.framework.core.support.excel.annotations.Field anno = field.getAnnotation(kainos.framework.core.support.excel.annotations.Field.class);
						String cell = indexCell.get(excelReadRowSpan.getColumnIndex());
						if(cell.equalsIgnoreCase(anno.value())) {
							field.setAccessible(true);
//							if(anno.formatType().equals(Date.class)){
//								field.set(excel, excelReadRowSpan.getColumnValue().getDateCellValue());
//							}
//							else if(anno.formatType().equals(Double.class)){
//								field.set(excel, excelReadRowSpan.getColumnValue().getNumericCellValue());
//							}
//							else {
								field.set(excel, excelReadRowSpan.getColumnValue().toString());
//							}
							break;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void startRow(int rowNum) {}
	
	public void rowSpan(boolean rowSpan) {
		this.rowSpan = rowSpan;
	}
	
	public void indexCellSetting(){
    	indexCell.put(0,"A"); 
		indexCell.put(1,"B"); 
		indexCell.put(2,"C"); 
		indexCell.put(3,"D"); 
		indexCell.put(4,"E"); 
		indexCell.put(5,"F"); 
		indexCell.put(6,"G"); 
		indexCell.put(7,"H"); 
		indexCell.put(8,"I"); 
		indexCell.put(9,"J"); 
		indexCell.put(10,"K"); 
		indexCell.put(11,"L"); 
		indexCell.put(12,"M"); 
		indexCell.put(13,"N"); 
		indexCell.put(14,"O"); 
		indexCell.put(15,"P"); 
		indexCell.put(16,"Q"); 
		indexCell.put(17,"R"); 
		indexCell.put(18,"S"); 
		indexCell.put(19,"T"); 
		indexCell.put(20,"U"); 
		indexCell.put(21,"V"); 
		indexCell.put(22,"W"); 
		indexCell.put(23,"X"); 
		indexCell.put(24,"Y"); 
		indexCell.put(25,"Z"); 
		indexCell.put(26,"AA"); 
		indexCell.put(27,"AB"); 
		indexCell.put(28,"AC"); 
		indexCell.put(29,"AD"); 
		indexCell.put(30,"AE"); 
		indexCell.put(31,"AF"); 
		indexCell.put(32,"AG"); 
		indexCell.put(33,"AH"); 
		indexCell.put(34,"AI"); 
		indexCell.put(35,"AJ"); 
		indexCell.put(36,"AK"); 
		indexCell.put(37,"AL"); 
		indexCell.put(38,"AM"); 
		indexCell.put(39,"AN"); 
		indexCell.put(40,"AO"); 
		indexCell.put(41,"AP"); 
		indexCell.put(42,"AQ"); 
		indexCell.put(43,"AR"); 
		indexCell.put(44,"AS"); 
		indexCell.put(45,"AT"); 
		indexCell.put(46,"AU"); 
		indexCell.put(47,"AV"); 
		indexCell.put(48,"AW"); 
		indexCell.put(49,"AX"); 
		indexCell.put(50,"AY"); 
		indexCell.put(51,"AZ"); 
		indexCell.put(52,"BA"); 
		indexCell.put(53,"BB"); 
		indexCell.put(54,"BC"); 
		indexCell.put(55,"BD"); 
		indexCell.put(56,"BE"); 
		indexCell.put(57,"BF"); 
		indexCell.put(58,"BG"); 
		indexCell.put(59,"BH"); 
		indexCell.put(60,"BI"); 
		indexCell.put(61,"BJ"); 
		indexCell.put(62,"BK"); 
		indexCell.put(63,"BL"); 
		indexCell.put(64,"BM"); 
		indexCell.put(65,"BN"); 
		indexCell.put(66,"BO"); 
		indexCell.put(67,"BP"); 
		indexCell.put(68,"BQ"); 
		indexCell.put(69,"BR"); 
		indexCell.put(70,"BS"); 
		indexCell.put(71,"BT"); 
		indexCell.put(72,"BU"); 
		indexCell.put(73,"BV"); 
		indexCell.put(74,"BW"); 
		indexCell.put(75,"BX"); 
		indexCell.put(76,"BY"); 
		indexCell.put(77,"BZ"); 
    }
	
}

package kc.logix.common.util.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelReadRowSpan {

	private int rowIndex;
	private int ColumnIndex;
	private XSSFCell ColumnValue;
	private int rowspanCnt;
//	private int firstRow;
//	private int lastRow;
	private int startRowNum;
	
}

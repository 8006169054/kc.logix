var tableName = '#port-table';
$( document ).ready(function() {
   	portTableInit();
});

/**
 * 조회
 */
async function search() {
	$(tableName).clearGridData();
	let response = await requestApi('GET', '/api/management/website-terminal-code', {hblNo : $('#hblNo').val(), arrivalNotice : $('#arrivalNotice').val()});
	$(tableName).searchData(response.data, {editor: true});
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['','uuid', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', 'DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'RETURN DEPOT', 'TOTAL DEM', 'DEM RECEIVED', 'DEM RCVD', 'COMMISSION DEM', 'DEM COMMISSION', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true},
	       	{ name: 'sales', 				width: 50, 		align:'center'},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center'},
	       	{ name: 'arrivalNotice',		width: 70, 		align:'center'},
	       	{ name: 'invoice', 				width: 70, 		align:'center'},
	    	{ name: 'concine', 				width: 150, 	align:'center'},
	    	{ name: 'profitDate', 			width: 90, 		align:'center'},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center'},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center'},
	    	{ name: 'quantity', 			width: 50, 		align:'center'},
	    	{ name: 'partner', 				width: 120, 	align:'center'},
	    	{ name: 'tankNo', 				width: 140, 	align:'center'},
	    	{ name: 'term', 				width: 80, 		align:'center'},
	    	{ name: 'item', 				width: 250, 	align:'center'},
	    	{ name: 'vesselVoyage', 		width: 200, 	align:'center'},
	    	{ name: 'carrier', 				width: 80, 		align:'center'},
	    	{ name: 'mblNo', 				width: 140, 	align:'center'},
	    	{ name: 'hblNo', 				width: 140, 	align:'center'},
	    	{ name: 'pol', 					width: 100, 	align:'center'},
	    	{ name: 'pod', 					width: 100, 	align:'center'},
	    	{ name: 'terminal', 			width: 150, 	align:'center'},
	    	{ name: 'etd', 					width: 90, 		align:'center'},
	    	{ name: 'eta', 					width: 90, 		align:'center'},
	       	{ name: 'ata', 					width: 90, 		align:'center'},
	       	{ name: 'remark', 				width: 250, 	align:'center'},
	       	{ name: 'ft', 					width: 70, 		align:'center'},
	       	{ name: 'demRate', 				width: 80, 		align:'center'},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center'},
	       	{ name: 'estimateReturnDate', 	width: 160, 	align:'center'},
	       	{ name: 'returnDate', 			width: 100, 	align:'center'},
	       	{ name: 'returnDepot', 			width: 80, 		align:'center'},
	       	{ name: 'totalDem', 			width: 100, 	align:'center'},
	       	{ name: 'demReceived', 			width: 80, 		align:'center'},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center'},
	       	{ name: 'demPrch', 				width: 100, 	align:'center'},
	       	{ name: 'demSales', 			width: 100, 	align:'center'},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center'},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center'}
	   	],
		height: 530, 
		width: '100%',
//		dblEdit : true,
//		frozen: true,
		multiselect : true, // 그리드 왼쪽부분에 체크 박스가 생겨 다중선택이 가능해진다.
 		multiboxonly : true, // 다중선택을 단일 선택으로 제한
//		delselect: true,
		onCellSelect : function(rowid, iCol, cellcontent, e) {
//			console.log('onCellSelect');
		}
	});
}

async function anSend(){
	var rowData = ComSelectRow(tableName);
	await requestFileDownload('POST', '/api/management/arrival-notice-send-mail', rowData, 'ArrivalNoticeTemplate_' + rowData.hblNo + '.eml');
}
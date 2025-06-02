var tableName = '#an-table';
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
	   	colNames: ['', 'uuid', 'A/N', 'HBL NO.',"Q'ty", 'Tank no.', 'Term', 'Name', 'Date', 'Location', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'POL', 'POD', 'ETD', 'ETA', 'F/T', 'DEM RATE', 'END OF F/T'],
	   	colModel: [
			{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : true,  frozen:true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true, 	frozen:true},
	       	{ name: 'arrivalNotice',		width: 70, 		align:'center',		rowspan: true,	frozen:true, formatter: arrivalNoticeFn},
	       	{ name: 'hblNo', 				width: 140, 	align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'quantity', 			width: 50, 		align:'center',		rowspan: true},
	    	{ name: 'tankNo', 				width: 150, 	align:'center'},
	    	{ name: 'term', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'item',					width: 250, 	align:'center',		rowspan: true},
			{ name: 'cargoDate', 			width: 80, 		align:'center',		rowspan: true},
			{ name: 'location', 			width: 100, 	align:'center',		rowspan: true},
	    	{ name: 'vesselVoyage', 		width: 200, 	align:'center',		rowspan: true},
	    	{ name: 'carrier', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'mblNo', 				width: 140, 	align:'center',		rowspan: true},
	    	{ name: 'pol', 					width: 100, 	align:'center',		rowspan: true},
	    	{ name: 'pod', 					width: 100, 	align:'center'},
	    	{ name: 'etd', 					width: 90, 		align:'center'},
	    	{ name: 'eta', 					width: 90, 		align:'center'},
	       	{ name: 'ft', 					width: 70, 		align:'center'},
	       	{ name: 'demRate', 				width: 80, 		align:'center'},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center'}
	   	],
		height: 530, 
		width: '100%',
		dblEdit : false,
		frozen: true
//		multiselect : true, // 그리드 왼쪽부분에 체크 박스가 생겨 다중선택이 가능해진다.
// 		multiboxonly : true // 다중선택을 단일 선택으로 제한
	});
	
	$(tableName).jqGrid('setGroupHeaders', {
				useColSpanStyle: true,
				groupHeaders: [
                                {startColumnName:'item', numberOfColumns: 3, titleText: 'Item' }
                                
                              ]
		});
}

function arrivalNoticeFn (cellvalue, options, rowObject ){
	if(emptyChange(rowObject.arrivalNotice) === '')
		return '<input type="radio" name="anRadio" id="anRadio" value="' + rowObject.hblNo + '" />';
	else
		return rowObject.arrivalNotice;
}

async function anSend(){
	var rowData = ComSelectRow(tableName);
	await requestFileDownload('POST', '/api/management/arrival-notice-send-mail', rowData, 'ArrivalNoticeTemplate_' + rowData.hblNo + '.eml');
}
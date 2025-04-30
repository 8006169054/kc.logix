var tableName = '#port-table';
$( document ).ready(function() {
   	portTableInit();
});

/**
 * 조회
 */
async function search() {
	$(tableName).clearGridData();
	let response = await requestApi('GET', '/api/basic/website-terminal-code', {hblNo : $('#hblNo').val(), arrivalNotice : $('#arrivalNotice').val()});
	$(tableName).searchData(response.data, {editor: true});
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['flag','uuid', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', 'DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'RETURN DEPOT', 'TOTAL DEM', 'DEM RECEIVED', 'DEM RCVD', 'COMMISSION DEM', 'DEM COMMISSION', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : false,	frozen:true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true,	frozen:true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'arrivalNotice',		width: 70, 		align:'center',		rowspan: true,	frozen:true, formatter: arrivalNoticeFn},
	       	{ name: 'invoice', 				width: 70, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'concine', 				width: 150, 	align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'profitDate', 			width: 90, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true, formatter: domesticSalesFn},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true, formatter: foreignSalesFn},
	    	{ name: 'quantity', 			width: 50, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'partner', 				width: 120, 	align:'center',		frozen:true,	cellattr:idColorFmt},
	    	{ name: 'tankNo', 				width: 140, 	align:'center',		frozen:true},
	    	{ name: 'term', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'item', 				width: 250, 	align:'center',		rowspan: true},
	    	{ name: 'vesselVoyage', 		width: 200, 	align:'center',		rowspan: true},
	    	{ name: 'carrier', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'mblNo', 				width: 140, 	align:'center',		rowspan: true},
	    	{ name: 'hblNo', 				width: 140, 	align:'center',		rowspan: true},
	    	{ name: 'pol', 					width: 100, 	align:'center',		rowspan: true},
	    	{ name: 'pod', 					width: 100, 	align:'center', formatter:podLinkFn},
	    	{ name: 'terminal', 			width: 150, 	align:'center', formatter:terminalFn},
	    	{ name: 'etd', 					width: 90, 		align:'center'},
	    	{ name: 'eta', 					width: 90, 		align:'center',	cellattr:idColorFmt},
	       	{ name: 'ata', 					width: 90, 		align:'center'},
	       	{ name: 'remark', 				width: 250, 	align:'center',		rowspan: true},
	       	{ name: 'ft', 					width: 70, 		align:'center'},
	       	{ name: 'demRate', 				width: 80, 		align:'center'},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center'},
	       	{ name: 'estimateReturnDate', 	width: 160, 	align:'center'},
	       	{ name: 'returnDate', 			width: 100, 	align:'center', editable: true, edittype: "date"},
	       	{ name: 'returnDepot', 			width: 80, 		align:'center'},
	       	{ name: 'totalDem', 			width: 100, 	align:'center', formatter: totalDemFn},
	       	{ name: 'demReceived', 			width: 80, 		align:'center'},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center'},
	       	{ name: 'demPrch', 				width: 100, 	align:'center', formatter: demPrchFn},
	       	{ name: 'demSales', 			width: 100, 	align:'center', formatter: demSalesFn},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center'},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center'}
	   	],
		height: 530, 
		width: '100%',
		dblEdit : true,
		frozen: true,
//		delselect: true,
		onCellSelect : function(rowid, iCol, cellcontent, e) {
			consoel.log(rowid, iCol, cellcontent, e);
		}
	});
}


function arrivalNoticeFn (cellvalue, options, rowObject ){
	if(cellvalue === '1')
		return 'OK';
	else{
		return '<input type="radio" id="jqArrivalNotice" name="jqArrivalNotice" value="' + options.rowId + '" />';
	}
}

/** 국내 매출 */
function domesticSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== '-' && cellvalue !== ''){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/** 국내 매출 */
function foreignSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== '-' && cellvalue !== ''){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  TOTAL DEM */
function totalDemFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A' && cellvalue !== ''){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  DEM(USD)-매입 */
function demPrchFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A' && cellvalue !== ''){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  DEM 매출 */
function demSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A' && cellvalue !== ''){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  터미널 링크 */
function terminalFn (cellvalue, options, rowObject ){
	if(cellvalue === '')
		return cellvalue;
	else
		return '<a href="' + rowObject.homepage + '" target="_blank">' + cellvalue + '</a>';
}

/**  터미널 링크 */
function podLinkFn (cellvalue, options, rowObject ){
	if(rowObject.pod === '')
		return rowObject.pod;
	else
//		return '<a href="javascript:openterminal()">' + rowObject.pod + '</a>';
		return '<a href="#" data-toggle="modal" data-target="#terminal-popup" data-id="' + rowObject.pod + '">' + rowObject.pod + '</a>';
		
		
}

function idColorFmt( rowId, tv, rawObject, cm, rdata) {
	switch( cm.name) {
		case 'partner':
			return 'style="background-color:rgb(0 176 240)"';
			break;

		case 'eta':
			return 'style="color:red"';
			break;

		default:
			return  "";
	}
}

async function save(){
	var saveData = $(tableName).saveGridData();
	if(saveData.length === 0)
		alertMessage(getMessage('0001'), 'info');
	else{
		await requestApi('POST', '/api/basic/save-port', saveData, {successFn : portSaveFn, errorFn : portSaveFn});
	}
}

function portSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
}

async function anSend(){
	var rowData = ComRowData(tableName, $('#jqArrivalNotice').val());
	await requestFileDownload('POST', '/api/basic/arrival-notice-send-mail', rowData, 'ArrivalNoticeTemplate_' + rowData.hblNo + '.eml');
}

function frozenCelHide(){
	var frozenCelNotVal = [];
	var frozenCelVal = $('#frozenCel').val();
	$("#frozenCel > option").each(function() {
		frozenCelNotVal.push(this.value);
	});
	frozenCelNotVal = frozenCelNotVal.filter(x => !frozenCelVal.includes(x));
	$(tableName).hideCol(frozenCelVal);
	$(tableName).showCol(frozenCelNotVal);
}

function gridClear(){
	console.log('clear');
	$(tableName).clearGridData();
}

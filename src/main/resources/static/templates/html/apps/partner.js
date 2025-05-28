var tableName = '#port-table';
var hiddenCelVal = [];
$( document ).ready(function() {
	gridColsearch();
});

async function gridColsearch() {
	let response = await requestApi('GET', '/api/partner/home/website-terminal-code-grid-col');
	if(response.data != ''){
		let loopCol = [];
		if(response.data.indexOf(",") > 0){
			loopCol = response.data.split(",");
		}else{
			loopCol.push(response.data);
		}
		for(var i=0; i < loopCol.length; i++){
			hiddenCelVal[loopCol[i]] = true;
		}
	}
	loopCol = null;
	response = null;
	portTableInit();
}

/**
 * 조회
 */
async function search() {
	$(tableName).clearGridData();
	let response = await requestApi('GET', '/api/partner/home/website-terminal-code', {hblNo : $('#hblNo').val()});
	$(tableName).searchData(response.data);
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['HBL NO.', 'MBL NO.', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', 'DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'RETURN DEPOT', 'TOTAL DEM', 'DEM RECEIVED', 'DEM RCVD', 'COMMISSION DEM', 'DEM COMMISSION', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
			{ name: 'hblNo', 				width: 140, 	align:'center',		rowspan: true,	frozen:true},
			{ name: 'mblNo', 				width: 140, 	align:'center',		hidden : hiddenCelVal['mblNo'],		rowspan: true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		hidden : hiddenCelVal['sales'],		rowspan: true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		hidden : hiddenCelVal['carryoverSales'],		rowspan: true},
	       	{ name: 'arrivalNotice',		width: 70, 		align:'center',		hidden : hiddenCelVal['arrivalNotice'],		rowspan: true, formatter: arrivalNoticeFn},
	       	{ name: 'invoice', 				width: 70, 		align:'center',		hidden : hiddenCelVal['invoice'],		rowspan: true, formatter: invoiceFn},
	    	{ name: 'concine', 				width: 150, 	align:'center',		hidden : hiddenCelVal['concine'],		rowspan: true},
	    	{ name: 'profitDate', 			width: 90, 		align:'center',		hidden : hiddenCelVal['profitDate'],		rowspan: true},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center',		hidden : hiddenCelVal['domesticSales'],		rowspan: true, formatter: domesticSalesFn},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center',		hidden : hiddenCelVal['foreignSales'],		rowspan: true, formatter: foreignSalesFn},
	    	{ name: 'quantity', 			width: 50, 		align:'center',		hidden : hiddenCelVal['quantity'],		rowspan: true},
	    	{ name: 'partner', 				width: 120, 	align:'center',		hidden : hiddenCelVal['partner'],		cellattr:idColorFmt},
	    	{ name: 'tankNo', 				width: 140, 	align:'center',		hidden : hiddenCelVal['tankNo']},
	    	{ name: 'term', 				width: 80, 		align:'center',		hidden : hiddenCelVal['term'],		rowspan: true},
	    	{ name: 'item', 				width: 250, 	align:'center',		hidden : hiddenCelVal['item'],		rowspan: true},
	    	{ name: 'vesselVoyage', 		width: 200, 	align:'center',		hidden : hiddenCelVal['vesselVoyage'],		rowspan: true},
	    	{ name: 'carrier', 				width: 80, 		align:'center',		hidden : hiddenCelVal['carrier'],		rowspan: true},
	    	{ name: 'pol', 					width: 100, 	align:'center',		hidden : hiddenCelVal['pol'],		rowspan: true},
	    	{ name: 'pod', 					width: 100, 	align:'center',		hidden : hiddenCelVal['pod']},
	    	{ name: 'terminal', 			width: 150, 	align:'center',		hidden : hiddenCelVal['terminal']},
	    	{ name: 'etd', 					width: 90, 		align:'center',		hidden : hiddenCelVal['etd']},
	    	{ name: 'eta', 					width: 90, 		align:'center',		hidden : hiddenCelVal['eta'],		cellattr:idColorFmt},
	       	{ name: 'ata', 					width: 90, 		align:'center', 	hidden : hiddenCelVal['ata']},
	       	{ name: 'remark', 				width: 250, 	align:'center', 	hidden : hiddenCelVal['remark'],		rowspan: true},
	       	{ name: 'ft', 					width: 70, 		align:'center',		hidden : hiddenCelVal['ft']},
	       	{ name: 'demRate', 				width: 80, 		align:'center',		hidden : hiddenCelVal['demRate']},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center',		hidden : hiddenCelVal['endOfFt']},
	       	{ name: 'estimateReturnDate', 	width: 160, 	align:'center',		hidden : hiddenCelVal['estimateReturnDate']},
	       	{ name: 'returnDate', 			width: 100, 	align:'center',		hidden : hiddenCelVal['returnDate']},
	       	{ name: 'returnDepot', 			width: 140, 	align:'center',		hidden : hiddenCelVal['returnDepot']},
	       	{ name: 'totalDem', 			width: 100, 	align:'center', 	hidden : hiddenCelVal['totalDem'],		formatter: totalDemFn},
	       	{ name: 'demReceived', 			width: 100, 	align:'center',		hidden : hiddenCelVal['demReceived']},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center',		hidden : hiddenCelVal['demRcvd']},
	       	{ name: 'demPrch', 				width: 140, 	align:'center', 	hidden : hiddenCelVal['demPrch'],		formatter: demPrchFn},
	       	{ name: 'demSales', 			width: 140, 	align:'center', 	hidden : hiddenCelVal['demSales'],		formatter: demSalesFn},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center',		hidden : hiddenCelVal['depotInDate']},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center',		hidden : hiddenCelVal['repositionPrch']}
	   	],
		height: 530, 
//		frozen: true,
		width: '100%',
		frozen: true
	});
}


function invoiceFn (cellvalue, options, rowObject ){
	if(cellvalue === '1')
		return 'OK';
	else{
		return '';
	}
}

function arrivalNoticeFn (cellvalue, options, rowObject ){
	if(cellvalue === '1')
		return 'OK';
	else{
		return '';
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

async function excelDown(){
	await requestFileDownload('GET', '/api/partner/home/website-terminal-code-exceldown', {hblNo : $('#hblNo').val()}, '서버엘셀다운로드.xlsx');
}

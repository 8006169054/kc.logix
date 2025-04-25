var tableName = '#port-table';
$( document ).ready(function() {
   	portTableInit();
    $('input[type="file"]').change(function() { 
    	upload(this);
	});
	
});

async function upload(customFile) {
	try{
		var frm = new FormData();
	    frm.append('upload', customFile.files[0]);
	    response = await requestFormDataApi('POST', '/api/basic/excel-upload', frm);
	    $(tableName).clearGridData();
		$(tableName).searchData(response.data, {frozen:true, jqFlag: C});
	}catch (error) {
	}finally {
	  document.getElementById("customFile").value=null;
	}
}

/**
 * 조회
 */
async function search() {
	$(tableName).clearGridData();
	let response = await requestApi('GET', '/api/basic/website-terminal-code', {hblNo : $('#hblNo').val()});
	$(tableName).searchData(response.data, {frozen:true});
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['flag','uuid', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', '	DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'DEM DAYS', 'TOTAL DEM', '반납DEPOT', 'DEM RCVD', 'DEM(USD)-매입', 'DEM 매출', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : false,	frozen:true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true,	frozen:true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'arrivalNotice', 		width: 70, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'invoice', 				width: 70, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'concine', 				width: 150, 	align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'profitDate', 			width: 90, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'quantity', 			width: 50, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'partner', 				width: 120, 	align:'center',		frozen:true},
	    	{ name: 'tankNo', 				width: 140, 	align:'center',		frozen:true},
	    	{ name: 'term', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'item', 				width: 250, 	align:'center',		rowspan: true},
	    	{ name: 'vesselVoyage', 		width: 200, 	align:'center',		rowspan: true},
	    	{ name: 'carrier', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'mblNo', 				width: 140, 	align:'center',		rowspan: true},
	    	{ name: 'hblNo', 				width: 140, 	align:'center',		rowspan: true},
	    	{ name: 'pol', 					width: 100, 	align:'center',		rowspan: true},
	    	{ name: 'pod', 					width: 100, 	align:'center'},
	    	{ name: 'terminal', 			width: 150, 	align:'center'},
	    	{ name: 'etd', 					width: 90, 		align:'center'},
	    	{ name: 'eta', 					width: 90, 		align:'center'},
	       	{ name: 'ata', 					width: 90, 		align:'center'},
	       	{ name: 'remark', 				width: 250, 	align:'center',		rowspan: true},
	       	{ name: 'ft', 					width: 70, 		align:'center'},
	       	{ name: 'demRate', 				width: 80, 		align:'center'},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center'},
	       	{ name: 'estimateReturnDate', 	width: 160, 	align:'center'},
	       	{ name: 'returnDate', 			width: 100, 	align:'center', editable: true, edittype: "date"},
	       	{ name: 'demDays', 				width: 80, 		align:'center'},
	       	{ name: 'totalDem', 			width: 100, 	align:'center'},
	       	{ name: 'returnDepot', 			width: 80, 		align:'center'},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center'},
	       	{ name: 'demPrch', 				width: 100, 	align:'center'},
	       	{ name: 'demSales', 			width: 100, 	align:'center'},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center'},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center'}
	   	],
		height: 530, 
		width: '100%',
		dblEdit : true,
		rownumbers : true,
//		delselect: true,
//		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(portData, ComRowData(this.id, iRow));
//			$('#add').click();
//			console.log('ondblClickRow', ComMultiSelectRow(tableName));
		}
	});
}

/** 국내 매출 */
function domesticSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== '-'){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/** 국내 매출 */
function foreignSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== '-'){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  TOTAL DEM */
function totalDemFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A'){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  DEM(USD)-매입 */
function demPrchFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A'){
		return usMoneyConversion('US$', cellvalue, '');
	} 
	return cellvalue;
}

/**  DEM 매출 */
function demSalesFn (cellvalue, options, rowObject ){
	if(cellvalue !== 'N/A'){
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
//			
//		case 3:
//			return 'style="background-color:white"';
//			break;

		default:
			return  "";
	}
}

async function fileOpen(){
	$('#customFile').click();
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

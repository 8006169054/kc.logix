var tableName = '#port-table';
$( document ).ready(function() {
   portTableInit();
   
    $('input[type="file"]').change(function() { 
    	upload(this);
	});

});

async function upload(customFile) {
	var frm = new FormData();
    frm.append('upload', customFile.files[0]);
    response = await requestFormDataApi('POST', '/api/basic/excel-upload', frm);
    $(tableName).clearGridData();
	$(tableName).searchData(response.data, {frozen:true});
	document.getElementById("customFile").value=null;
}

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/port', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['jqFlag', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', '	DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'DEM DAYS', 'TOTAL DEM', '반납DEPOT', 'DEM RCVD', 'DEM(USD)-매입', 'DEM 매출', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 70,		align:'center', 	hidden : false,	frozen:true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'arrivalNotice', 		width: 70, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'invoice', 				width: 70, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'concine', 				width: 150, 	align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'profitDate', 			width: 90, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
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
	    	{ name: 'pod', 					width: 100, 	align:'center'},
	    	{ name: 'terminal', 			width: 150, 	align:'center'},
	    	{ name: 'etd', 					width: 90, 		align:'center'},
	    	{ name: 'eta', 					width: 90, 		align:'center',	cellattr:idColorFmt},
	       	{ name: 'ata', 					width: 90, 		align:'center'},
	       	{ name: 'remark', 				width: 250, 	align:'center',		rowspan: true},
	       	{ name: 'ft', 					width: 70, 		align:'center'},
	       	{ name: 'demRate', 				width: 80, 		align:'center'},
	       	{ name: 'endOfFt', 				width: 90, 		align:'center'},
	       	{ name: 'estimateReturnDate', 	width: 160, 	align:'center'},
	       	{ name: 'returnDate', 			width: 100, 	align:'center'},
	       	{ name: 'demDays', 				width: 80, 		align:'center'},
	       	{ name: 'totalDem', 			width: 100, 	align:'center'},
	       	{ name: 'returnDepot', 			width: 80, 		align:'center'},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center'},
	       	{ name: 'demPrch', 				width: 100, 	align:'center'},
	       	{ name: 'demSales', 			width: 100, 	align:'center'},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center'},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center'}
	   	],
		height: 650, 
		width: '100%',
		delselect: true,
		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(portData, ComRowData(this.id, iRow));
//			$('#add').click();
			console.log('ondblClickRow', ComMultiSelectRow(tableName));
		}
	});
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

async function deletePort(){
	const selectObjects = ComMultiSelectRow(tableName);
	if(selectObjects.length === 0)
		alertMessage(getMessage('0000'), 'info');
	else{
		await requestApi('DELETE', '/api/basic/port', selectObjects, {successFn : portDeleteSaveFn, errorFn : portDeleteSaveFn});
	}
}

function portDeleteSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
 }
var tableName = '#port-table';
$( document ).ready(function() {
   	portTableInit();
   	  searchPartnerAutocomplete();
   	  searchCargoAutocomplete();
});

var partnerList = [];
var carGoList = [];

/**
 * 조회
 */
async function search() {
	$(tableName).clearGridData();
	let response = await requestApi('GET', '/api/management/website-terminal-code', {hblNo : $('#shblNo').val(), arrivalNotice : $('#sarrivalNotice').val()});
	$(tableName).searchData(response.data, {editor: true});
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['','cargo','uuid', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', 'DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'RETURN DEPOT', 'TOTAL DEM', 'DEM RECEIVED', 'DEM RCVD', 'COMMISSION DEM', 'DEM COMMISSION', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : false,	frozen:true},
	   		{ name: 'cargo',				width: 100,		align:'center', 	rowspan: true,	editable : true, hidden : false,	frozen:true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true,	frozen:true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		rowspan: true,	frozen:true, editable: true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'arrivalNotice',		width: 70, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'invoice', 				width: 70, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'concine', 				width: 150, 	align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'profitDate', 			width: 90, 		align:'center',		rowspan: true,	frozen:true, editable: true, edittype: "date"},
	    	{ name: 'domesticSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'foreignSales', 		width: 80, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'quantity', 			width: 50, 		align:'center',		rowspan: true,	frozen:true},
	    	{ name: 'partner',				width: 120, 	align:'center', 	rowspan: false,  frozen:true, editable : true, editable : true, edittype: 'text', editoptions: {
				dataInit:function(elem) {
					$(elem).autocomplete({
						source: partnerList,
						delay: 100,
						autoFocus: true,
						minChars: 1,
				        select: function (event, ui) {
//				            $(e).val(ui.item.label);
//				            $("input#birthPlaceId").val(ui.item.value);
				        }
					});
				}
			}},
	    	{ name: 'tankNo', 				width: 140, 	align:'center',		frozen:true},
	    	{ name: 'term', 				width: 80, 		align:'center',		rowspan: true},
	    	{ name: 'item',					width: 220, 	align:'center', 	rowspan: true, editable : true, edittype: 'text', editoptions: {
				dataInit:function(elem) {
					$(elem).autocomplete({
						source: carGoList,
						delay: 100,
						autoFocus: true,
						minChars: 1,
				        select: function (event, ui) {
							ComSetCellData(tableName, ComSelectIndex(tableName), 2, ui.item.code, true);
							$(elem).autocomplete( "close" );
				        }
//				        ,
//				        close : function(event) { // 자동완성 창 닫아질 때의 이벤트
//				            ComSaveCell(tableName, ComSelectIndex(tableName), 2);
//				        }
					});
				}
			}},
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
		dblEdit : true,
		frozen: true,
		delselect: true,
//		multiselect: true,
		onCellSelect : function(rowid, iCol, cellcontent, e) {
		}
	});
}

async function searchPartnerAutocomplete(){
	var response = await requestApi('GET', '/api/mdm/partner/autocomplete');
	if(response.common.status === 'S'){
		partnerList = response.data;
	}
}

async function searchCargoAutocomplete(){
	var response = await requestApi('GET', '/api/mdm/cargo/autocomplete');
	if(response.common.status === 'S'){
		carGoList = response.data;
	}
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

async function save(){
	var saveData = $(tableName).saveGridData();
	if(saveData.length === 0)
		alertMessage(getMessage('0001'), 'info');
	else{
		await requestApi('POST', '/api/management/save-port', saveData, {successFn : portSaveFn, errorFn : portSaveFn});
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
	$(tableName).refreshFrozen();
}

function gridClear(){
	console.log('clear');
	$(tableName).clearGridData();
}

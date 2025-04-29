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
	let response = await requestApi('GET', '/api/basic/website-terminal-code', {hblNo : $('#hblNo').val(), arrivalNotice : $('#arrivalNotice').val()});
	$(tableName).searchData(response.data, {frozen:true, editor: true});
	response = null;
}

function portTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['flag','uuid', '매출', '이월 매출', 'A/N&EDI', 'INVOICE', 'CNEE', 'PROFIT DATE', '국내매출', '해외매출', "Q'ty", 'Partner', 'Tank no.', 'Term', 'ITEM', 'Vessel / Voyage', 'Carrier', 'MBL NO.', 'HBL NO.', 'POL', 'POD', 'TERMINAL', 'ETD', 'ETA', 'ATA', '비고', 'F/T', '	DEM RATE', 'END OF F/T', 'ESTIMATE RETURN DATE', 'RETURN DATE', 'DEM DAYS', 'TOTAL DEM', '반납DEPOT', 'DEM RCVD', 'DEM(USD)-매입', 'DEM 매출', 'DEPOT IN DATE(REPO ONLY)', 'REPOSITION 매입','hpod','harrivalNotice'],
	   	colModel: [
	   		{ name: 'jqFlag',				width: 40,		align:'center', 	hidden : false,	frozen:true},
	   		{ name: 'uuid', 				width: 50, 		align:'center',		hidden : true,	frozen:true},
	       	{ name: 'sales', 				width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'carryoverSales', 		width: 50, 		align:'center',		rowspan: true,	frozen:true},
	       	{ name: 'arrivalNoticeRadio',	width: 70, 		align:'center',		rowspan: true,	frozen:true, formatter: arrivalNoticeFn},
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
	    	{ name: 'podLink', 				width: 100, 	align:'center', formatter:podLinkFn},
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
	       	{ name: 'demDays', 				width: 80, 		align:'center'},
	       	{ name: 'totalDem', 			width: 100, 	align:'center', formatter: totalDemFn},
	       	{ name: 'returnDepot', 			width: 80, 		align:'center'},
	       	{ name: 'demRcvd', 				width: 90, 		align:'center'},
	       	{ name: 'demPrch', 				width: 100, 	align:'center', formatter: demPrchFn},
	       	{ name: 'demSales', 			width: 100, 	align:'center', formatter: demSalesFn},
	       	{ name: 'depotInDate', 			width: 180, 	align:'center'},
	       	{ name: 'repositionPrch', 		width: 120, 	align:'center'},
	       	{ name: 'pod', 					width: 30, 		align:'center', hidden : false},
	       	{ name: 'arrivalNotice', 		width: 30, 		align:'center', hidden : false}
	   	],
		height: 530, 
		width: '100%',
		dblEdit : true,
//		rownumbers : true,
//		delselect: true,
//		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(portData, ComRowData(this.id, iRow));
//			$('#add').click();
//			console.log('ondblClickRow', ComMultiSelectRow(tableName));
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
		return '<a href="' + rowObject.homepage + '" target="_blank">' + rowObject.pod + '</a>';
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

//var textFile = null;

//function makeTextFile(text) {
//    var data = new Blob([text], {type: 'text/plain'});
//    if (textFile !== null) {
//      window.URL.revokeObjectURL(textFile);
//    }
//    textFile = window.URL.createObjectURL(data);
//    return textFile;
//}
//  
//function anSend(){
//	var rowData = ComRowData(tableName, $('#jqArrivalNotice').val());
//
//const mail = `	
//To: User <user@domain.demo>
//Subject: ` + encodeURIComponent('[KCL] Arrival notice 송부의 건, / ') + rowData.hblNo + `
//X-Unsent: 1
//Content-Type: text/html
//
//<html>
//<head>
//<style>
//    body, html, table {
//        font-family: Calibri, Arial, sans-serif;
//    }
//    .pastdue { color: crimson; }
//    table {
//    	border: 1px solid silver;
//    	padding: 6px;
//    }
//    thead {
//        text-align: center;
//        font-size: 1.2em;
//        color: navy;
//        background-color: silver;
//        font-weight: bold;
//    }
//    tbody td {
//    	text-align: center;
//    }
//</style>
//</head>
//<body>
//<table width=100%>
//	<tr>
//		<td><img src="http://www.laurell.com/images/logo/laurell_logo_storefront.jpg" width="200" height="57" alt=""></td>
//		<td align="right"><h1><span class="pastdue">PAST DUE</span> INVOICE</h1></td>
//	</tr>
//</table>
//<table width=100%>
//	<thead>
//		<th>Invoice #</th>
//		<th>Days Overdue</th>
//		<th>Amount Owed</th>
//	</thead>
//	<tbody>
//	<tr>
//		<td>OU812</td>
//		<td>9</td>
//		<td>$4395.00</td>
//	</tr>
//	<tr>
//		<td>OU812</td>
//		<td>9</td>
//		<td>$4395.00</td>
//	</tr>
//	<tr>
//		<td>OU812</td>
//		<td>9</td>
//		<td>$4395.00</td>
//	</tr>
//	</tbody>
//</table>
//</body>
//</html>
//`;
//
//var link = document.getElementById('downloadlink');
//    link.href = makeTextFile(mail);
//    link.download= rowData.hblNo + ".eml";
//    link.click();
//}


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

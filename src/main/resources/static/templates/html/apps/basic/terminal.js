var tableName = '#terminal-table';
$( document ).ready(function() {
   tableInit();

	$('input[type="file"]').change(function() { 
    	upload(this);
	});
});

function tableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: tableColNames.split(','),
	   	colModel: [
	   		{ name: 'jqFlag',			width: 40,		align:'center'},
	   		{ name: 'region', 			width: 100, 	align:'left', editable: true},
	       	{ name: 'type', 			width: 100, 	align:'left', editable: true},
	       	{ name: 'code', 			width: 100, 	align:'left', editable: false},
	       	{ name: 'name', 			width: 250, 	align:'left', editable: true},
	       	{ name: 'parkingLotCode', 	width: 120, 	align:'center', editable: true},
	    	{ name: 'homepage', 		width: 500, 	align:'left', editable: true},
	    	{ name: 'createUserId', 	width: 80, 		align:'left', editable: true},
	    	{ name: 'createDate', 		width: 100, 	align:'left', editable: true},
	    	{ name: 'updateUserId', 	width: 80, 		align:'left', editable: true},
	    	{ name: 'updateDate', 		width: 100, 	align:'left', editable: true}
	    	
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

async function upload(customFile) {
	try{
		var frm = new FormData();
	    frm.append('upload', customFile.files[0]);
	    response = await requestFormDataApi('POST', '/api/basic/terminal/upload', frm);
	   	if(response.common.status === 'S'){
 			search();
 		}
	}catch (error) {
	}finally {
	  document.getElementById("customFile").value=null;
	}
}

async function add(){
	let rowId = $(tableName).getGridParam("records");
	$(tableName).addRow(rowId);
}

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/terminal', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

async function save(){
	var saveData = $(tableName).saveGridData();
	if(saveData.length === 0)
		alertMessage(getMessage('0001'), 'info');
	else{
		await requestApi('POST', '/api/basic/terminal', saveData, {successFn : portSaveFn, errorFn : portSaveFn});
	}
}

function portSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
}

async function fileOpen(){
	$('#customFile').click();
}
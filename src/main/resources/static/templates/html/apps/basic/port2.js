var tableName = '#port-table';
$( document ).ready(function() {
   portTableInit();
});

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
	   	colNames: portTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 400, 	align:'left'},
	       	{ name: 'remark', 			width: 400, 	align:'left'},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
			Object.assign(portData, ComRowData(this.id, iRow));
			$('#add').click();
		}
	});
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
var tableName = '#depot-table';
$( document ).ready(function() {
   depotTableInit();
});

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/depot', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

function depotTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: depotTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 200, 	align:'center'},
	       	{ name: 'contactName', 		width: 200, 	align:'center'},
	       	{ name: 'contactPerson', 	width: 200, 	align:'center'},
	    	{ name: 'address', 			width: 250, 	align:'center'},
	    	{ name: 'etcOne', 			width: 200, 	align:'center'},
	    	{ name: 'etcTwo', 			width: 200, 	align:'center'},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
			Object.assign(depotData, ComRowData(this.id, iRow));
			$('#add').click();
		}
	});
}

async function deleteDepot(){
	const selectObjects = ComMultiSelectRow(tableName);
	if(selectObjects.length === 0)
		alertMessage(getMessage('0000'), 'info');
	else{
		await requestApi('DELETE', '/api/basic/depot', selectObjects, {successFn : depotDeleteSaveFn, errorFn : depotDeleteSaveFn});
	}
}

function depotDeleteSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
 }
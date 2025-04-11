var tableName = '#partner-table';
$( document ).ready(function() {
   partnerTableInit();
});

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/partner', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

function partnerTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: partnerTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 150, 	align:'center'},
	       	{ name: 'contactName', 		width: 150, 	align:'center'},
	       	{ name: 'contactPerson', 	width: 130, 	align:'center'},
	    	{ name: 'addressOne', 		width: 180, 	align:'center'},
	    	{ name: 'addressTwo', 		width: 180, 	align:'center'},
	    	{ name: 'etcOne', 			width: 180, 	align:'center'},
	    	{ name: 'etcTwo', 			width: 180, 	align:'center'},
	    	{ name: 'etcThree', 		width: 180, 	align:'center'},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
			Object.assign(partnerData, ComRowData(this.id, iRow));
			$('#add').click();
		}
	});
}

async function deletePartner(){
	const selectObjects = ComMultiSelectRow(tableName);
	if(selectObjects.length === 0)
		alertMessage(getMessage('0000'), 'info');
	else{
		await requestApi('DELETE', '/api/basic/partner', selectObjects, {successFn : partnerDeleteSaveFn, errorFn : partnerDeleteSaveFn});
	}
}

function partnerDeleteSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
 }
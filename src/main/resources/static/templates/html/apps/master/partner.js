var partnerData = {
	jqFlag: '',
	code : '',
	name : 'Partner Name',
	contactName : 'Manager Name',
	contactPerson : 'Manager Contact',
	addressOne : 'Main Address',
	addressTwo : 'Sub Address',
	etcOne : 'Other1',
	etcTwo : 'Other2',
	etcThree : 'Other3'
}

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
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : false},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 150, 	align:'center', editable : true},
	       	{ name: 'contactName', 		width: 150, 	align:'center', editable : true},
	       	{ name: 'contactPerson', 	width: 130, 	align:'center', editable : true},
	    	{ name: 'addressOne', 		width: 180, 	align:'center', editable : true},
	    	{ name: 'addressTwo', 		width: 180, 	align:'center', editable : true},
	    	{ name: 'etcOne', 			width: 180, 	align:'center', editable : true},
	    	{ name: 'etcTwo', 			width: 180, 	align:'center', editable : true},
	    	{ name: 'etcThree', 		width: 180, 	align:'center', editable : true},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		delselect: true,
		multiselect: true,
		dblEdit : true,
//		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(partnerData, ComRowData(this.id, iRow));
//			$('#add').click();
//		}
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
 
function gridAdd(){
	var rowId = $(tableName).getGridParam("reccount"); 
	$(tableName).jqGrid("addRowData", rowId+1, partnerData, 'first');
}

async function gridSave(){
	
	 let method = 'POST';
  //	if(!isEmpty($('#code').val())) method = 'PATCH';
  	await requestApi(method, '/api/basic/partner', ComGridData(tableName)[0]);
  	
}

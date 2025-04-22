$( document ).ready(function() {
   consigneeTableInit();
});

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/consignee', {name : $('#name').val()});
	$("#consignee-table").clearGridData();
	$("#consignee-table").searchData(response.data);
}

function consigneeTableInit(){
	$("#consignee-table").jqGrid({
	   	datatype: "json",
	   	colNames: consigneeTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : false},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 150, 	align:'center', editable: true, edittype: "date"},
	       	{ name: 'contactName', 		width: 150, 	align:'center', editable: true},
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
		delselect: true,
		multiselect: true,
		cellEdit : true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(consigneeData, ComRowData(this.id, iRow));
//			$('#add').click();
//			console.log('ondblClickRow', ComMultiSelectRow('#consignee-table'));
		}
	});
}
        
async function add(){
	let rowId = $("#consignee-table").getGridParam("records");
	$("#consignee-table").addRow(rowId);
}

async function deleteConsignee(){
	const selectObjects = ComMultiSelectRow("#consignee-table");
	if(selectObjects.length === 0)
		alertMessage(getMessage('0000'), 'info');
	else{
		await requestApi('DELETE', '/api/basic/consignee', selectObjects, {successFn : consigneeDeleteSaveFn, errorFn : consigneeDeleteSaveFn});
	}
}

function consigneeDeleteSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
 }
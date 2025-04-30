var tableName = '#product-table';
$( document ).ready(function() {
   productTableInit();
});

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/basic/product', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

function productTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: productTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : false},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 500, 	align:'left', editable: true, cellattr:idColorFmt},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		dblEdit : true,
		multiselect: true,
		ondblClickRow : function(rowid, iRow, iCol,	e) {
//			Object.assign(productData, ComRowData(this.id, iRow));
//			$('#add').click();
		}
	});
}

function idColorFmt( rowId, tv, rawObject, cm, rdata) {
		return 'style="background:rgb(0 176 240)"';
}

/**  터미널 링크 */
function terminalFn (rowId, tv, rawObject, cm, rdata){
//	return "<a href='" + rowObject.homepage + "' target='_blank'>" + cellvalue + "</a>";
console.log(rowId, tv, rawObject, cm, rdata);
	return tv;
}

async function deleteProduct(){
	const selectObjects = ComMultiSelectRow(tableName);
	if(selectObjects.length === 0)
		alertMessage(getMessage('0000'), 'info');
	else{
		await requestApi('DELETE', '/api/basic/product', selectObjects, {successFn : productDeleteSaveFn, errorFn : productDeleteSaveFn});
	}
}

function productDeleteSaveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
 }
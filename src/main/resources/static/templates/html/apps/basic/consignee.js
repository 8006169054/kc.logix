

$( document ).ready(function() {
   consigneeTableInit();
});

/**
 * 조회
 */
async function search() {
	$("#consignee-table").clearGridData();
	response = await requestApi('GET', '/api/basic/consignee', {name : $('#name').val()});
	$("#consignee-table").searchData(response.data);
}

function consigneeTableInit(){
	$("#consignee-table").jqGrid({
	   	datatype: "json",
	   	colNames: consigneeTableColNames.split(','),
	   	colModel: [
			{ name: 'jqFlag', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'code', 			width: 70, 		align:'center', hidden : true},
	       	{ name: 'name', 			width: 120, 	align:'center', cellattr: function (rowId, val) { return val; }},
	       	{ name: 'contactName', 		width: 120, 	align:'center', cellattr: function (rowId, val) { return val; }},
	       	{ name: 'contactPerson', 	width: 130, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'addressOne', 		width: 180, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'addressTwo', 		width: 180, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'etcOne', 			width: 180, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'etcTwo', 			width: 180, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'etcThree', 		width: 180, 	align:'center', cellattr: function (rowId, val) { return val; }},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDatek',		width: 140,		align:'center'}
	   	],
		height: 100, 
		width: '100%',
		multiselect: true,
//		shrinkToFit: true,
//		autowidth: true
	    //cellEdit : true
//  	dblEdit : true,
//	emptyrecords:'데이터가 없습니다.'
	});
}
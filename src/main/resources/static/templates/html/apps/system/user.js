var tableName = '#user-table';
$( document ).ready(function() {
   userTableInit();
});

/**
 * 조회
 */
async function search() {
	response = await requestApi('GET', '/api/system/user', {name : $('#name').val()});
	$(tableName).clearGridData();
	$(tableName).searchData(response.data);
}

function userTableInit(){
	$(tableName).jqGrid({
	   	datatype: "json",
	   	colNames: ['','ID','Name','e-Mail','Use','Type','Partner','Update User','Update Date'],
	   	colModel: [
			{ name: 'jqFlag', 			width: 50, 		align:'center', hidden : true},
	       	{ name: 'id', 				width: 100, 	align:'center', editable : false},
	       	{ name: 'name', 			width: 200, 	align:'center', editable : false},
	       	{ name: 'mail', 			width: 150, 	align:'center', editable : false},
	    	{ name: 'activation',		width: 80, 		align:'center', editable : false, formatter: activationFn},
	    	{ name: 'type',				width: 100, 	align:'center', editable : false, formatter: typeFn},
	    	{ name: 'partnerCode',		width: 300, 	align:'center', editable : false},
	    	{ name: 'updateUserId', 	width: 100, 	align:'center'},
	    	{ name: 'updateDate',		width: 140,		align:'center'}
	   	],
		height: 500, 
		width: '100%',
		delselect: true,
		multiselect : true, // 그리드 왼쪽부분에 체크 박스가 생겨 다중선택이 가능해진다.
 		multiboxonly : true // 다중선택을 단일 선택으로 제한
//		afterEditCell: function (rowId, cellName, value, indexRow, indexCol){
//			if(cellName == 'code')
//			console.log(rowId, cellName, value, indexRow, indexCol);
//			
//			 return true;
//		}
	});
}

function activationFn(cellvalue, options, rowObject ){
	if(cellvalue === 'Y') return '사용';
	else return '미사용';
}

function typeFn(cellvalue, options, rowObject ){
	if(cellvalue === 'M') return '관리자';
	else return '파트너';
}

async function add(){
	$(tableName).addRow();
}

async function save(){
	var saveData = $(tableName).saveGridData();
	if(saveData.length === 0)
		alertMessage(getMessage('0001'), 'info');
	else{
		await requestApi('POST', '/api/system/user', saveData, {successFn : saveFn, errorFn : saveFn});
	}
}

function saveFn(response){
	if(response.common.status === 'S'){
 		search();
 	}
}
const msgObjet = [];
msgObjet['0000'] = {zh : {message : '未選取任何數據.'}, en : {message : 'No data has been selected.'}}; //선택된 데이터가 없습니다.
msgObjet['0001'] = {zh : {message : '沒有數據變更.'}, en : {message : 'The data has not changed.'}}; //데이터가 변경되지 않았습니다.
msgObjet['0002'] = {zh : {message : '这是新注册的数据. 要删除吗？'}, en : {message : 'This is newly registered data. Do you want to delete it?'}}; //새로 등록한 데이터 입니다. 삭제하시겠습니까?

function getMessage(id){
	const language = getkainosLang();
	if(isEmpty(language)) language = 'en';
	return eval(`msgObjet[id].${language}.message`);
}
const msgObjet = [];
msgObjet['0000'] = {zh : {message : '未選取任何數據.'}, en : {message : 'No data has been selected.'}}; //선택된 데이터가 없습니다.


function getMessage(id){
	const language = getCookie('kainos-lang');
	if(isEmpty(language)) language = 'en';
	return eval(`msgObjet[id].${language}.message`);
}
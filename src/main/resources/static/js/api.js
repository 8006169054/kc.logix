/** Server API 호출 기능 */
requestApi = async (method, url, params, option) => {

  let responseHeaders = {};
  let responseHeaderJSON = {};
  let autoMessage = (option != undefined && option.message != undefined) ? option.message : true;
  let headers = {
    'Content-Type': method?.match(/(POST|PUT|PATCH)/) ? 'application/json' : 'text/plain'
//    ,'Authorization': storage.getItem('kainos') === null ? '' : 'Bearer ' + storage.getItem('kainos')
  };
  let body = (method || '').match(/(POST|PUT|PATCH)/) && params ? JSON.stringify(params) : null;

  if (method.match(/GET/) && (params != null && params != undefined)) {
    url = `${url}?${new URLSearchParams(params)}`;
  }

  return await fetch(`${url}`, {
    method: method,
    mode: 'cors',
    headers: headers,
    body: body,
    cache: 'no-cache',
    credentials: 'include'
  })
    .then(function (response) {
      responseStatus = response.status;
      responseHeaders = response.headers;
      for (let pair of responseHeaders.entries()) {
        responseHeaderJSON[pair[0]] = pair[1];
      }
      switch (response.status) {
        case 200:
          return response.json();

        case 204:
          return undefined;

      	case 403:
//		  $.redirect("/", {path: "home/home"}, "POST");
          return undefined;
          
        default:
          return response;
      }
    })
    .then(function (response) {
		console.log(6, response, responseStatus);
		switch (!!(responseStatus >= 200 && responseStatus < 300)) {
			case true:
//				if(response.common.status === 'E'){
//					Swal.fire('', response.common.message, "error");
//				}
//				else{
//					if(method?.match(/(POST|PUT|PATCH)/) && autoMessage)
//						Swal.fire('', '저장되었습니다.', "success");
//					else if(method === 'DELETE' && autoMessage)
//						Swal.fire('', '삭제되었습니다.', "success");
//					if(response.common.token != null)
//						storage.setItem('kainos', response.common.token);
					
//				}
				return response;
				
			default:
          		throw Error(`${response.status} , messages : ${response.error}`);
		}
	})
    .catch(function (error) {
      console.log('error : ', error);
//      oLoader(false);
//      Swal.fire("", Error(`${error}`).message, "error");
      throw Error(`${error}`).message;
    });
};

window.storage = window.sessionStorage || (function() {
// window.sStorage = (function() {
  var winObj = opener || window;  //opener가 있으면 팝업창으로 열렸으므로 부모 창을 사용
  var data = JSON.parse(winObj.top.name || '{}');
  var fn = {
    length : Object.keys(data).length,
    setItem : function(key, value) {
      data[key]  = value + '';
      winObj.top.name = JSON.stringify(data);
      fn.length++;
    },
    getItem : function(key) {
      return data[key] || null;
    },
    key : function(idx) {
      return Object.keys(data)[idx] || null;  //Object.keys() 는 IE9 이상을 지원하므로 IE8 이하 브라우저 환경에선 수정되어야함
    },
    removeItem : function(key) {
      delete data[key];
      winObj.top.name = JSON.stringify(data);
      fn.length--;
    },
    clear : function() {
      winObj.top.name = '{}';
      fn.length = 0;
    }
  };
  return fn;
})();


/** ================================================================================ */
//priceFormatter = (price) => {
//	return price.toLocaleString();
//};
//
//priceFormatterRemove = (price) => {
//	return price.replace(/,/g, "");
//};
//
//deleteFormatter = () => {
//	return '<i class="bx bx-trash" style="cursor: pointer;"></i>';
//};
//
//shareFormatter = () => {
//	return '<div class="Icon_icon_select__tLN6i"><div class="Icon_icon__I3Lry Icon_added__UaQEK"><i class="bx bx-share" style="cursor: pointer;"></i></div><div class="Icon_icon_name__XWqpO"></div></div>';
//};
//
//oLoader = (showOnInit) => {
//	if(showOnInit)
//		$('#oloader_canvas_0_1').show();
//	else
//		$('#oloader_canvas_0_1').hide();
//};
//
//addDate = (addDay) => {
//	let d = new Date();
//	let sel_day = addDay; //일자를 조절하시면 됩니다. -1이면 하루전/ +1이면 내일
//	d.setDate(d.getDate() + sel_day );
//	let year    = d.getFullYear();
//	let month   = ('0' + (d.getMonth() +  1 )).slice(-2);
//	let day     = ('0' + d.getDate()).slice(-2);
//	dt = year+"-"+month+"-"+day;
//	return dt;
//};
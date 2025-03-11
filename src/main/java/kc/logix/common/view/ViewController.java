package kc.logix.common.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {

	private final String contextPath = "view";
	
	/**
	 * Index 페이지
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/")
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String cacheControl = CacheControl.noCache().getHeaderValue();
//		response.addHeader("Cache-Control", cacheControl);
		return "html/index";
    }
	
	/**
	 * 메인 페이지
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = contextPath + "/**")
    public String view(HttpServletResponse response,  HttpServletRequest request) throws Exception {
//		String cacheControl = CacheControl.noCache().getHeaderValue();
//		response.addHeader("Cache-Control", cacheControl);
		String htmlPath = request.getRequestURI().split(contextPath)[1];
		// OPEN << 권한 체크 패스
        return "html" + htmlPath;
    }
	
}

package jp.gr.java_conf.saboten.wicketutils.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;

public class ServletUtil {


	/**
	 * @return HttpServletRequestのラッパー。モック対応してる。
	 */
	public static WebRequest getRequest() {
		RequestCycle requestCycle = RequestCycle.get();
		if (requestCycle == null)
			throw new RuntimeException("No RequestCycle instance for this thread! This method can only called by Wicket-Thread.");

		return (WebRequest) requestCycle.getRequest();
	}

	public static HttpServletRequest getHttpRequest() {

		WebRequest webReq = getRequest();
		if (webReq instanceof ServletWebRequest) {
			return (HttpServletRequest) webReq.getContainerRequest();
		}

		return null;
	}

	public static HttpSession getHttpSession() {
		HttpServletRequest request = getHttpRequest();
		return request.getSession();
	}
}

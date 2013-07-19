package jp.gr.java_conf.saboten.wicketutils.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import jp.gr.java_conf.saboten.wicketutils.resource.EncodingStringResourceStream;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.crypt.Base64;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;

public class DownloadUtil {

	public static void downloadString(String source, String encoding, String filename) {
		downloadResource(new EncodingStringResourceStream(source, encoding), filename);
	}

	public static void downloadFileResource(File source, String filename) {
		downloadResource(new FileResourceStream(source), filename);
	}

	public static void downloadResource(IResourceStream source, String filename) {
		ResourceStreamRequestHandler h = new ResourceStreamRequestHandler(source, getEncodedFileName(filename));
		RequestCycle.get().scheduleRequestHandlerAfterCurrent(h);
	}

	private enum Browser{MSIE, Safari, Chrome, Firefox, Opera, Else}

	public static String getEncodedFileName(String filename) {
		String ua = ServletUtil.getRequest().getHeader("user-agent");

		// 手抜き判定だが、文字化け対策のみが目的なので
		Browser browser;
		if (ua.contains("MSIE"))		browser = Browser.MSIE;
		else if (ua.contains("Opera")) 	browser = Browser.Opera;
		else if (ua.contains("Firefox")) browser = Browser.Firefox;
		else if (ua.contains("Chrome")) browser = Browser.Chrome;
		else if (ua.contains("Safari")) browser = Browser.Safari;
		else 							browser = Browser.Else;

		try {
			switch(browser) {
			case MSIE:
			case Opera:
				return URLEncoder.encode(filename, "UTF8");

			case Firefox:
			case Chrome:
				byte[] bytes = filename.getBytes("UTF8");
				return "=?utf-8?B?" + new String(Base64.encodeBase64(bytes)) + "?=";

			case Safari:
				return filename;

			default:
				return URLEncoder.encode(filename, "UTF8");
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
}

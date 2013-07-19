package jp.gr.java_conf.saboten.wicketutils.resource;

import java.nio.charset.Charset;

import org.apache.wicket.util.resource.AbstractStringResourceStream;


/**
 * 文字列からブラウザに返却するリソースストリームを構築する<br/>
 *
 * @see {@link jp.gr.java_conf.saboten.wicketutils.util.DownloadUtil}
 */
public class EncodingStringResourceStream extends AbstractStringResourceStream {

	private static final long serialVersionUID = 1L;

	private String str;

	public EncodingStringResourceStream(String str, String charset) {
		setCharset(Charset.forName(charset));
		this.str = str;
	}

	public String getContentType() {
		return "application/octet-stream";
	}

	@Override
	protected String getString() {
		return str;
	}
}

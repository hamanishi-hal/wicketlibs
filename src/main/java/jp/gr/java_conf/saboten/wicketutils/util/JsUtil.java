package jp.gr.java_conf.saboten.wicketutils.util;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.model.Model;

public class JsUtil {

//	public static AbstractHeaderContributor getHeaderJs(final String...scripts) {
//		return new AbstractHeaderContributor() {
//			private static final long serialVersionUID = 1L;
//			@Override
//			public IHeaderContributor[] getHeaderContributors() {
//				IHeaderContributor h = new IHeaderContributor() {
//					private static final long serialVersionUID = 1L;
//					public void renderHead(IHeaderResponse response) {
//						response.renderOnLoadJavascript(concarStr(scripts));
//					}
//				};
//				return new IHeaderContributor[]{h};
//			}
//		};
//	}

	public static AttributeModifier getOnClickJs(String...scripts) {
		return new AttributeModifier("onclick", Model.of(concarStr(scripts)));
	}

	public static String getDisableScript(Component...components) {
		return "$('" + getIDs(components) + "').attr('disabled','disabled');";
	}

	public static String getEnableScript(Component...components) {
		return "$('" + getIDs(components) + "').removeAttr('disabled');";
	}

	public static String concarStr(String...str) {
		StringBuilder sb = new StringBuilder();
		for (String c : str) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static String getIDs(Component...components) {
		StringBuilder sb = new StringBuilder();
		for (Component c : components) {
			sb.append("#").append(c.getMarkupId()).append(",");
		}
		if (sb.length() > 0)
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

}

package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigation;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationIncrementLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigationLink;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;

public class AjaxPagingNavigator extends org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator implements IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	private String indicatorMarkupId;

	public AjaxPagingNavigator(String id, IPageable pageable, String indicatorMarkupId) {
		super(id, pageable);

		this.indicatorMarkupId = indicatorMarkupId;
	}

	public String getAjaxIndicatorMarkupId() {
		return indicatorMarkupId;
	}

	@Override
	protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageNumber) {
		PagingNavigationLink<Void> link = new AjaxPagingNavigationLink(id, pageable, pageNumber);
		link.setBeforeDisabledLink("<span class='disabled'>");
		link.setAfterDisabledLink("</span>");
		return link;
	}

	@Override
	protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment) {
		PagingNavigationIncrementLink<Void> link = new AjaxPagingNavigationIncrementLink(id, pageable, increment);
//		link.add(new SimpleAttributeModifier("class", link.isEnabled()?"":"disabled")); <-- 何故かComponentが見つからなくなる。バグか？
		link.setBeforeDisabledLink("<span class='disabled'>");
		link.setAfterDisabledLink("</span>");
		return link;
	}

	@Override
	protected PagingNavigation newNavigation(final String id, final IPageable pageable, final IPagingLabelProvider labelProvider) {
		return new AjaxPagingNavigation(id, pageable, labelProvider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected Link<?> newPagingNavigationLink(String id, IPageable pageable, long pageIndex) {
				PagingNavigationLink<Void> link = new AjaxPagingNavigationLink(id, pageable, pageIndex);
//				link.add(new SimpleAttributeModifier("class", link.isEnabled()?"":"disabled"));
				link.setBeforeDisabledLink("<span class='current'>");
				link.setAfterDisabledLink("</span>");
				return link;
			}
		};
	}
}

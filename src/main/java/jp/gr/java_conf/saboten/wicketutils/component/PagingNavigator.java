package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.markup.html.navigation.paging.IPagingLabelProvider;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;

public class PagingNavigator extends org.apache.wicket.markup.html.navigation.paging.PagingNavigator {

	private static final long serialVersionUID = 1L;

	public PagingNavigator(String id, IPageable pageable) {
		super(id, pageable);
	}

	@Override
	protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, int pageNumber) {
		PagingNavigationLink<Void> link = new PagingNavigationLink<Void>(id, pageable, pageNumber);
		link.setBeforeDisabledLink("<span class='disabled'>");
		link.setAfterDisabledLink("</span>");
		return link;
	}

	@Override
	protected AbstractLink newPagingNavigationIncrementLink(String id, IPageable pageable, int increment) {
		PagingNavigationIncrementLink<Void> link = new PagingNavigationIncrementLink<Void>(id, pageable, increment);
//		link.add(new SimpleAttributeModifier("class", link.isEnabled()?"":"disabled")); <-- 何故かComponentが見つからなくなる。バグか？
		link.setBeforeDisabledLink("<span class='disabled'>");
		link.setAfterDisabledLink("</span>");
		return link;
	}

	@Override
	protected PagingNavigation newNavigation(final String id, final IPageable pageable, final IPagingLabelProvider labelProvider) {
		return new PagingNavigation(id, pageable, labelProvider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected AbstractLink newPagingNavigationLink(String id, IPageable pageable, long pageIndex) {
				PagingNavigationLink<Void> link = new PagingNavigationLink<Void>(id, pageable, pageIndex);
//				link.add(new SimpleAttributeModifier("class", link.isEnabled()?"":"disabled"));
				link.setBeforeDisabledLink("<span class='current'>");
				link.setAfterDisabledLink("</span>");
				return link;
			}
		};
	}
}

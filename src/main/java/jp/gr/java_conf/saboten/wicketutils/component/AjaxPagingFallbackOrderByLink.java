package jp.gr.java_conf.saboten.wicketutils.component;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.AbstractPageableView;

/**
 *
 */
public class AjaxPagingFallbackOrderByLink<T> extends AjaxFallbackOrderByLink<T>
implements IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	private AbstractPageableView<?> listView;
	private WebMarkupContainer refleshTarget;
	private String indicatorMarkupId;

	public AjaxPagingFallbackOrderByLink(String id, /*IColumn*/T col,
			SortableProvider<?,T> provider,
			AbstractPageableView<?> listView,
			WebMarkupContainer refleshTarget,
			String indicatorMarkupId) {

		super(id, col, provider);
		this.listView = listView;
		this.refleshTarget = refleshTarget;
		this.indicatorMarkupId = indicatorMarkupId;
	}

	protected void onSortChanged() {
		listView.setCurrentPage(0); // Pagingもある場合に、ページ番号を0に戻す
	}


	public String getAjaxIndicatorMarkupId() {
		return indicatorMarkupId;
	}

//	@Override
//	protected void onAjaxClick(AjaxRequestTarget target) {
//		target.addComponent(refleshTarget);
//	}
	@Override
	public void onClick(AjaxRequestTarget target) {
		target.add(refleshTarget);
	}
}

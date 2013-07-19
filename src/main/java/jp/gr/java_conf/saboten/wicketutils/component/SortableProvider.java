package jp.gr.java_conf.saboten.wicketutils.component;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * SortableDataProviderの拡張クラス.<br/>
 * 大体、ソートしたいという要件は後から発生するので、その時になって基底クラスを変更するのは影響が大きいため、
 * 最初から使いやすいSortableDataProviderを用意したもの。
 *
 */
public abstract class SortableProvider<T,S> extends SortableDataProvider<T,S> {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SortableProvider.class);


	private Long size;

	public SortableProvider() {
		setSort(null, true); // TODO これでソート無しの場合に正常に稼働するか？ver6では未確認
	}

	public SortableProvider(S defaultSortKey) {
		setSort(defaultSortKey, true);
	}

	public SortableProvider(S defaultSortKey, boolean ascending) {
		setSort(defaultSortKey, ascending);
	}

	public void setSort(S property, boolean isAsc) {
		super.setSort(property, isAsc ? SortOrder.ASCENDING : SortOrder.DESCENDING);
	}


	public IModel<T> model(final T object) {
		return new LoadableDetachableModel<T>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected T load() {
				return object;
			}
         };
	}

	public Iterator<? extends T> iterator(int first, int count) {
		return getList(count, first, getSort().getProperty(), getSort().isAscending())
			.iterator();
	}

	public final long size() {
		if (size == null)
			size = getSize();
		return size;
	}

	public void detach() {
		size = null;
//		logger.debug("**** size is detached");
	}

	public abstract List<? extends T> getList(int limit, int offset, S sortKey, boolean isAsc);

	public abstract long getSize();
}

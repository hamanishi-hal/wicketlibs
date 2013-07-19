package jp.gr.java_conf.saboten.common;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 *
 * @param <W> Wrapped type
 * @param <O> Original type
 */
public abstract class ReadOnlyListWrapper<W,O> implements List<W> {

	public class ListWrapIterator implements Iterator<W> {

		private Iterator<O> it;

		public ListWrapIterator(Iterator<O> it) {
			this.it = it;
		}

		public boolean hasNext() {
			return it.hasNext();
		}

		public W next() {
			return ReadOnlyListWrapper.this.value(it.next());
		}

		public void remove() {
			throw new RuntimeException("This is a wrapper instance.");
		}
	}


	private List<O> list;

	public ReadOnlyListWrapper(List<O> list) {
		this.list = list;
	}

	protected abstract W value(O object);

	public Iterator<W> iterator() {
		return new ListWrapIterator(list.iterator());
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public W get(int index) {
		return value(list.get(index));
	}

	public int size() {
		return list.size();
	}


	public boolean add(W o) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public void add(int index, W element) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean addAll(Collection<? extends W> c) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean addAll(int index, Collection<? extends W> c) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public void clear() {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean contains(Object o) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean containsAll(Collection<?> c) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public int indexOf(Object o) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public int lastIndexOf(Object o) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public ListIterator<W> listIterator() {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public ListIterator<W> listIterator(int index) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean remove(Object o) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public W remove(int index) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean removeAll(Collection<?> c) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public boolean retainAll(Collection<?> c) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public W set(int index, W element) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public List<W> subList(int fromIndex, int toIndex) {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public Object[] toArray() {
		throw new RuntimeException("This is a wrapper instance.");
	}

	public <T> T[] toArray(T[] a) {
		throw new RuntimeException("This is a wrapper instance.");
	}
}

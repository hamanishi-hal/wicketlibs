package jp.gr.java_conf.saboten.wicketutils.model;

import java.util.List;

import org.apache.wicket.extensions.model.AbstractCheckBoxModel;

//import jp.co.vega_net.wicketutils.dbutil.IDto;
//import jp.co.vega_net.wicketutils.dbutil.anotation.PrimaryKey;

public class SelectionHoldModel<T> extends AbstractCheckBoxModel {

	private static final long serialVersionUID = 1L;

	private T thisInstance;
	private List<T> selection;

	public SelectionHoldModel(T cmts, List<T> selection) {
		this.thisInstance = cmts;
		this.selection = selection;
	}

//	private List<Field> getPrimaryFields(Field[] fs) {
//		List<Field> ret = new ArrayList<Field>();
//		for (Field f : fs) {
////			if (f.isAnnotationPresent(PrimaryKey.class))
//				ret.add(f);
//		}
//		return ret;
//	}
//
//	private boolean equalPrimaryFields(T t1, T t2, List<Field> primaryFields) {
//		if (primaryFields.size() == 0)
//			throw new RuntimeException("no primarykey field.");
//
//		try {
//			for (Field tf : primaryFields) {
//				if (!tf.get(t1).equals(tf.get(t2)))
//					return false;
//			}
//			return true;
//		} catch (IllegalArgumentException e) {
//			throw new RuntimeException(e);
//		} catch (IllegalAccessException e) {
//			throw new RuntimeException(e);
//		}
//	}

	@Override
	public boolean isSelected() {
		// Instanceで比較する
		return selection.contains(thisInstance);

//		// instanceではなく、PrimaryKeyの値で比較する
//		Field[] fs = thisInstance.getClass().getFields();
//		List<Field> primaryFields = getPrimaryFields(fs);
//		for (T t : selection) {
//			if (equalPrimaryFields(thisInstance, t, primaryFields))
//				return true;
//		}
//		return false;
	}

	@Override
	public void select() {
		selection.add(thisInstance);
	}

	@Override
	public void unselect() {
		selection.remove(thisInstance);
	}
}

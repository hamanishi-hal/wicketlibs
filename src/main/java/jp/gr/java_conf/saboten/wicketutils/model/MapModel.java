package jp.gr.java_conf.saboten.wicketutils.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.model.AbstractReadOnlyModel;

public class MapModel extends AbstractReadOnlyModel<Map<String, Object>> {

	private static final long serialVersionUID = 1L;



	private Map<String, Object> map;

	public MapModel(Map<String, Object> map) {
		this.map = map;
	}

	public MapModel() {
		map = new HashMap<String, Object>();
	}

	public MapModel(String key, Object val) {
		map = new HashMap<String, Object>();
		map.put(key, val);
	}

	public MapModel add(String key, Object val) {
		map.put(key, val);
		return this;
	}

	public Map<String, Object> getObject() {
		return map;
	}
}

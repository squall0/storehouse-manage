package com.webone.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.webone.domain.Tool;

public class Tbody {
	String id = "";
	List<Tool> tools = new ArrayList<>();
	String data_toggle = "";
	String href = "";
	String clas = "";

	public static List<Tbody> Sort(List<Tool> tools) {
		List<Tbody> tbodies = new ArrayList<>();
		List<Tbody> result = new ArrayList<>();
		Iterator<Tool> toolIt = tools.iterator();
		String name = "";
		int id = 0;
		int stoId = 0;
		Tbody temTbody = null;
		while (toolIt.hasNext()) {
			Tool tool = (Tool) toolIt.next();
			if (name.equals(tool.getName()) & stoId == tool.getStoreroom().getId()) {
				temTbody.getTools().add(tool);
			} else {
				if (temTbody != null)
					tbodies.add(temTbody);
				temTbody = new Tbody();
				temTbody.getTools().add(tool);
				temTbody.setId("t" + id++);
				name = tool.getName();
				stoId = tool.getStoreroom().getId();
			}
		}
		Iterator<Tbody> tbodyIt = tbodies.iterator();
		while (tbodyIt.hasNext()) {
			Tbody tbody = (Tbody) tbodyIt.next();
			if (tbody.getTools().size() > 1) {
				Tbody head = new Tbody();
				head.setData_toggle("collapse");
				head.setHref("#" + tbody.getId());
				Tool htool = new Tool();
				Tool cltools = tbody.getTools().get(0);
				htool.setName(cltools.getName());
				htool.setAmount(tbody.getTools().size());
				htool.setStoreroom(cltools.getStoreroom());
				htool.setHidden("hidden");
				htool.setClas("table-primary");
				head.getTools().add(htool);
				result.add(head);
				tbody.setClas("collapse show");
			}
			result.add(tbody);
		}
		return result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Tool> getTools() {
		return tools;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public String getData_toggle() {
		return data_toggle;
	}

	public void setData_toggle(String data_toggle) {
		this.data_toggle = data_toggle;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

}

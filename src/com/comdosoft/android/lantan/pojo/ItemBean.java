package com.comdosoft.android.lantan.pojo;

public class ItemBean {
	private int id;
	private int type;
	
	public ItemBean(){}

	public ItemBean(int id, int type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

package com.jk.model;

import java.io.Serializable;

public class Student implements Serializable{
    private static final long serialVersionUID = -8129223395843946633L;

	private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
    
    
}
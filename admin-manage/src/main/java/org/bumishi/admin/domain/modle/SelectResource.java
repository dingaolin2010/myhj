package org.bumishi.admin.domain.modle;

import javax.persistence.Entity;

/**
 * @author qiang.xie
 * @date 2016/10/28
 */

public class SelectResource {

    private String rid;//resource id

    private String label;

    private boolean checked;
    
    private String description;

    public SelectResource() {
    }

    public SelectResource(String rid, String label, boolean checked) {
        this.rid = rid;
        this.label = label;
        this.checked = checked;
    }

    public String getLabel() {
        return label;
    }

    public String getRid() {
        return rid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}

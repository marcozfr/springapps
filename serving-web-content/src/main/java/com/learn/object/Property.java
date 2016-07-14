package com.learn.object;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="properties")
public class Property {
	
	@Id
	private String _id;
	private String groupName;
	private String propertyName;
	private String virtualEndpoint;
	private String modifyBy;
	private String modifyName;
	private String dataType;
	private String isVirtualized;
	private String camelRoutes;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public String getVirtualEndpoint() {
		return virtualEndpoint;
	}
	public void setVirtualEndpoint(String virtualEndpoint) {
		this.virtualEndpoint = virtualEndpoint;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getIsVirtualized() {
		return isVirtualized;
	}
	public void setIsVirtualized(String isVirtualized) {
		this.isVirtualized = isVirtualized;
	}
	public String getCamelRoutes() {
		return camelRoutes;
	}
	public void setCamelRoutes(String camelRoutes) {
		this.camelRoutes = camelRoutes;
	}
	
	
}

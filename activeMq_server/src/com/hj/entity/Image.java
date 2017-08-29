package com.hj.entity;


public class Image {

	private String fileid; // id
	private String docname; // 图片
	private byte[] photo; // 图像
	private long blobsize; // 大小

	public Image() {
		super();
	}

	public Image(String fileid, String docname, byte[] photo, long blobsize) {
		super();
		this.fileid = fileid;
		this.docname = docname;
		this.photo = photo;
		this.blobsize = blobsize;
	}

	public long getBlobsize() {
		return blobsize;
	}

	public String getDocname() {
		return docname;
	}

	public String getFileid() {
		return fileid;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setBlobsize(long blobsize) {
		this.blobsize = blobsize;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
 
}

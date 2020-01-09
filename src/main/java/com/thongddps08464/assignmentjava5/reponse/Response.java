package com.thongddps08464.assignmentjava5.reponse;

public class Response {
	private boolean thanhCong;
	private String thongBao;
	private Object doiTuong;

	public Response(String thongBao) {
		this(false, thongBao, null);
	}

	public Response(boolean thanhCong, String thongBao) {
		this(thanhCong, thongBao, null);
	}

	public Response(String thongBao, Object doiTuong) {
		this(false, thongBao, doiTuong);
	}
	
	public Response(boolean thanhCong, String thongBao, Object doiTuong) {
		this.thanhCong = thanhCong;
		this.thongBao = thongBao;
		this.doiTuong = doiTuong;
	}

	public Response() {

	}

	public boolean isThanhCong() {
		return thanhCong;
	}

	public void setThanhCong(boolean thanhCong) {
		this.thanhCong = thanhCong;
	}
	
	public String getThongBao() {
		return thongBao;
	}

	public void setThongBao(String thongBao) {
		this.thongBao = thongBao;
	}

	public Object getDoiTuong() {
		return doiTuong;
	}

	public void setDoiTuong(Object doiTuong) {
		this.doiTuong = doiTuong;
	}
}

package com.ticarum.apirest.dominio;

public enum Magnitud {
	GRADOS_CELSIUS("Cº"), PORCENTAJE("%"), HECTOPASCAL("hPa"), METROS_SEGUNDO("m/s");
	
	private String magnitud_representacion;
	
	private Magnitud(String magnitud) {
		this.magnitud_representacion = magnitud;
	}
	
	public String getMagnitud_representacion() {
		return magnitud_representacion;
	}
}

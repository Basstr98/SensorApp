package com.ticarum.apirest.dominio;

public enum TipoSensor {
	TEMPERATURA(Magnitud.GRADOS_CELSIUS), HUMEDAD(Magnitud.PORCENTAJE), PRESION(Magnitud.HECTOPASCAL), VELOCIDAD_VIENTO(Magnitud.METROS_SEGUNDO);
	
	private Magnitud tipo_magnitud;
	
	private TipoSensor(Magnitud magnitud) {
		this.tipo_magnitud = magnitud;
	}
	
	public Magnitud getTipo_magnitud() {
		return tipo_magnitud;
	}
}

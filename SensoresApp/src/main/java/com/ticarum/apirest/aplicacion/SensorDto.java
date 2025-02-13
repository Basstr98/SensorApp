package com.ticarum.apirest.aplicacion;

import com.ticarum.apirest.dominio.Magnitud;
import com.ticarum.apirest.dominio.TipoSensor;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorDto {

	private Long id;
	
	private TipoSensor tipo;
	
	private Magnitud magnitud;
	
	private double valor;
	
	private double media;

	
	public SensorDto() {
		
	}
	
	public SensorDto(TipoSensor tipo) {
		this.tipo = tipo;
		this.magnitud = tipo.getTipo_magnitud();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoSensor getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoSensor tipo) {
		this.tipo = tipo;
	}
	
	public Magnitud getMagnitud() {
		return magnitud;
	}
	
	public void setMagnitud(Magnitud magnitud) {
		this.magnitud = magnitud;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public double getMedia() {
		return media;
	}
	
	public void setMedia(double media) {
		this.media = media;
	}
	
	public String getMagnitudRepresentacion() {
		return this.magnitud.getMagnitud_representacion();
	}
}
	
	

package com.ticarum.apirest.aplicacion;

import java.util.Set;

import com.ticarum.apirest.dominio.TipoSensor;


public class SensorHistDto extends SensorDto{

	public SensorHistDto() {}
	
	public SensorHistDto(TipoSensor tipo) {
		super(tipo);
		// TODO Auto-generated constructor stub
	}

	private Set<HistoricoDto> historicos;

	public Set<HistoricoDto> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(Set<HistoricoDto> historicos) {
		this.historicos = historicos;
	}
}

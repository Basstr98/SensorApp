package com.ticarum.apirest.infraestructura;

import java.util.Optional;
import com.ticarum.apirest.aplicacion.HistoricoDto;
import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;

public interface HistoricoServicio {
	public HistoricoDto toDto(Historico historico, Class<? extends HistoricoDto> c);
	public Historico toEntidad(HistoricoDto historicoDto);
	public Historico registrar(HistoricoDto historicoDto);
	public Optional<Historico> getHistorico(Long id);
	public Optional<Sensor> getSensor(Historico historico);
	public String getFecha(Historico historico);
	public Double getValor(Historico historico);
	public void eliminarHistorico(Historico historico);
}

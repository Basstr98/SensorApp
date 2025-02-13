package com.ticarum.apirest.aplicacion;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;

@Service
public class HistoricoServicio implements com.ticarum.apirest.infraestructura.HistoricoServicio{

	@Autowired
	com.ticarum.apirest.infraestructura.HistoricoRepositorio histRep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public HistoricoDto toDto(Historico historico, Class<? extends HistoricoDto> c) {
		HistoricoDto dto = modelMapper.map(historico, c);
		return dto;
	}

	@Override
	public Historico toEntidad(HistoricoDto historicoDto) {
		Historico historico = modelMapper.map(historicoDto, Historico.class);
		return historico;
	}

	@Override
	public Historico registrar(HistoricoDto historicoDto) {
		Historico historico = histRep.save(toEntidad(historicoDto));
		return historico;
	}

	@Override
	public Optional<Historico> getHistorico(Long id) {
		return histRep.findById(id);
	}

	@Override
	public Optional<Sensor> getSensor(Historico historico) {
		return Optional.ofNullable(historico.getSensor());
	}

	@Override
	public String getFecha(Historico historico) {
		return historico.getFecha();
	}

	@Override
	public Double getValor(Historico historico) {
		return historico.getValor();
	}

	@Override
	public void eliminarHistorico(Historico historico) {
		histRep.delete(historico);
	}

}

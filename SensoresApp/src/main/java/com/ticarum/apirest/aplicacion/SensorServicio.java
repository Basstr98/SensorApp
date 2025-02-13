package com.ticarum.apirest.aplicacion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;
import com.ticarum.apirest.excepciones.TipoNoDefinidoException;

@Service
public class SensorServicio implements com.ticarum.apirest.infraestructura.SensorServicio{

	@Autowired
	com.ticarum.apirest.infraestructura.SensorRepositorio sensorRep;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SensorDto toDto(Sensor sensor, Class<? extends SensorDto> c) {
		SensorDto dto = modelMapper.map(sensor, c);
		return dto;
	}	

	@Override
	public Sensor toEntidad(SensorDto sensorDto) {
		Sensor sensor = modelMapper.map(sensorDto, Sensor.class);
		return sensor;
	}

	@Override
	public Sensor registrar(String tipo) {
		SensorDto sdto = new SensorDto(TipoSensor.valueOf(tipo));
		Sensor sensor = sensorRep.save(toEntidad(sdto));
		return sensor;
	}

	@Override
	public Optional<Sensor> getSensor(Long id) {
		return sensorRep.findById(id);
	}

	@Override
	public Optional<Sensor> getSensor(SensorDto sensorDto) {
		Optional<Sensor> sensor = sensorRep.getSensorByTipo(sensorDto.getTipo());
		return sensor;
	}

	@Override
	public Optional<Sensor> getSensor(TipoSensor tipo) {
		return sensorRep.getSensorByTipo(tipo);
	}

	@Override
	public List<Sensor> listar() {
		return sensorRep.findAll();
	}

	@Override
	public Historico registrarHistorico(Sensor sensor) {
		Historico historico = new Historico(sensor,sensor.getValor());
		return historico;
	}

	@Override
	public boolean tieneHistorico(Sensor sensor) {
		return !sensor.getHistoricoValores().isEmpty();
	}

	@Override
	public Double getValor(Sensor sensor) {
		return sensor.getValor();
	}

	@Override
	public OptionalDouble calcularMedia(Sensor sensor, String fechaInicio, String fechaFin) {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		LocalDateTime fechaI = LocalDateTime.parse(fechaInicio,formato);
		LocalDateTime fechaF = LocalDateTime.parse(fechaFin,formato);
		
		OptionalDouble media = sensor.getHistoricoValores().stream()
				.filter(h -> !LocalDateTime.parse(h.getFecha(),formato).isBefore(fechaI) && !LocalDateTime.parse(h.getFecha(),formato).isAfter(fechaF))
				.mapToDouble(Historico::getValor)
				.average(); 
		if (media.isPresent()) {
			sensor.setMedia(media.getAsDouble());
			return media;
		}
		return null;
	}

	@Override
	public void eliminarSensor(Sensor sensor) {
		sensorRep.delete(sensor);
		
	}

	@Override
	public Double generarValor(Sensor sensor) {
		Random random = new Random();
		
		double valorNuevo = 0;
		switch (sensor.getTipo()) {
        case TEMPERATURA: 
        	valorNuevo = 0 + (30 - 0) * random.nextDouble();
        	break;
        case HUMEDAD:
        	valorNuevo = 0 + (100 - 0) * random.nextDouble();
        	break;
        case PRESION:
        	valorNuevo = 899 + (1013 - 899) * random.nextDouble();
        break;
        case VELOCIDAD_VIENTO:
        	valorNuevo = 0 + (15 - 0) * random.nextDouble();
        	break;
        default: 
        	throw new TipoNoDefinidoException("El tipo de Sensor no está permitido");
    };
    
    return valorNuevo;  // 
	}

	@Override
	public Double getMedia(Sensor sensor) {
		return sensor.getMedia();
	}

	@Override
	public boolean existeSensor(Sensor sensor) {
		return sensorRep.findAll().contains(sensor);
	}

	@Override
	public TipoSensor getTipo(Sensor sensor) {
		return sensor.getTipo();
	}

}

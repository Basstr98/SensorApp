package com.ticarum.apirest.infraestructura;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import com.ticarum.apirest.aplicacion.SensorDto;
import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;

public interface SensorServicio {
	public SensorDto toDto(Sensor sensor, Class<? extends SensorDto> c);
	public Sensor toEntidad(SensorDto sensorDto);
	public Sensor registrar(String tipo);
	public Optional<Sensor> getSensor(Long id);
	public Optional<Sensor> getSensor(SensorDto sensorDto);
	public Optional<Sensor> getSensor(TipoSensor tipo);
	public List<Sensor> listar();
	public Historico registrarHistorico(Sensor sensor);
	public boolean tieneHistorico(Sensor sensor);
	public Double getValor(Sensor sensor);
	public Double generarValor(Sensor sensor);
	public OptionalDouble calcularMedia(Sensor sensor, String fechaInicio, String fechaFin);
	public Double getMedia(Sensor sensor);
	public void eliminarSensor(Sensor sensor);
	public boolean existeSensor(Sensor sensor);
	public TipoSensor getTipo(Sensor sensor);
}

package com.ticarum.apirest.presentacion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticarum.apirest.infraestructura.SensorServicio;

import com.ticarum.apirest.aplicacion.HistoricoDto;
import com.ticarum.apirest.aplicacion.SensorDto;
import com.ticarum.apirest.aplicacion.SensorHistDto;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;
import com.ticarum.apirest.excepciones.NoExisteEntidadException;
import com.ticarum.apirest.excepciones.NoExisteHistoricoException;
import com.ticarum.apirest.excepciones.TipoNoDefinidoException;
import com.ticarum.apirest.excepciones.TipoRepetidoException;
import com.ticarum.apirest.infraestructura.HistoricoServicio;

@RestController
public class Controlador {
	
	@Autowired
	SensorServicio sensorServicio;
	
	@Autowired
	HistoricoServicio historicoServicio;
	

	@GetMapping("/sensores")
	public List<SensorDto> listarSensores() {
		List<Sensor> sensores = sensorServicio.listar();
		List<SensorDto> sdtos = new LinkedList<SensorDto>();
		for(Sensor sensor : sensores) {
			SensorDto sdto = sensorServicio.toDto(sensor, SensorDto.class);
			sdtos.add(sdto);
		}
		return sdtos;
	}
	
	@GetMapping("/sensores/{idSensor}")
	public SensorDto getSensorById(@PathVariable(name="idSensor") String idSensor) {
		/* Java 8
		Sensor sensor = sensorServicio.getSensor(Long.parseLong(id))
				.orElseThrow(() -> new NoExisteEntidadException("No existe el sensor con ID " + id));
		sensorServicio.registrarHistorico(sensor);
		sensor.setValor(sensorServicio.generarValor(sensor));
		return sensor.getValor();
		*/
		
		//Java 11
		return sensorServicio.getSensor(Long.parseLong(idSensor)).map(sensor -> {
			SensorDto sdto = sensorServicio.toDto(sensor, SensorDto.class);
			sdto.setValor(sensorServicio.generarValor(sensor));
			sensor.setValor(sdto.getValor());
			historicoServicio.registrar(historicoServicio.toDto(sensorServicio.registrarHistorico(sensor), HistoricoDto.class));
			return sdto;
		}).orElseThrow(() -> new NoExisteEntidadException("No existe el sensor con ID " + idSensor));
	}
	
	@GetMapping("/sensores/{idSensor}/media/{fechaInicio}/{fechaFin}")
	public SensorDto getValorMedioSensor(@PathVariable(name="idSensor", required = true) String idSensor, 
			@PathVariable(name="fechaInicio", required = true) String fechaInicio, 
			@PathVariable(name="fechaFin", required = true) String fechaFin) {
		
		return sensorServicio.getSensor(Long.parseLong(idSensor)).map(sensor -> {
			if (!sensorServicio.tieneHistorico(sensor)) throw new NoExisteHistoricoException("No se puede calcular la media de un sensor sin historicos"); 
			SensorDto sdto = sensorServicio.toDto(sensor, SensorDto.class);
			sdto.setMedia(sensorServicio.calcularMedia(sensor, fechaInicio, fechaFin).getAsDouble());
			return sdto;
		}).orElseThrow(() -> new NoExisteEntidadException("No existe el sensor con ID " + idSensor));
	}
	
	@GetMapping("/sensores/{idSensor}/histórico")
	public SensorHistDto getHistorico(@PathVariable(name="idSensor", required = true) String idSensor) {
		return sensorServicio.getSensor(Long.parseLong(idSensor)).map(sensor -> {
			SensorHistDto shdto = (SensorHistDto) sensorServicio.toDto(sensor, SensorHistDto.class);
			Set<HistoricoDto> hdtos = sensor.getHistoricoValores().stream().map(hist -> {
				HistoricoDto hdto = historicoServicio.toDto(hist, HistoricoDto.class);
				return hdto;
			}).collect(Collectors.toSet());
			shdto.setHistoricos(hdtos);
			return shdto;
		}).orElseThrow(() -> new NoExisteEntidadException("No existe el sensor con ID " + idSensor));
	}
	
	@DeleteMapping("/sensores/{idSensor}")
	public ResponseEntity<String> deleteSensor(@PathVariable(name="idSensor") String idSensor) {
		return sensorServicio.getSensor(Long.parseLong(idSensor)).map(sensor -> {
			sensor.getHistoricoValores().stream().forEach(hist -> {
				historicoServicio.eliminarHistorico(hist);    
			});
			sensorServicio.eliminarSensor(sensor);
			return ResponseEntity.ok("El sensor " + sensor.getId() + " y sus históricos se han eliminado correctamente");
		}).orElseThrow(() -> new NoExisteEntidadException("No existe el sensor con ID " + idSensor));
	}
	
	@PostMapping(value="/sensores")
	public SensorDto registrarSensores(@RequestParam(required = true) String tipo) {	
		boolean tipoValido = Arrays.stream(TipoSensor.values())
							.anyMatch(tip -> tip.name().equals(tipo));
		
		if (!tipoValido) throw new TipoNoDefinidoException("No puede añadir un sensor no definido entre los tipos: TEMPERATURA, HUMEDAD, PRESION, VELOCIDAD_VIENTO");
		
		if (sensorServicio.listar().isEmpty()) {
			return sensorServicio.toDto(sensorServicio.registrar(tipo), SensorDto.class);
		} else {
			for (Sensor sensor: sensorServicio.listar()) {
				if (sensor.getTipo().name().equals(tipo)) throw new TipoRepetidoException("No puede añadir un sensor de Tipo " + tipo + ", ya existe uno igual");
			}
		}
		return sensorServicio.toDto(sensorServicio.registrar(tipo), SensorDto.class);
	}
	
	@GetMapping("/")
	public String hola() {
		return "hola";
	}
} 

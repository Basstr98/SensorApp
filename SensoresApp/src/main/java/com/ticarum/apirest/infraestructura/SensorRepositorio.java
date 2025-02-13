package com.ticarum.apirest.infraestructura;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;

@Repository
public interface SensorRepositorio extends JpaRepository<Sensor, Long>{
	public Optional<Sensor> getSensorByTipo(TipoSensor tipo);
}

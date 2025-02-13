package com.ticarum.apirest.test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.OptionalDouble;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ticarum.apirest.aplicacion.SensorServicio;
import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;
import com.ticarum.apirest.infraestructura.SensorRepositorio;

@ExtendWith(MockitoExtension.class)
class SensorServicioTest {

    @Mock
    private SensorRepositorio sensorRepositorio;

    @InjectMocks
    private SensorServicio sensorServicio;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor(TipoSensor.TEMPERATURA);
        sensor.setId(1L);
        sensor.setValor(25.5);
    }

    @Test
    void testGenerarValor() {
        double valor = sensorServicio.generarValor(sensor);
        assertTrue(valor >= 0 && valor <= 30, "El valor debe estar entre 0 y 30");
    }

    @Test
    void testRegistrarSensor() {
        when(sensorRepositorio.save(any(Sensor.class))).thenReturn(sensor);
        Sensor registrado = sensorServicio.registrar("TEMPERATURA");
        assertNotNull(registrado);
        assertEquals(TipoSensor.TEMPERATURA, registrado.getTipo());
    }

    @Test
    void testGetSensorById() {
        when(sensorRepositorio.findById(1L)).thenReturn(Optional.of(sensor));
        Optional<Sensor> encontrado = sensorServicio.getSensor(1L);
        assertTrue(encontrado.isPresent());
        assertEquals(25.5, encontrado.get().getValor());
    }

    @Test
    void testCalcularMedia() {
        sensor.getHistoricoValores().add(new Historico(sensor, 10.0));
        sensor.getHistoricoValores().add(new Historico(sensor, 20.0));

        OptionalDouble media = sensorServicio.calcularMedia(sensor, "13-02-2025 00:00:00", "13-02-2025 23:59:59");

        assertTrue(media.isPresent());
        assertEquals(15.0, media.getAsDouble());
    }
}
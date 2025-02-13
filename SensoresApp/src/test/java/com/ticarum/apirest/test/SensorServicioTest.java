package com.ticarum.apirest.test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticarum.apirest.aplicacion.SensorServicio;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.dominio.TipoSensor;
import com.ticarum.apirest.presentacion.Controlador;

@WebMvcTest(Controlador.class)
@ExtendWith(MockitoExtension.class)
class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorServicio sensorServicio;

    @Autowired
    private ObjectMapper objectMapper;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setTipo(TipoSensor.TEMPERATURA);
        sensor.setValor(25.0);
    }

    @Test
    void testObtenerSensorPorId() throws Exception {
        when(sensorServicio.getSensor(1L)).thenReturn(Optional.of(sensor));

        mockMvc.perform(get("/sensores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipo").value("TEMPERATURA"))
                .andExpect(jsonPath("$.valor").value(25.0));
    }

    @Test
    void testRegistrarSensor() throws Exception {
        when(sensorServicio.registrar(any())).thenReturn(sensor);

        mockMvc.perform(post("/sensores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sensor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.tipo").value("TEMPERATURA"));
    }

    @Test
    void testEliminarSensor() throws Exception {
        when(sensorServicio.getSensor(1L)).thenReturn(Optional.of(sensor));
        doNothing().when(sensorServicio).eliminarSensor(sensor);

        mockMvc.perform(delete("/sensores/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("El sensor 1 se ha eliminado correctamente"));

        verify(sensorServicio, times(1)).eliminarSensor(sensor);
    }
}
package com.ticarum.apirest.test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.ticarum.apirest.aplicacion.HistoricoDto;
import com.ticarum.apirest.aplicacion.HistoricoServicio;
import com.ticarum.apirest.dominio.Historico;
import com.ticarum.apirest.dominio.Sensor;
import com.ticarum.apirest.infraestructura.HistoricoRepositorio;

@ExtendWith(MockitoExtension.class)
class HistoricoServicioTest {

    @Mock
    private HistoricoRepositorio histRep;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private HistoricoServicio historicoServicio;

    private Historico historico;
    private HistoricoDto historicoDto;
    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor();
        sensor.setId(1L);
        
        historico = new Historico(sensor, 25.5);
        historico.setId(100L);
        
        historicoDto = new HistoricoDto();
        historicoDto.setId(100L);
        historicoDto.setValor(25.5);
    }

    @Test
    void testToDto() {
        when(modelMapper.map(historico, HistoricoDto.class)).thenReturn(historicoDto);
        
        HistoricoDto result = historicoServicio.toDto(historico, HistoricoDto.class);
        
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(25.5, result.getValor());
    }

    @Test
    void testToEntidad() {
        when(modelMapper.map(historicoDto, Historico.class)).thenReturn(historico);
        
        Historico result = historicoServicio.toEntidad(historicoDto);
        
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals(25.5, result.getValor());
    }

    @Test
    void testRegistrar() {
        when(histRep.save(any(Historico.class))).thenReturn(historico);
        when(modelMapper.map(historicoDto, Historico.class)).thenReturn(historico);
        
        Historico result = historicoServicio.registrar(historicoDto);
        
        assertNotNull(result);
        assertEquals(100L, result.getId());
        verify(histRep, times(1)).save(any(Historico.class));
    }

    @Test
    void testGetHistorico() {
        when(histRep.findById(100L)).thenReturn(Optional.of(historico));
        
        Optional<Historico> result = historicoServicio.getHistorico(100L);
        
        assertTrue(result.isPresent());
        assertEquals(100L, result.get().getId());
    }

    @Test
    void testGetSensor() {
        Optional<Sensor> result = historicoServicio.getSensor(historico);
        
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testGetFecha() {
        String fecha = historicoServicio.getFecha(historico);
        
        assertNotNull(fecha);
    }

    @Test
    void testGetValor() {
        Double valor = historicoServicio.getValor(historico);
        
        assertEquals(25.5, valor);
    }

    @Test
    void testEliminarHistorico() {
        doNothing().when(histRep).delete(historico);
        
        historicoServicio.eliminarHistorico(historico);
        
        verify(histRep, times(1)).delete(historico);
    }
}
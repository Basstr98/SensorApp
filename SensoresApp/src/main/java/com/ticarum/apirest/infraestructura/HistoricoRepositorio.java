package com.ticarum.apirest.infraestructura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticarum.apirest.dominio.Historico;

@Repository
public interface HistoricoRepositorio extends JpaRepository<Historico, Long>{

}

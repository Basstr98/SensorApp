package com.ticarum.apirest.dominio;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Sensor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false,length=20)
	private TipoSensor tipo;
	
	@Column(nullable=false)
	private Magnitud magnitud;
	
	@Column(nullable=false)
	private Double valor;
	
	@Column(nullable=false)
	private Double media;
	
	@OneToMany(mappedBy="sensor",cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<Historico> historicoValores;
	
	public Sensor() {}
	
	public Sensor(TipoSensor tipo) {
		this.tipo = tipo;
		this.magnitud = tipo.getTipo_magnitud();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Magnitud getMagnitud() {
		return magnitud;
	}
	
	public void setMagnitud(Magnitud magnitud) {
		this.magnitud = magnitud;
	}
	
	public TipoSensor getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoSensor tipo) {
		this.tipo = tipo;
	}
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Set<Historico> getHistoricoValores() {
		return historicoValores;
	}
	
	public Double getMedia() {
		return media;
	}
	
	public void setMedia(Double media) {
		this.media = media;
	}
	
}

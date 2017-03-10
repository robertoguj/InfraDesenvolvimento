package com.cspecem.automacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

//import com.cspecem.automacao.validation.SKU;

@Entity
@Table(name="equipamento")
public class Equipamento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Produto produto;
	private String numeroSerie;
	private LocalInstalacao localInstalacao;
	private String tag;
	private String statusGarantia;
	private Date dataGarantia;
	private Date dataAtual = new Date(System.currentTimeMillis());
	
	@Id
	@GeneratedValue
	@Column(name="equipamento_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL, optional=true, fetch = FetchType.EAGER)
	@JoinColumn(name="produto_id")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@NotBlank(message="Número de série do equipamento deve ser informado.")
	//@SKU(message="Por favor, informe um número de série no formato XX9999")
	@Column(name="numero_serie", nullable=false, length = 20, unique=true)
	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="local_id")
	public LocalInstalacao getLocalInstalacao() {
		return localInstalacao;
	}

	public void setLocalInstalacao(LocalInstalacao localInstalacao) {
		this.localInstalacao = localInstalacao;
	}

	@Column(length=35)
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Transient
	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	@Transient
	public String getStatusGarantia() {

		if(this.dataGarantia.compareTo(dataAtual) < 0) {
			this.statusGarantia = "Expirada";
		} else {
			this.statusGarantia = "Ativa";
		}
		return statusGarantia;
	}

	public void setStatusGarantia(String statusGarantia) {
		this.statusGarantia = statusGarantia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_garantia")
	public Date getDataGarantia() {
		return dataGarantia;
	}

	public void setDataGarantia(Date dataGarantia) {
		this.dataGarantia = dataGarantia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAtual == null) ? 0 : dataAtual.hashCode());
		result = prime * result + ((dataGarantia == null) ? 0 : dataGarantia.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((localInstalacao == null) ? 0 : localInstalacao.hashCode());
		result = prime * result + ((numeroSerie == null) ? 0 : numeroSerie.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((statusGarantia == null) ? 0 : statusGarantia.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipamento other = (Equipamento) obj;
		if (dataAtual == null) {
			if (other.dataAtual != null)
				return false;
		} else if (!dataAtual.equals(other.dataAtual))
			return false;
		if (dataGarantia == null) {
			if (other.dataGarantia != null)
				return false;
		} else if (!dataGarantia.equals(other.dataGarantia))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (localInstalacao == null) {
			if (other.localInstalacao != null)
				return false;
		} else if (!localInstalacao.equals(other.localInstalacao))
			return false;
		if (numeroSerie == null) {
			if (other.numeroSerie != null)
				return false;
		} else if (!numeroSerie.equals(other.numeroSerie))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (statusGarantia == null) {
			if (other.statusGarantia != null)
				return false;
		} else if (!statusGarantia.equals(other.statusGarantia))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
			return false;
		return true;
	}
	
	
}

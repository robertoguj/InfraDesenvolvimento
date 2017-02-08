package com.cspecem.automacao.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="material")
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Produto produto;
	private String criticidade;
	private Integer quantidadeDesejavel;
	private Integer quantidadeRecebida;
	private Integer quantidadeNecessariaRC;
	private String numeroRC;
	private String comprador;
	private String statusSAP;
	private String statusConsolidado;

	@Id
	@GeneratedValue
	@Column(name="material_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade=CascadeType.ALL, optional=true, fetch=FetchType.EAGER)
	@JoinColumn(name="produto_id")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Column(nullable=false, length=60)
	@NotNull(message="Criticidade do material deve ser informada")
	public String getCriticidade() {
		return criticidade;
	}

	public void setCriticidade(String criticidade) {
		this.criticidade = criticidade;
	}

	@Column(name="qtd_desejavel", length=5)
	public Integer getQuantidadeDesejavel() {
		return quantidadeDesejavel;
	}

	public void setQuantidadeDesejavel(Integer quantidadeDesejavel) {
		this.quantidadeDesejavel = quantidadeDesejavel;
	}

	@Column(name="qtd_recebida", length=5)
	public Integer getQuantidadeRecebida() {
		return quantidadeRecebida;
	}

	public void setQuantidadeRecebida(Integer quantidadeRecebida) {
		this.quantidadeRecebida = quantidadeRecebida;
	}
	
	@Column(name="status_sap", length=20)
	public String getStatusSAP() {
		return statusSAP;
	}

	public void setStatusSAP(String statusSAP) {
		this.statusSAP = statusSAP;
	}
	
	@Transient
	public Integer getQuantidadeNecessariaRC() {
		if (this.quantidadeNecessariaRC == null) {
			this.quantidadeNecessariaRC = quantidadeDesejavel - quantidadeRecebida;
		}
		return quantidadeNecessariaRC;
	}

	@Column(name="numero_rc", length=10)
	public String getNumeroRC() {
		return numeroRC;
	}

	public void setNumeroRC(String numeroRC) {
		this.numeroRC = numeroRC;
	}

	@Column(length=30)
	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

	@Column(name="status_consolidado", length=40)
	public String getStatusConsolidado() {
		if (quantidadeDesejavel <= quantidadeRecebida ) {
			setStatusConsolidado("Atendida");
		} else {
			setStatusConsolidado("Pendente");
		}
		return statusConsolidado;
	}

	public void setStatusConsolidado(String statusConsolidado) {
		this.statusConsolidado = statusConsolidado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comprador == null) ? 0 : comprador.hashCode());
		result = prime * result + ((criticidade == null) ? 0 : criticidade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numeroRC == null) ? 0 : numeroRC.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidadeDesejavel == null) ? 0 : quantidadeDesejavel.hashCode());
		result = prime * result + ((quantidadeNecessariaRC == null) ? 0 : quantidadeNecessariaRC.hashCode());
		result = prime * result + ((quantidadeRecebida == null) ? 0 : quantidadeRecebida.hashCode());
		result = prime * result + ((statusConsolidado == null) ? 0 : statusConsolidado.hashCode());
		result = prime * result + ((statusSAP == null) ? 0 : statusSAP.hashCode());
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
		Material other = (Material) obj;
		if (comprador == null) {
			if (other.comprador != null)
				return false;
		} else if (!comprador.equals(other.comprador))
			return false;
		if (criticidade == null) {
			if (other.criticidade != null)
				return false;
		} else if (!criticidade.equals(other.criticidade))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numeroRC == null) {
			if (other.numeroRC != null)
				return false;
		} else if (!numeroRC.equals(other.numeroRC))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidadeDesejavel == null) {
			if (other.quantidadeDesejavel != null)
				return false;
		} else if (!quantidadeDesejavel.equals(other.quantidadeDesejavel))
			return false;
		if (quantidadeNecessariaRC == null) {
			if (other.quantidadeNecessariaRC != null)
				return false;
		} else if (!quantidadeNecessariaRC.equals(other.quantidadeNecessariaRC))
			return false;
		if (quantidadeRecebida == null) {
			if (other.quantidadeRecebida != null)
				return false;
		} else if (!quantidadeRecebida.equals(other.quantidadeRecebida))
			return false;
		if (statusConsolidado == null) {
			if (other.statusConsolidado != null)
				return false;
		} else if (!statusConsolidado.equals(other.statusConsolidado))
			return false;
		if (statusSAP == null) {
			if (other.statusSAP != null)
				return false;
		} else if (!statusSAP.equals(other.statusSAP))
			return false;
		return true;
	}

}

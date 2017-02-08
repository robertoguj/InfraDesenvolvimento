package com.cspecem.automacao.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cspecem.automacao.service.NegocioException;

//import com.cspecem.automacao.validation.SKU;
//import org.hibernate.validator.constraints.NotBlank;
//import com.cspecem.automacao.service.NegocioException;

@Entity
@Table(name="produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String categoria;
	private String sku;
	private String fabricante;
	private String modelo;
	private String descricao;
	private BigDecimal valorEstimado;
	private String codigoSAP;
	private Integer quantidadeEstoque;

	@Id
	@GeneratedValue
	@Column(name="produto_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable=false, length=50)
	@NotNull(message="Categoria deve ser informada.")
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	//@SKU(message = "Por favor, informe um SKU no formato XX9999")
	@NotBlank(message = "Por favor, informe o código do produto.")
	@Column(nullable = false, length=20)
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku == null ? null : sku.toUpperCase();
	}

	@Column(name="valor_estimado", precision=10, scale=2)
	public BigDecimal getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(BigDecimal valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	@NotNull(message="Fabricante deve ser informado.")
	@Column(nullable=false, length=30)
	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	@NotNull(message = "Modelo deve ser informado.")
	@Column(nullable = false, length=60)
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column(nullable=false)
	@NotNull(message="Descrição deve ser informada.")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name="codigo_sap", length=10)
	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	
	@Min(0) @Max(value=9999, message="tem um valor muito alto")
	@Column(name="qtd_estoque", length=5)
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((codigoSAP == null) ? 0 : codigoSAP.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((quantidadeEstoque == null) ? 0 : quantidadeEstoque.hashCode());
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		result = prime * result + ((valorEstimado == null) ? 0 : valorEstimado.hashCode());
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
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (codigoSAP == null) {
			if (other.codigoSAP != null)
				return false;
		} else if (!codigoSAP.equals(other.codigoSAP))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (quantidadeEstoque == null) {
			if (other.quantidadeEstoque != null)
				return false;
		} else if (!quantidadeEstoque.equals(other.quantidadeEstoque))
			return false;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		if (valorEstimado == null) {
			if (other.valorEstimado != null)
				return false;
		} else if (!valorEstimado.equals(other.valorEstimado))
			return false;
		return true;
	}
	
	public void baixarEstoque(Integer quantidade) throws NegocioException {
		int novaQuantidade = this.getQuantidadeEstoque() - quantidade;
		
		if (novaQuantidade < 0) {
			throw new NegocioException("Não há disponibilidade no estoque de "
					+ quantidade + " itens do produto " + this.getSku() + ".");
		}
		
		this.setQuantidadeEstoque(novaQuantidade);
	}

	public void adicionarEstoque(Integer quantidade) {
		this.setQuantidadeEstoque(getQuantidadeEstoque() + quantidade);
	}

}
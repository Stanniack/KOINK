package br.projeto.interdisciplinar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_Financa")
public class Despesa extends Financa {
	
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private FormaDePagamento pagamento;
	
	@Column(nullable = false)
	private boolean pago;
	
	public Despesa() {
		
	}

	public FormaDePagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(FormaDePagamento pagamento) {
		this.pagamento = pagamento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	
	
}

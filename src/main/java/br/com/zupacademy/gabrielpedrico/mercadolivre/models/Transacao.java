package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.StatusPagamento;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Compra compra;

    @NotNull
    private StatusPagamento status;

    @CreationTimestamp
    @PastOrPresent
    private LocalDateTime dataPagamento;

    public Transacao(Compra compra, StatusPagamento status) {
        this.compra = compra;
        this.status = status;
    }

    @Deprecated
    public Transacao(){
    }

    public Long getId() {
        return id;
    }

    public Compra getCompra() {
        return compra;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
}

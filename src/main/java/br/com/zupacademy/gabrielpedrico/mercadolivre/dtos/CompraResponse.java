package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.StatusPagamento;

import java.math.BigDecimal;

public class CompraResponse {

    private String produto;
    private String linkCompra;
    private String pagador;
    private StatusPagamento statusPagamento;
    private BigDecimal valorProduto;

    public CompraResponse(String produto, String linkCompra, StatusPagamento statusPagamento, BigDecimal valorProduto,String pagador) {
        this.produto = produto;
        this.linkCompra = linkCompra;
        this.statusPagamento = statusPagamento;
        this.valorProduto = valorProduto;
        this.pagador = pagador;
    }

    public String getProduto() {
        return produto;
    }

    public String getPagador() {
        return pagador;
    }

    public String getLinkCompra() {
        return linkCompra;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }
}

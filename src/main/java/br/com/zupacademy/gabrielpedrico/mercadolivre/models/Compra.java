package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CompraResponse;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.TransacaoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakeEmailSender;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakeNF;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakeRanking;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.StatusPagamento;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario comprador;
    @NotBlank
    private String token;
    @NotBlank
    private String tipoPagamento;
    @NotNull
    private StatusPagamento status = StatusPagamento.ERRO;

    public Compra(Produto produto, Usuario comprador,String tipoPagamento) {
        this.produto = produto;
        this.comprador = comprador;
        this.token = geraToken();
        this.tipoPagamento = tipoPagamento;
    }

    @Deprecated
    public Compra(){
    }

    public String geraToken(){
        String token = Jwts.builder().toString();
        return token;
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public String getToken() {
        return token;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public CompraResponse conversor(String linkCompra,Usuario usuario, String response, TransacaoRepository transacaoRepository) {
        String produto = this.produto.getNome();
        BigDecimal valorProduto = this.produto.getValor();
        String comprador = usuario.getLogin();
        Boolean transacaoAprovada = pagamentoAprovado(response);
        if(transacaoAprovada) {
            Transacao transacao = new Transacao(this,this.status);
            transacaoRepository.save(transacao);
            FakeEmailSender.enviaCompra(this.produto.getDonoProduto().getLogin(),true);
            FakeNF.envia(this.token,usuario.getId());
            FakeRanking.envia(this.token,this.produto.getDonoProduto().getId());
            return new CompraResponse(produto, linkCompra, this.status, valorProduto, comprador);
        }
        Transacao transacao = new Transacao(this,this.status);
        transacaoRepository.save(transacao);
        FakeEmailSender.enviaCompra(this.produto.getDonoProduto().getLogin(),false);
        throw new IllegalArgumentException("Transação não aprovada");
    }

    private boolean pagamentoAprovado(String response){
        if(response.contentEquals("SUCESSO")) {
            this.status = StatusPagamento.SUCESSO;
            return true;
        }
        if(response.contentEquals("1")){
            this.status = StatusPagamento.SUCESSO;
            return true;
        }
        return false;
    }
}

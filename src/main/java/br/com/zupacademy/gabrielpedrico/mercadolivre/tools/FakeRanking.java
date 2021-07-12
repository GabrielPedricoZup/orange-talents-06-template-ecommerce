package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

public class FakeRanking {

    public static void envia(String identificadorCompra,Long idDonoproduto) {

        System.out.println("http://fakeranking/user/"+idDonoproduto+"/add/"+identificadorCompra);
        System.out.println("Ranking registrado com sucesso =)");

    }
}

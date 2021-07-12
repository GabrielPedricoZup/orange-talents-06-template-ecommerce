package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

public class FakeNF {

    public static void envia(String identificadorCompra,Long idUsuario) {

        System.out.println("http://notafiscalbuilder/build/"+identificadorCompra+"/"+idUsuario);
        System.out.println("Nota fiscal enviada com sucesso");
    }
}

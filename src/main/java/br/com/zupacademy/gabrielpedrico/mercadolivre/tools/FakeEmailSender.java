package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeEmailSender {
    public static void envia(String emailDono) {
/* :::::::::::::@param email do dono produto::::::::::::::::::::::::::::::::::::
   :::::::::::::@return link para mandar o email para o dono::::
   :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        String link ="https://bucket.io/"+ emailDono + "/novaPergunta";
        System.out.println(link);
    }
    public static void enviaCompra(String emailDono,Boolean aprovado) {
/* :::::::::::::@param email do dono produto::::::::::::::::::::::::::::::::::::
   :::::::::::::@return link para mandar o email para o dono::::
   :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        if(aprovado) {
            String link = "https://bucket.io/" + emailDono + "/novaCompraAprovada";
            System.out.println(link);
        }else {
            String link = "https://bucket.io/" + emailDono + "/novaCompraNaoAprovada";
            System.out.println(link);
        }
    }

}

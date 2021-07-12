package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

@Component
public class FakePaymentGateway {
    public static String envia(String identificadorCompra,String tipoPagamento) {
/* :::::::::::::@param identificador e tipo de pagamento::::::::::::::::::::::::::::::::::::
   :::::::::::::@return link pagamento::::
   :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
        if(tipoPagamento.contentEquals("paypal")){
             String link ="paypal.com?buyerId={"+identificadorCompra+"}&redirectUrl={http://localhost:8080/compra}";
             return link;
        }
        if(tipoPagamento.contentEquals("pagseguro")) {
            String link = "pagseguro.com?buyerId={" + identificadorCompra + "}&redirectUrl={http://localhost:8080/compra}";
            return link;
        }
        throw new IllegalArgumentException("Forma de pagamento não permitida");
    }
    public static String getResponse(String tipoPagamento) {
        //Aqui vou simular uma "response do gateway"
        //Vou criar 2 listas com as responses possíveis e embaralhar elas e sempre
        //retornar a response que está no indice 1 da lista para "aleatorizar" o response
        List<String> pagseguro = Arrays.asList("SUCESSO","ERRO");
        List<Integer> paypal = Arrays.asList(0,1);
        Collections.shuffle(paypal);
        Collections.shuffle(pagseguro);


        if(tipoPagamento.contentEquals("pagseguro") && pagseguro.get(1) == "SUCESSO"){
            return "SUCESSO";
        }
        if(tipoPagamento.contentEquals("paypal") && paypal.get(1).toString().contentEquals("1")) {
            return "SUCESSO";
        }
        return "ERRO";
    }
}

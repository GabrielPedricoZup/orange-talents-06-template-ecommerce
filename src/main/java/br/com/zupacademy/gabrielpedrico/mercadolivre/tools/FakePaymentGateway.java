package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

import org.springframework.stereotype.Component;

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
}

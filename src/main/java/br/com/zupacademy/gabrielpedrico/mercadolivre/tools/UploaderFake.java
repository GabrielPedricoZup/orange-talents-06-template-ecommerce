package br.com.zupacademy.gabrielpedrico.mercadolivre.tools;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@Component
public class UploaderFake {
    public static Set<String> envia(List<MultipartFile> imagens) {
/* :::::::::::::@param imagens::::::::::::::::::::::::::::::::::::
   :::::::::::::@return link para imagens que foram uploadadas::::
   :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
      return  imagens.stream().map(imagem -> "https://bucket.io/"+imagem
                .getOriginalFilename()).collect(Collectors.toSet());
    }
}

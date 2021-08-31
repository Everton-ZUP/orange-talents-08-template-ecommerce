package br.com.zupacademy.everton.ecommerce.produto;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader {
    public List<String> envia(List<MultipartFile> imagens){
        return imagens.stream().map(multipartFile -> "Http:// "+multipartFile.getOriginalFilename()).collect(Collectors.toList());
    }
}

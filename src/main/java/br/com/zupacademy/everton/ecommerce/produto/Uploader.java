package br.com.zupacademy.everton.ecommerce.produto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {
    List<String> envia(List<MultipartFile> imagens);
}

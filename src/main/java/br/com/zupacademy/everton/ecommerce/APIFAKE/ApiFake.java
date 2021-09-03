package br.com.zupacademy.everton.ecommerce.APIFAKE;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiFake {

    @PostMapping("/fake/fiscal")
    public String api1(@RequestBody FiscalForm formulario){
        return formulario.toString();
    }

    @PostMapping("/fake/ranking")
    public String api2(@RequestBody RankingForm formulario){
        return formulario.toString();
    }
}

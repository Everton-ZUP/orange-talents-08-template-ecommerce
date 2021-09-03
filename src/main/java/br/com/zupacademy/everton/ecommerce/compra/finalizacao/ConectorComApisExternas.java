package br.com.zupacademy.everton.ecommerce.compra.finalizacao;

import br.com.zupacademy.everton.ecommerce.APIFAKE.FiscalForm;
import br.com.zupacademy.everton.ecommerce.APIFAKE.RankingForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "localhost:8080/fake/", name = "api-fake")
public interface ConectorComApisExternas {

    @PostMapping(value = "/fiscal")
    String notificaFiscal(@RequestBody FiscalForm form);


    @PostMapping(value = "/ranking")
    String notificaRanking(@RequestBody RankingForm form);

}

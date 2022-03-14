package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.dto.AccountDto;
import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.util.AppUtils;
import com.bootcamp.bankdeposit.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositServiceImpl implements DepositService {

    @Value("${microservice-accounts.uri}")
    private String urlAccounts;
    @Value("${apiclient.uri}")
    private String urlApigateway;


    private static final Logger LOGGER = LoggerFactory.getLogger(DepositServiceImpl.class);
    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private WebClient.Builder webClient;
    @Autowired
    private RestTemplate restTemplate;

    public Flux<DepositDto> getDeposit() {

        LOGGER.debug("In getDeposit()");
        return depositRepository.findAll().map(AppUtils::entityToDto);
    }

    @Override
    public Mono<DepositDto> getDepositById(String id) {
        return depositRepository.findById(id).map(AppUtils::entityToDto);
    }
/*
    @Override
    public Mono<DepositDto> getDepositByName(String name) {
        return depositRepository.findByName(name);
    }*/
/*
    @Override
    public Mono<DepositDto> getDepositByDepositNumber(String depositNumber) {
        return depositRepository.findByDepositNumber(depositNumber)
                .switchIfEmpty(Mono.just(DepositDto.builder()
                        .depositNumber(null).build()));
    }*/

    public Mono<DepositDto> saveDeposit(DepositDto depositDtoMono) {
        LOGGER.debug("url a invocar:"+urlApigateway+urlAccounts);
        /*depositDtoMono.subscribe(p ->
                 webClient.build().get().uri(urlApigateway+urlAccounts,p.getToAccountId())
                .retrieve()
                .bodyToMono(AccountDto.class));*/
        /*Mono<AccountDto> monoDto = webClient.build().get().uri(urlApigateway+urlAccounts,depositDtoMono.getToAccountId())
                .retrieve()
                .bodyToMono(AccountDto.class);*/
//        AccountDto dto = ((AccountDto) monoDto.block());
//        LOGGER.debug("client:"+dto);
/*
        return Mono.just(depositDtoMono)
                .flatMap(
                        depo->{
                            Mono<AccountDto> account = webClient.build().get().uri(urlApigateway+urlAccounts,depo.getToAccountId())
                                    .retrieve()
                                    .bodyToMono(AccountDto.class);
                            //account.setBalance("123456");
                            //return Mono.just(depo);
                            return Mono.just(depositDtoMono).map(AppUtils::dtoToEntity)
                                    .flatMap(depositRepository::insert)
                                    .map(AppUtils::entityToDto);
                        }
                );*/
        /*Mono<AccountDto> monoDto = webClient.build().get().uri(urlApigateway+urlAccounts,depositDtoMono.getToAccountId())
                .exchangeToMono(response -> {
                    LOGGER.debug("--response-->" + response.statusCode());
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        Mono<AccountDto> dto = response.bodyToMono(AccountDto.class);

                        dto.subscribe(p -> LOGGER.debug("---->" + p.getAccountNumber()));
                        return dto;
                    }
                    else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
*/
        AccountDto account = restTemplate.getForObject(urlApigateway+urlAccounts+depositDtoMono.getToAccountId(),AccountDto.class);
        LOGGER.debug("restTemplate:"+account.getAccountNumber());

        calculateBalance(account,depositDtoMono);

        //AccountDto dto = ((AccountDto) monoDto.block());
        //LOGGER.debug("client:"+dto);
        return Mono.just(depositDtoMono).map(AppUtils::dtoToEntity)
                .flatMap(depositRepository::insert)
                .map(AppUtils::entityToDto);
    }

    private AccountDto calculateBalance(AccountDto account, DepositDto depositDto) {

        if(Constant.TIPO_CUENTA_PLAZO.equals(account.getAccountType())) {


        }
        return account;
    }

    public Mono<DepositDto> updateDeposit(Mono<DepositDto> DepositDtoMono, String id) {
        return depositRepository.findById(id)
                .flatMap(p -> DepositDtoMono.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(depositRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteDeposit(String id) {
        return depositRepository.deleteById(id);
    }
}

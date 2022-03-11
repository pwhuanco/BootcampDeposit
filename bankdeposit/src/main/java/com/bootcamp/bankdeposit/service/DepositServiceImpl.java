package com.bootcamp.bankdeposit.service;

import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.repository.DepositRepository;
import com.bootcamp.bankdeposit.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    public Flux<DepositDto> getDeposit() {
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

    @Override
    public Mono<DepositDto> getDepositByDepositNumber(String depositNumber) {
        return depositRepository.findByDepositNumber(depositNumber)
                .switchIfEmpty(Mono.just(DepositDto.builder()
                        .depositNumber(null).build()));
    }


    public Mono<DepositDto> saveDeposit(Mono<DepositDto> DepositDtoMono) {
        return DepositDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(depositRepository::insert)
                .map(AppUtils::entityToDto);
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

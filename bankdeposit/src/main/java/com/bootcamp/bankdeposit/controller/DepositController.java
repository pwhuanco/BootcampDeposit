package com.bootcamp.bankdeposit.controller;

import com.bootcamp.bankdeposit.dto.DepositDto;
import com.bootcamp.bankdeposit.service.DepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Slf4j
@RestController
@RequestMapping(path = "/api/deposit")
public class DepositController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepositController.class);

    @Autowired
    private DepositService depositService;

    @GetMapping
    public Flux<DepositDto> getDeposit(){
        LOGGER.debug("Getting Deposit!");
        return depositService.getDeposit();
    }

    @GetMapping("/{id}")
    public Mono<DepositDto> getDeposit(@PathVariable String id){
        LOGGER.debug("Getting a deposit!");
        return depositService.getDepositById(id);
    }

    @PostMapping
    public Mono<DepositDto> saveDeposit(@RequestBody Mono<DepositDto> depositDtoMono){
        LOGGER.debug("Saving clients!");
        return depositService.saveDeposit(depositDtoMono);
    }

    @PutMapping("/{id}")
    public Mono<DepositDto> updateDeposit(@RequestBody Mono<DepositDto> depositDtoMono, @PathVariable String id){
        LOGGER.debug("Updating deposit!");
        return depositService.updateDeposit(depositDtoMono,id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDeposit(@PathVariable String id){
        LOGGER.debug("Deleting deposit!");
        return depositService.deleteDeposit(id);
    }

}

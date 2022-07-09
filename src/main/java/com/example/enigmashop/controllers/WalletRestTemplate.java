package com.example.enigmashop.controllers;

import com.example.enigmashop.constant.ApiConstantURL;
import com.example.enigmashop.constant.ResponseMessage;
import com.example.enigmashop.dto.WalletDTO;
import com.example.enigmashop.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
@RequestMapping(ApiConstantURL.WALLET)
public class WalletRestTemplate {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<Response<WalletDTO>> createWallet(@RequestBody WalletDTO walletDTO){

        String url = "http://localhost:8090/wallets";
        ResponseEntity<Response> walletDTO1 = restTemplate.postForEntity(URI.create(url), walletDTO, Response.class);

        Object walletDTO2 = walletDTO1.getBody().getData();

        WalletDTO walletDTO3 = walletDTO(walletDTO2);

        Response<WalletDTO> response =  new Response<>();
        String message = String.format(ResponseMessage.DATA_INSERT, "Wallet");
        response.setMessage(message);
        response.setData(walletDTO3);


        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    public WalletDTO walletDTO(Object obj){
        try {
            return new ObjectMapper().convertValue(obj, WalletDTO.class);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}

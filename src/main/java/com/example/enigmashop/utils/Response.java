package com.example.enigmashop.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Response<T> {
    public String message;
    private T data;
}

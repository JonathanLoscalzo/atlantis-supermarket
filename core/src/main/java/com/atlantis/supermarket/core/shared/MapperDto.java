package com.atlantis.supermarket.core.shared;

public interface MapperDto<T,Dto>{
    Dto toDto(T t);
}

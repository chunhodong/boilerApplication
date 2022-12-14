package com.bronze.boiler.exception;

public class MemberException extends RuntimeException implements BaseException {

    private ExceptionType type;

    public MemberException(ExceptionType type){
        this.type = type;
    }


    @Override
    public ExceptionType getType() {
        return this.type;
    }
}

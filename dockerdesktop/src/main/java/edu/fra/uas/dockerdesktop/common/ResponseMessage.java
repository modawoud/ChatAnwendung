package edu.fra.uas.dockerdesktop.common;

public record ResponseMessage( String message , Object data) {

    @Override
    public String message() {
        return message;
    }

    @Override
    public Object data() {
        return data;
    }
}
package com.jvitorsaugusto.vetclinic_api.utils;

public class VerifyEmpty {

    public static final String NAO_INFORMADO = "NÃO INFORMADO";
    public String verifyStringEmpty(String attribute){
        if (attribute.isEmpty()){
            return NAO_INFORMADO;
        }
        return attribute;
    }
}

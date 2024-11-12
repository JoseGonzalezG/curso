package com.curso.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.utilidades.utilidadesJWE;
import com.curso.entitie.userLogin;

import java.security.interfaces.ECPublicKey;

@Service
@Slf4j
public class jweService {

    @Autowired
    utilidadesJWE utilidadesJWE;

    public String getJWE(){
        ECPublicKey publicKey = null;
        userLogin userLogin = null;
        Gson gson = new Gson();
        try {
            publicKey = utilidadesJWE.loadPublicKey();
            userLogin = utilidadesJWE.getUserLogin();

            JsonWebEncryption jwe = new JsonWebEncryption();
            jwe.setPayload(gson.toJson(userLogin));
            jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.ECDH_ES);
            jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
            jwe.setKey(publicKey);
            String serializedJwe = jwe.getCompactSerialization();
            System.out.println("Serialized Encrypted JWE: " + serializedJwe);
            return serializedJwe;
        }catch (Exception ex){
            log.info("Ocurrio un error al generar el JWE ", ex.getCause());
            return null;
        }
    }


}

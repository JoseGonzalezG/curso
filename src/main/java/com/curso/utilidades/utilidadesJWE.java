package com.curso.utilidades;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.curso.entitie.userLogin;


@Service
@Slf4j
public class utilidadesJWE {

    @Value("${indice.urlBase.pem}")
    private String uri_public_key;

    public ECPrivateKey loadPrivateKey(String filename) {
        try {

            Reader reader = new FileReader(filename);
            PemReader pemReader = new PemReader(reader);
            byte[] pem = pemReader.readPemObject().getContent();
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(pem);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyInfo.getEncoded());
            return (ECPrivateKey) keyFactory.generatePrivate(keySpec);
        }catch (IOException ex) {
            log.info("No se encontro el archivo pem " + ex.getMessage() + " con causa " + ex.getCause());
            return null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public ECPublicKey loadPublicKey() {
        try {
            Reader reader = new FileReader(uri_public_key);
            PemReader pemReader = new PemReader(reader);
            byte[] pem = pemReader.readPemObject().getContent();
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo.getInstance(pem);
            KeyFactory keyFactory = KeyFactory.getInstance("EC");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyInfo.getEncoded());
            return (ECPublicKey) keyFactory.generatePublic(keySpec);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public userLogin getUserLogin(){
        try {
            userLogin userLogin = new userLogin();
            userLogin = new userLogin();
            userLogin.setUsername("manuel.garcia");
            userLogin.setPassword("4321");
            return userLogin;
        }catch (Exception ex){
            log.info("Error al generar el objeto userLogin");
            return null;
        }
    }

}

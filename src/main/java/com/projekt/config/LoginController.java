package com.projekt.config;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/login")
@CrossOrigin("*")
public class LoginController {
    @PostMapping
    public String generateToken(@RequestBody Credentials credentials) {
        String connectedCredentials = credentials.getUsername() +":"+ credentials.getPassword();
        byte[] bytesEncoded = Base64.encodeBase64(connectedCredentials.getBytes());
        return "{\"token\":\""+new String(bytesEncoded)+"\"}";
    }
}

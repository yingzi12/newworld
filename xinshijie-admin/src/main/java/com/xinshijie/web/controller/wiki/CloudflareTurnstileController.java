package com.xinshijie.web.controller.wiki;

import cn.hutool.http.server.HttpServerRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.sun.net.httpserver.HttpServer;
import com.xinshijie.wiki.vo.TurnstileVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class CloudflareTurnstileController {
    private final String SECRET_KEY = "your_secret_key";
    private final String TURNSTILE_API_URL = "https://challenges.cloudflare.com/turnstile/v0/siteverify";

    @PostMapping("/validateToken")
    public ResponseEntity<String> validateToken( @RequestBody String dto, HttpServerRequest httpRequest) {
        try {
            JSONObject jsonObject=JSONObject.parseObject(dto);
//            	const token = body.get('cf-turnstile-response');
            String token=jsonObject.getString("cf-turnstile-response");
            //	const ip = request.headers.get('CF-Connecting-IP');
           String ip= httpRequest.getHeaders().getFirst("CF-Connecting-IP");
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("secret", SECRET_KEY);
            formData.add("response", token);
            formData.add("remoteip", ip);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(formData, headers);

            ResponseEntity<String> firstResult = restTemplate.postForEntity(TURNSTILE_API_URL, entity, String.class);

            TurnstileVo firstOutcome = JSONObject.parseObject(firstResult.getBody(), TurnstileVo.class);

            if (firstOutcome.isSuccess()) {
                // Handle success...
            }


            return ResponseEntity.ok("Validation complete.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Validation failed.");
        }
    }
}

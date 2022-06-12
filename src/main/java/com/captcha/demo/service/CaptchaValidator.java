package com.captcha.demo.service;

import com.captcha.demo.dto.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CaptchaValidator {

    private final RestTemplate restTemplate;

    public boolean isValidCaptcha(String captcha) {

        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LfVblUgAAAAAAErpppUW9VWG-N-STrJA4WG_BWc9&response=" + captcha;
        String completeUrl = url + params;
        CaptchaResponse resp = restTemplate.postForObject(completeUrl, null, CaptchaResponse.class);
        return resp.isSuccess();
    }
}

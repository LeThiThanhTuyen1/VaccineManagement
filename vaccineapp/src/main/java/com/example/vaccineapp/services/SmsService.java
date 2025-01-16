package com.example.vaccineapp.services;

import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SmsService {

    @Value("${sms.apiUrl}")
    private String apiUrl;

    @Value("${sms.apiKey}")
    private String apiKey;

    @Value("${sms.senderName}")
    private String senderName;
    public SmsService() {
        // Constructor logic
    }
    private OkHttpClient client = new OkHttpClient();

    public void sendSms(String to, String message) throws IOException {
        // Tạo URL và các tham số cần thiết cho dịch vụ gửi SMS
        String url = apiUrl + "?apiKey=" + apiKey + "&to=" + to + "&message=" + message + "&sender=" + senderName;
        
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("SMS sent successfully!");
            } else {
                System.out.println("Error: " + response.message());
            }
        }
    }
}

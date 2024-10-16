package com.example.unkey;

import io.github.cdimascio.dotenv.Dotenv;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest; // Updated import for Jakarta EE

@RestController
@RequestMapping("/api")
public class ApiController {

    private final String unkeyRootKey;

    public ApiController() {
        Dotenv dotenv = Dotenv.configure().load();
        this.unkeyRootKey = dotenv.get("UNKEY_ROOT_KEY");
    }

    @GetMapping("/cheap")
    public String cheap(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        HttpResponse<String> response = Unirest.post("https://api.unkey.dev/v1/ratelimits.limit")
                .header("Authorization", "Bearer " + unkeyRootKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"namespace\": \"harshbhat\",\n" +
                        "  \"identifier\": \"" + clientIp + "\",\n" +
                        "  \"limit\": 12,\n" +
                        "  \"duration\": 30000,\n" +
                        "  \"cost\": 2,\n" +
                        "  \"async\": true\n" +
                        "}")
                .asString();

        StringBuilder result = new StringBuilder();

        if (response.isSuccess()) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            if (jsonResponse.getBoolean("success")) {
                result.append("Cheap route accessed \n");
            } else {
                result.append("Rate limit exceeded\n");
            }
        } else {
            result.append("Failed to make rate limit request: ").append(response.getStatusText()).append("\n");
        }
        return result.toString();
    }

    @GetMapping("/expensive")
    public String expensive(HttpServletRequest request) {
        String clientIp = request.getRemoteAddr();

        HttpResponse<String> response = Unirest.post("https://api.unkey.dev/v1/ratelimits.limit")
                .header("Authorization", "Bearer " + unkeyRootKey)
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"namespace\": \"harshbhat\",\n" +
                        "  \"identifier\": \"" + clientIp + "\",\n" +
                        "  \"limit\": 12,\n" +
                        "  \"duration\": 30000,\n" +
                        "  \"cost\": 4,\n" +
                        "  \"async\": true\n" +
                        "}")
                .asString();

        StringBuilder result = new StringBuilder();

        if (response.isSuccess()) {
            JSONObject jsonResponse = new JSONObject(response.getBody());
            if (jsonResponse.getBoolean("success")) {
                result.append("Expensive route accessed \n");
            } else {
                result.append("Rate limit exceeded\n");
            }
        } else {
            result.append("Failed to make rate limit request: ").append(response.getStatusText()).append("\n");
        }
        return result.toString();
    }
}

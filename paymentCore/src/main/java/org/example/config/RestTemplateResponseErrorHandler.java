package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.custom.ErrorResponse;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            throw new HttpClientErrorException(response.getStatusCode());
        } else if (response.getStatusCode().is4xxClientError()) {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new RuntimeException("Not Found");
            }
            if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                ObjectMapper mapper = new ObjectMapper();
                ErrorResponse errorResponse = mapper.readValue(response.getBody(), ErrorResponse.class);
                throw new IntegrationException(errorResponse.getErrorDescription());
            }
        }
    }
}

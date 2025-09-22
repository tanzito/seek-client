package com.seek.client.error;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorInfo  {
    private String typeError;
    private int status;
    private String message;
    private String path;
}

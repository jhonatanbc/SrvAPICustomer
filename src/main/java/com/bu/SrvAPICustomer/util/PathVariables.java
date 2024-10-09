package com.bu.SrvAPICustomer.util;

import java.util.HashMap;
import java.util.Map;

public class PathVariables {
    public static Map<String, String> splitVariablesPath(String identifierPath) {
        String[] parts = identifierPath.split("_");

        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato inválido en el path. Debe contener 'tipoDocumento_numeroDocumento'");
        }

        Map<String, String> documentInfo = new HashMap<>();
        documentInfo.put("tipoDocumento", parts[0]);
        documentInfo.put("numeroDocumento", parts[1]);

        return documentInfo;
    }
}

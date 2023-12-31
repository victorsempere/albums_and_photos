package com.victorsemperevidal.albumsandfotos.domain.services;

import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;

/**
 * Servicio que nos dará la información obtenida de la fuente de datos externa
 * en objetos del dominio
 */
public interface ExternalDataService {
    public ExternalData fetchExternalData() throws ExternalClientException;
}

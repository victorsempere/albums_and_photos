package com.victorsemperevidal.albumsandfotos.domain.services;

import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;

/**
 * Servicio que conecta con la fuente de datos de álbumes y fotos y nos da la
 * información completa
 */
public interface ExternalDataService {
    public ExternalData fetchExternalData() throws ExternalClientException;
}

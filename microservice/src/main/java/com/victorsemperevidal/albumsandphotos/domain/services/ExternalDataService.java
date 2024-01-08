package com.victorsemperevidal.albumsandphotos.domain.services;

import com.victorsemperevidal.albumsandphotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;

/**
 * Servicio que conecta con la fuente de datos de álbumes y fotos y nos da la
 * información completa
 */
public interface ExternalDataService {
    public ExternalData fetchExternalData() throws ExternalClientException;
}

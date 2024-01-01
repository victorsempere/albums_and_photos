package com.victorsemperevidal.albumsandfotos.domain.services;

import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;

/**
 * Servicio que inserta en el modelo de datos los datos recibidos del servicio
 * externo que nos proporciona la información de los álbumes y las fotos
 */
public interface PopulateService {
    public void populate(ExternalData externalData);
}
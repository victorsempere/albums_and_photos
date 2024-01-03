package com.victorsemperevidal.albumsandfotos.domain.services;

import java.util.Collection;

import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;

/**
 * Servicio que se encarga de procesar los datos de álbumes y photos para generar el resultado enriquecido que nos muestra para cada album la información del album y de las fotos asociadas
 */
public interface ProcessAlbumsService {
    public Collection<AlbumPhotos> processAlbumsAndPhotos();
}

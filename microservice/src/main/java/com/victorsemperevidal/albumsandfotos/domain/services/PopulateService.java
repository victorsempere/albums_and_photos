package com.victorsemperevidal.albumsandfotos.domain.services;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;

public interface PopulateService {
    /**
     * 
     * @throws ApiException Cuando ocurre algún error en las peticiones de datos al
     *                      servicio web externo de datos. Por ejemplo:
     *                      https://jsonplaceholder.typicode.com
     */
    public void populate();
}
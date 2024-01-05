package com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service;

import java.util.Collection;

public interface CollectionService {
    public <T> Collection<T> getInstance();

    public <T> Collection<T> copyOf(Collection<T> listOfAlbums);
}

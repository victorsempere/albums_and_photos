package com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("arrayListCollectionService")
public class ArrayListCollectionService implements CollectionService {

    ArrayListCollectionService() {
        super();
    }

    @Override
    public <T> Collection<T> getInstance() {
        return new ArrayList<>();
    }

    @Override
    public <T> Collection<T> copyOf(Collection<T> collection) {
        Collection<T> copy = null;
        if (collection != null) {
            copy = new ArrayList<>(collection);
        }
        return copy;
    }

}

package com.victorsemperevidal.albumsandphotos.infraestructure.services.collection_service;

import java.util.Collection;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("treeSetCollectionService")
public class TreeSetCollectionService implements CollectionService {

    @Override
    public <T> Collection<T> getInstance() {
        return new TreeSet<>();
    }

    @Override
    public <T> Collection<T> copyOf(Collection<T> collection) {
        Collection<T> copy = null;
        if (collection != null) {
            copy = new TreeSet<>(collection);
        }
        return copy;
    }

}

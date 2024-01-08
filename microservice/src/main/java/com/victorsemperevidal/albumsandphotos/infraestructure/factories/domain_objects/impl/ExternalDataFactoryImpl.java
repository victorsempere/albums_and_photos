package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.ExternalDataFactory;

@Service
public class ExternalDataFactoryImpl implements ExternalDataFactory {

    @Override
    public ExternalData getInstance(Collection<Album> albums, Collection<Photo> photos) {
        return new ExternalData(albums, photos);
    }

}

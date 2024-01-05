package com.victorsemperevidal.albumsandphotos.infraestructure.factories.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.PhotoDao;

/**
 * TODO: Crear interface para que esta factoría la implemente
 * Para poder después tener varias implementaciones de la factoría y poder
 * cambiar el tipo de datos que se utiliza por debajo, por ejemplo, List o Set
 */
@Service
public class PhotoDaoFactory {
    public PhotoDaoFactory() {
        super();
    }

    public Collection<PhotoDao> getInstancesFromDomainPhotos(Collection<Photo> listOfPhotos) {
        if (listOfPhotos == null) {
            return List.of();
        }
        List<PhotoDao> daos = new ArrayList<>();
        for (Photo photo : listOfPhotos) {
            daos.add(getInstance(photo));
        }
        return daos;
    }

    private PhotoDao getInstance(Photo photo) {
        PhotoDao dao = new PhotoDao();
        dao.setAlbumId(photo.getAlbumId());
        dao.setId(photo.getId());
        dao.setThumbnailUrl(photo.getThumbnailUrl());
        dao.setTitle(photo.getTitle());
        dao.setUrl(photo.getUrl());
        return dao;
    }
}

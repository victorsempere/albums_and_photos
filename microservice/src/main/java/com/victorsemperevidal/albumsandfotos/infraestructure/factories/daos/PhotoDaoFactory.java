package com.victorsemperevidal.albumsandfotos.infraestructure.factories.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.PhotoDao;

@Service
public class PhotoDaoFactory {
    public PhotoDaoFactory() {
        super();
    }

    public List<PhotoDao> getInstancesFromDomainPhotos(List<Photo> listOfPhotos) {
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

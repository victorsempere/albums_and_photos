package com.victorsemperevidal.albumsandfotos.infraestructure.factories.repos.projections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.AlbumIdFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.repositories.database.projections.AlbumAndPhotoProjectionDao;

/**
 * Servicio que a partir de datos de diferentes fuentes (base de datos, API
 * rest, {podría haber más fuentes}), nos da como salida una
 * lista de albums y photos utilizando objetos del dominio. Esto nos permite
 * mover la lógica de negocio que manipula estos datos a la capa de aplicación y
 * dejar esa lógica desacoplada. Por lo tanto podremos validar de forma aislada,
 * el procesamiento de la información de la lógica de negocio.,.
 */
@Service
public class AlbumAndPhotoProjectionFactory {
    private AlbumIdFactory albumIdFactory;

    @Autowired
    public AlbumAndPhotoProjectionFactory(AlbumIdFactory albumIdFactory) {
        super();
        this.albumIdFactory = albumIdFactory;
    }

    public List<AlbumAndPhotoProjection> getInstancesFromListOfDaos(
            List<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos) {
        List<AlbumAndPhotoProjection> albumsAndPhotos = new ArrayList<>();
        if (albumsAndPhotosDaos != null) {
            for (AlbumAndPhotoProjectionDao dao : albumsAndPhotosDaos) {
                albumsAndPhotos.add(getInstanceFromDao(dao));
            }
        }
        return albumsAndPhotos;
    }

    private AlbumAndPhotoProjection getInstanceFromDao(AlbumAndPhotoProjectionDao albumAndPhotosDao) {
        return new AlbumAndPhotoProjection(albumAndPhotosDao.getAlbumId(),
                albumAndPhotosDao.getUserId(), albumAndPhotosDao.getAlbumTitle(), albumAndPhotosDao.getPhotoId(),
                albumAndPhotosDao.getPhotoTitle(),
                albumAndPhotosDao.getPhotoUrl(), albumAndPhotosDao.getPhotoThumbnailUrl());
    }

    public List<AlbumAndPhotoProjection> getInstancesFromListOfAlbumsAndPhotos(List<Album> listOfAlbums,
            List<Photo> listOfPhotos) {
        Map<AlbumId, List<Photo>> photosPerAlbum = buildMapOfPhotosPerAlbum(listOfPhotos);
        return getInstancesFromListOfAlbumsAndPhotosPerAlbum(listOfAlbums, photosPerAlbum);
    }

    private List<AlbumAndPhotoProjection> getInstancesFromListOfAlbumsAndPhotosPerAlbum(List<Album> listOfAlbums,
            Map<AlbumId, List<Photo>> photosPerAlbum) {
        if (listOfAlbums == null) {
            return List.of();
        }
        List<AlbumAndPhotoProjection> projections = new ArrayList<>();
        for (Album album : listOfAlbums) {
            AlbumId albumId = albumIdFactory.getInstance(album);
            List<AlbumAndPhotoProjection> albumPhotos = getInstancesFromAlbumAndPhotos(album,
                    photosPerAlbum == null ? null : photosPerAlbum.get(albumId));
            projections.addAll(albumPhotos);
        }
        return projections;
    }

    private List<AlbumAndPhotoProjection> getInstancesFromAlbumAndPhotos(Album album, List<Photo> listOfPhotos) {
        if (album == null) {
            return List.of();
        }
        if (listOfPhotos == null) {
            return List.of(getInstance(album));

        } else {
            List<AlbumAndPhotoProjection> projections = new ArrayList<>();
            for (Photo photo : listOfPhotos) {
                projections.add(getInstance(album, photo));
            }
            return projections;
        }
    }

    private AlbumAndPhotoProjection getInstance(Album album, Photo photo) {
        return new AlbumAndPhotoProjection(album.getId(), album.getUserId(),
                album.getTitle(), photo == null ? null : photo.getId(), photo == null ? null : photo.getTitle(),
                photo == null ? null : photo.getUrl(), photo == null ? null : photo.getThumbnailUrl());
    }

    private AlbumAndPhotoProjection getInstance(Album album) {
        return getInstance(album, null);
    }

    private Map<AlbumId, List<Photo>> buildMapOfPhotosPerAlbum(List<Photo> listOfPhotos) {
        if (listOfPhotos == null) {
            return Map.of();
        }
        Map<AlbumId, List<Photo>> mapOfPhotosPerAlbum = new HashMap<>();
        for (Photo photo : listOfPhotos) {
            AlbumId albumId = albumIdFactory.getInstance(photo);
            mapOfPhotosPerAlbum.computeIfAbsent(albumId, this::createAlbumListOfPhotos);
            mapOfPhotosPerAlbum.merge(albumId, Arrays.asList(photo), this::appendPhotoToAlbum);
        }
        return mapOfPhotosPerAlbum;
    }

    private List<Photo> appendPhotoToAlbum(List<Photo> photos, List<Photo> newPhotos) {
        photos.addAll(newPhotos);
        return photos;
    }

    private List<Photo> createAlbumListOfPhotos(AlbumId key) {
        return new ArrayList<>();
    }
}

package com.victorsemperevidal.albumsandfotos.infraestructure.factories.projections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumId;
import com.victorsemperevidal.albumsandfotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandfotos.domain.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.daos.projections.AlbumAndPhotoProjectionDao;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.AlbumIdFactory;

@Service
public class AlbumAndPhotoProjectionFactory {
    private AlbumIdFactory albumIdFactory;

    @Autowired
    public AlbumAndPhotoProjectionFactory(AlbumIdFactory albumIdFactory) {
        super();
        this.albumIdFactory = albumIdFactory;
    }

    public List<AlbumAndPhotoProjection> getInstancesFromDaos(List<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos) {
        List<AlbumAndPhotoProjection> albumsAndPhotos = new ArrayList<>();
        if (albumsAndPhotosDaos != null) {
            for (AlbumAndPhotoProjectionDao dao : albumsAndPhotosDaos) {
                albumsAndPhotos.add(getInstance(dao));
            }
        }
        return albumsAndPhotos;
    }

    private AlbumAndPhotoProjection getInstance(AlbumAndPhotoProjectionDao albumAndPhotosDao) {
        return new AlbumAndPhotoProjection(albumAndPhotosDao.getAlbumId(),
                albumAndPhotosDao.getUserId(), albumAndPhotosDao.getAlbumTitle(), albumAndPhotosDao.getPhotoId(),
                albumAndPhotosDao.getPhotoTitle(),
                albumAndPhotosDao.getPhotoUrl(), albumAndPhotosDao.getPhotoThumbnailUrl());
    }

    public List<AlbumAndPhotoProjection> getInstancesFrom(List<Album> listOfAlbums, List<Photo> listOfPhotos) {
        Map<AlbumId, Album> albumsPerId = buildMapOfAlbumsPerId(listOfAlbums);
        return getInstancesFrom(albumsPerId, listOfPhotos);
    }

    private List<AlbumAndPhotoProjection> getInstancesFrom(Map<AlbumId, Album> albumsPerId, List<Photo> listOfPhotos) {
        List<AlbumAndPhotoProjection> albumsAndPhotos = new ArrayList<>();
        if (listOfPhotos != null) {
            for (Photo photo : listOfPhotos) {
                AlbumId albumId = albumIdFactory.getInstance(photo);
                if (albumsPerId.containsKey(albumId)) {
                    albumsAndPhotos.add(getInstance(albumsPerId.get(albumId), photo));
                }
            }
        }
        return albumsAndPhotos;
    }

    private AlbumAndPhotoProjection getInstance(Album album, Photo photo) {
        return new AlbumAndPhotoProjection(album.getId(), album.getUserId(), album.getTitle(),
                photo.getId(), photo.getTitle(), photo.getUrl(), photo.getThumbnailUrl());
    }

    private Map<AlbumId, Album> buildMapOfAlbumsPerId(List<Album> listOfAlbums) {
        Map<AlbumId, Album> map = new HashMap<>();
        if (listOfAlbums != null) {
            for (Album album : listOfAlbums) {
                AlbumId albumId = albumIdFactory.getInstance(album);
                map.put(albumId, album);
            }
        }
        return map;
    }

}

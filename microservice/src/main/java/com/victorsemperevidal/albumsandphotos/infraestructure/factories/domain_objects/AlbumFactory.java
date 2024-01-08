package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;

public interface AlbumFactory {

        public Album getInstance(
                        com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album album);

        public Collection<Album> getInstancesFromAlbumsApi(
                        Collection<com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album> apiAlbums);

        public Collection<Album> getInstancesFromAlbumsDao(Collection<AlbumDao> listOfAlbumsDaos);

        public Album getInstance(AlbumAndPhotoProjection albumProjection);

}

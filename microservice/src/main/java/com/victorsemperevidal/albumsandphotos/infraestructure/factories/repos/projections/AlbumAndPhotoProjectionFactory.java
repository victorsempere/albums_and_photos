package com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.projections.AlbumAndPhotoProjectionDao;

/**
 * Interface que genera una salida normalizada del listado de álbumes y fotos para que se pueda procesar y generar la salida en los puntos de entrada.
 * 
 * Las fuentes de datos en nuestro caso son la base de datos y las instancias de datos en memoria.
 * 
 */
public interface AlbumAndPhotoProjectionFactory {

    public Collection<AlbumAndPhotoProjection> getInstancesFromListOfDaos(
            Collection<AlbumAndPhotoProjectionDao> albumsAndPhotosDaos);

    public Collection<AlbumAndPhotoProjection> getInstancesFromListOfAlbumsAndPhotos(Collection<Album> listOfAlbums,
            Collection<Photo> listOfPhotos);

}

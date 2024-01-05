package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.victorsemperevidal.albumsandphotos.infraestructure.daos.AlbumDao;
import com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data.projections.AlbumAndPhotoProjectionDao;

import jakarta.transaction.Transactional;

@Repository
@RepositoryRestResource(path = "db-albums")
public interface SpringDataAlbumRepository extends ListCrudRepository<AlbumDao, Long> {

    @Query(value = "SELECT a.id AS albumId, a.userId AS userId, a.title AS albumTitle, p.id AS photoId, p.title AS photoTitle, p.url AS photoUrl, p.thumbnailUrl AS photoThumbnailUrl FROM AlbumDao a LEFT JOIN a.photos p ORDER BY a.id, p.id")
    @RestResource(exported = false)
    List<AlbumAndPhotoProjectionDao> getAlbumsAndPhotos();

    @Override
    @RestResource(exported = false)
    <S extends AlbumDao> S save(S entity);

    @Override
    @RestResource(exported = false)
    @Transactional
    <S extends AlbumDao> List<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(AlbumDao entity);

    @Override
    @RestResource(exported = false)
    @Transactional
    void deleteAll();

}
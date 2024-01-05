package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database.spring_data;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.victorsemperevidal.albumsandphotos.infraestructure.daos.PhotoDao;

@Repository
@RepositoryRestResource(path = "db-photos")
public interface SpringDataPhotoRepository extends ListCrudRepository<PhotoDao, Long> {

    @Override
    @RestResource(exported = false)
    <S extends PhotoDao> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends PhotoDao> List<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void delete(PhotoDao entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
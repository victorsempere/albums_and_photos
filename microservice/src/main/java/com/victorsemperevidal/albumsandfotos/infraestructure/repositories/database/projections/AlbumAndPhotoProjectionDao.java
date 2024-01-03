package com.victorsemperevidal.albumsandfotos.infraestructure.repositories.database.projections;

public interface AlbumAndPhotoProjectionDao {
    public Long getAlbumId();

    public Long getUserId();

    public String getAlbumTitle();
    
    public Long getPhotoId();

    public String getPhotoTitle();

    public String getPhotoUrl();

    public String getPhotoThumbnailUrl();
}

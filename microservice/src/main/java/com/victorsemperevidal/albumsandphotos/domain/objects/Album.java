package com.victorsemperevidal.albumsandphotos.domain.objects;

import java.io.Serializable;

public class Album implements Serializable, Comparable<Album> {
    private final Long userId;
    private final Long id;
    private final String title;

    public Album(Long userId, Long id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Album other = (Album) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Album [userId=" + userId + ", id=" + id + ", title=" + title + "]";
    }

    @Override
    public int compareTo(Album o) {
        return getId().compareTo(o.getId());
    }

}

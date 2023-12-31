package com.victorsemperevidal.albumsandfotos.infraestructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiClient;

@Configuration
public class AlbumsAndFotosApplicationConfiguration {
	@Value("${albums-and-fotos-application.configs.albums-api.debugging:false}")
	private boolean albumsApiDebugging;
	@Value("${albums-and-fotos-application.configs.albums-api.base-path:}")
	private String albumsApiBasePath;
	@Value("${albums-and-fotos-application.configs.photos-api.debugging:false}")
	private boolean photosApiDebugging;
	@Value("${albums-and-fotos-application.configs.photos-api.base-path:}")
	private String photosApiBasePath;

	public AlbumsAndFotosApplicationConfiguration() {
		super();
	}

	@Bean
	public AlbumsApi getAlbumsApi() {
		AlbumsApi albumsApi = new AlbumsApi();
		ApiClient apiClient = albumsApi.getApiClient();
		apiClient.setDebugging(albumsApiDebugging);
		if (albumsApiBasePath != null && !albumsApiBasePath.isBlank()) {
			apiClient.setBasePath(albumsApiBasePath);
		}

		return albumsApi;
	}

	@Bean
	public PhotosApi getPhotosApi() {
		PhotosApi photosApi = new PhotosApi();
		ApiClient apiClient = photosApi.getApiClient();
		apiClient.setDebugging(photosApiDebugging);
		if (photosApiBasePath != null && !photosApiBasePath.isBlank()) {
			apiClient.setBasePath(photosApiBasePath);
		}

		return photosApi;
	}

}

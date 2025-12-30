package com.mrretektor.astrasearch.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mrretektor.astrasearch.dao.ImageDao;
import com.mrretektor.astrasearch.domain.Image;

@Component
public class ImageDaoImpl implements ImageDao {

	private final JdbcTemplate jdbcTemplate;
	
	public ImageDaoImpl(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void create(Image image) {
		jdbcTemplate.update("INSERT INTO images (url, caption) VALUES (?, ?)",
				image.getUrl(),
				image.getCaption()
				);
	}
}

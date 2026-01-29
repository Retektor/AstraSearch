package com.mrretektor.astrasearch.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
	public Image create(Image image) {
		String url = image.getUrl();
		
		String sql = "INSERT INTO images (url, caption) VALUES (?, ?) RETURNING id, url, caption";
		
		try {
			return jdbcTemplate.queryForObject(sql,
					new ImageRowMapper(),
					image.getCaption(),
					image.getUrl());
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static class ImageRowMapper implements RowMapper<Image> {
		
		@Override
		public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Image.builder()
					.id(rs.getLong("id"))
					.url(rs.getString("url"))
					.caption(rs.getString("caption"))
					.build();
		}
	}
}

package com.bibliotheques.ws.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bibliotheques.ws.consumer.contract.dao.PhotoDao;
import com.bibliotheques.ws.consumer.impl.rowmapper.edition.PhotoRM;
import com.bibliotheques.ws.model.bean.edition.Photo;

@Named
public class PhotoDaoImpl extends AbstractDaoImpl implements PhotoDao {

	@Override
	public Photo getPhoto(int photoId) {
		String vSQL = "SELECT * FROM public.photo WHERE id= "+photoId;
		JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource()); 
		RowMapper<Photo> vRowMapper= new PhotoRM();
		List<Photo> vListPhoto = vJdbcTemplate.query(vSQL, vRowMapper);
		return vListPhoto.get(0);
	}
}
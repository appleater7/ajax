package dao.Impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import db.DBCon;

public class MovieDAOImpl implements MovieDAO{
	
	private String selectMovieList = "select mi_num, mi_name, mi_year, mi_national, "
			+ " mi_vendor, mi_director from movie_info";
	private String insertMovie = "insert into movie_info (mi_num, mi_name, mi_year, mi_national, "
			+ " mi_vendor, mi_director) values(seq_mi_num.nextval, ?,?,?,?,?)";

	@Override
	public List<Map<String, String>> selectMovieList() {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectMovieList);
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> movieList = new ArrayList<>();
			while (rs.next()) {
				Map<String, String> movie = new HashMap<>();
				movie.put("miNum", rs.getInt("mi_num") + "");
				movie.put("miName", rs.getString("mi_name"));
				movie.put("miYear", rs.getInt("mi_year") + "");
				movie.put("miNational", rs.getString("mi_national").toUpperCase());
				movie.put("miVendor", rs.getString("mi_vendor"));
				movie.put("miDirector", rs.getString("mi_director"));
				movieList.add(movie);
			}
			return movieList;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int insertMovie(Map<String, String> movie) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(insertMovie);
			ps.setString(1, movie.get("miName"));
			ps.setString(2, movie.get("miYear"));
			ps.setString(3, movie.get("miNational"));
			ps.setString(4, movie.get("miVendor"));
			ps.setString(5, movie.get("miDirector"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

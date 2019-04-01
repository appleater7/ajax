package service.Impl;

import java.util.List;
import java.util.Map;

import dao.MovieDAO;
import dao.Impl.MovieDAOImpl;

public class MovieServiceImpl implements service.MovieService {

	private MovieDAO mdao = new MovieDAOImpl();
	
	@Override
	public List<Map<String, String>> selectMovieList() {
		return mdao.selectMovieList(); 
	}

	@Override
	public int insertMovie(Map<String, String> movie) {
		return mdao.insertMovie(movie);
	}

}

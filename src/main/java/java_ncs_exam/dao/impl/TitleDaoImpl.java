package java_ncs_exam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.JdbcConn;
import java_ncs_exam.dao.TitleDao;
import java_ncs_exam.dto.Title;

public class TitleDaoImpl implements TitleDao {

	private static TitleDaoImpl instance = new TitleDaoImpl();
	
	private TitleDaoImpl() {}

	public static TitleDaoImpl getInstance() {
		if(instance == null) {
			instance = new TitleDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Title> selectTitleByAll() {
		String sql = "select tno,tname from title";
		try(Connection con = JdbcConn.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				List<Title> list = new ArrayList<>();
				do {
					list.add(getTitle(rs));
				}while(rs.next());
				return list;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Title getTitle(ResultSet rs) throws SQLException {
		int tNo = rs.getInt("tno");
		String tName = rs.getString("tname"); 
		return new Title(tNo, tName);
	}

	@Override
	public Title selectTitleByNo(Title title) {
		String sql = "select tno,tname from title  where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				pstmt.setInt(1,title.gettNo());
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						return getTitle(rs);
					}
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertTitle(Title title) {
		String sql = "insert into title values(?,?)";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, title.gettNo());
			pstmt.setNString(2, title.gettName());
			return pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateTitle(Title title) {
		String sql = "update title set tname = ? where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, title.gettName());
			pstmt.setInt(2, title.gettNo());
			return pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteTitle(int titleNo) {
		String sql = "delete from title where tno = ?";
		try(Connection con = JdbcConn.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, titleNo);
			return pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
}

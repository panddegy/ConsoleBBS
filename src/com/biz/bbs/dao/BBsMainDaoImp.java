package com.biz.bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.bbs.vo.BBsVO;

public class BBsMainDaoImp implements BBsMainDao {
	
	Connection dbConn;
	
	public BBsMainDaoImp() {
		this.dbConnection();
	}
	
	public void dbConnection() {
		
		String dbDriver="oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(dbDriver);
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="bbsuser";
			String password="1234";
			dbConn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void insert(BBsVO vo) {
		// TODO Auto-generated method stub
		String sql=" insert into tbl_bbs_main ";
		sql+=" values(seq_bbs_main.nextval, ";
		sql+="'"+vo.getB_date()+"', ";
		sql+="'"+vo.getB_auth()+"', ";
		sql+="'"+vo.getB_subject()+"', ";
		sql+="'"+vo.getB_text()+"') ";
		
		sql=" insert into tbl_bbs_main ";
		sql+=" values( seq_bbs_main.nextval, ?, ?, ?, ?)";
		
		
		
		PreparedStatement ps;
		
		try {
			ps=dbConn.prepareStatement(sql);
			ps.setString(1, vo.getB_date());
			ps.setString(2, vo.getB_auth());
			ps.setString(3, vo.getB_subject());
			ps.setString(4, vo.getB_text());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<BBsVO> selectAll() {
		// TODO Auto-generated method stub
		String sql=" Select * from tbl_bbs_main ";
		PreparedStatement ps;
		try {
			ps=dbConn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			List<BBsVO> bbsList=new ArrayList<>();
			while(rs.next()) {
				long id=rs.getLong("b_id");
				String strDate=rs.getString("b_date");
				String strAuth=rs.getString("b_auth");
				String strSubject=rs.getString("b_subject");
				String strText=rs.getString("b_text");
				BBsVO vo=new BBsVO();
				vo.setB_id(id);
				vo.setB_date(strDate);
				vo.setB_auth(strAuth);
				vo.setB_subject(strSubject);
				vo.setB_text(strText);
				bbsList.add(vo);
			}
			return bbsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public BBsVO fintByID(long id) {
		// TODO Auto-generated method stub
		String sql=" select * from tbl_bbs_main ";
		sql+=" where b_id=?";
		PreparedStatement ps;
		try {
			ps=dbConn.prepareStatement(sql);
			ps.setLong(1,id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			BBsVO vo=new BBsVO();
			vo.setB_id(rs.getLong("b_id"));
			vo.setB_date(rs.getString("b_date"));
			vo.setB_auth(rs.getString("b_auth"));
			vo.setB_subject(rs.getString("b_subject"));
			vo.setB_text(rs.getString("b_text"));
			return vo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void update(BBsVO vo) {
		// TODO Auto-generated method stub
		String sql=" update tbl_bbs_main ";
		sql+=" set b_date=? , b_auth=? , b_subject=? , b_text=? where b_id=? ";
		
		PreparedStatement ps;
		
		try {
			ps=dbConn.prepareStatement(sql);
			ps.setString(1, vo.getB_date());
			ps.setString(2, vo.getB_auth());
			ps.setString(3, vo.getB_subject());
			ps.setString(4, vo.getB_text());
			ps.setLong(5, vo.getB_id());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		String sql=" delete tbl_bbs_main ";
		sql+=" where b_id=?";
		
		PreparedStatement ps;
		try {
			ps=dbConn.prepareStatement(sql);
			ps.setLong(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}


















package com.cafe24.jblog2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog2.vo.UserVo;

@Repository
public class UserDao extends DBConnection {
	@Autowired
	private SqlSession sqlSession;
	public boolean insert(UserVo vo) {
			int count = sqlSession.insert("user.insert",vo);
//			System.out.println(vo);
			return count ==1;
	}

	public List<UserVo> getList() {
		List<UserVo> list = new ArrayList<UserVo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select *\r\n" + " from user";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next() == true) {
				Long no = rs.getLong(1);
				String id = rs.getString(2);
				String name = rs.getString(3);
				String password = rs.getString(4);
				String regDate = rs.getString(5);

				UserVo vo = new UserVo();

				vo.setNo(no);
				vo.setId(id);
				vo.setPassword(password);
				vo.setName(name);
				vo.setRegDate(regDate);

				list.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Driver loading fail : " + e);
		} catch (SQLException e) {
			System.out.println("error " + e);
		} finally {
			// 자원정리(Clean-Up)
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public UserVo get(UserVo vo) {
		return sqlSession.selectOne("user.getByIdAndPassword",vo);
	}
	public UserVo get(String id) {
		return sqlSession.selectOne("user.getById",id);
	}
}

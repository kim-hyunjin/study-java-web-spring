package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.sql.DataSource;

public class BoardDaoImpl implements BoardDao {

  DataSource dataSource;

  public BoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public int insert(Board board) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("insert into lms_board(conts) values(?)")) {
      stmt.setString(1, board.getTitle());
      // DBMS에게 데이터 입력하라는 명령을 보낸다.
      // SQL 문법:
      // Insert into 테이블명(컬럼명1, 컬럼명2, ...) values(값, 값2, ...)
      // => executeUpdate의 리턴값 : SQL 명령에 따라 변경된 데이터의 개수이다.
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, conts, cdt, vw_cnt from lms_board order by board_id desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("conts"));
        board.setDate(rs.getDate("cdt"));
        board.setViewCount(rs.getInt("vw_cnt"));
        list.add(board);
      }
      return list;
    }
  }

  @Override
  public Board findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select board_id, conts, cdt, vw_cnt from lms_board where board_id=?");) {
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setTitle(rs.getString("conts"));
          board.setDate(rs.getDate("cdt"));
          board.setViewCount(rs.getInt("vw_cnt"));
          return board;
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(//
            "update lms_board set"//
                + " conts=?"//
                + " where board_id=?")) {
      stmt.setString(1, board.getTitle());
      stmt.setInt(2, board.getNo());
      // DBMS에게 데이터를 변경하라는 명령을 보낸다.
      // SQL 문법:
      // update 테이블명 set 컬럼명1=값1, 컬럼명2=값2, ... where 조건
      // => executeUpdate의 리턴값 : SQL 명령에 따라 변경된 데이터의 개수이다.
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from lms_board where board_id=?")) {
      stmt.setInt(1, no);
      // DBMS에게 데이터를 삭제하라는 명령을 보낸다.
      // SQL 문법:
      // delete from 테이블명 where 조건
      // => executeUpdate의 리턴값 : SQL 명령에 따라 변경된 데이터의 개수이다.
      return stmt.executeUpdate();
    }
  }

}

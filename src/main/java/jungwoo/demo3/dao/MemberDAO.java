package jungwoo.demo3.dao;

import lombok.Cleanup;
import jungwoo.demo3.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {


    public MemberVO getWithPassword(String mid, String mpw) throws Exception {

        String query = "select mid, mpw from member where mid =? and mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setString(1, mid);
        preparedStatement.setString(2, mpw);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        memberVO = MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .build();

        return memberVO;
    }


    public void updateUuid(String mid, String uuid) throws  Exception {

        String sql = "update member set uuid =? where mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =
                connection.prepareStatement(sql);

        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, mid);


        preparedStatement.executeUpdate();

    }

    public MemberVO selectUUID(String uuid) throws Exception{

        String query = "select mid, mpw, uuid from member where uuid =?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement =
                connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(resultSet.getString(1))
                .mpw(resultSet.getString(2))
                .uuid(resultSet.getString(3))
                .build();

        return memberVO;

    }


    public void insertMember(MemberVO member) throws Exception {
        String sql = "INSERT INTO member (mid, mpw, uuid) VALUES (?, ?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, member.getMid());
        preparedStatement.setString(2, member.getMpw());
        preparedStatement.setString(3, member.getUuid());

        preparedStatement.executeUpdate();
    }

    public boolean existsByMid(String mid) throws Exception {
        String sql = "SELECT COUNT(*) FROM member WHERE mid = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, mid);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        return resultSet.getInt(1) > 0;
    }
}
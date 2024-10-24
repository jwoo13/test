package jungwoo.demo3.dao;


import lombok.Cleanup;
import jungwoo.demo3.domain.VoteVO;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class VoteDAO {




    //가져오기
    public List<VoteVO> selectAll()throws Exception  {

        String sql = "select * from vote";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();


        List<VoteVO> list = new ArrayList<>();

        while(resultSet.next()) {
            VoteVO vo = VoteVO.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .build();

            list.add(vo);
        }

        return list;
    }


    public void insert(VoteVO vo) throws Exception {
        String sql = "INSERT INTO vote (title) VALUES (?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, vo.getTitle());
        preparedStatement.executeUpdate();

        @Cleanup ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            vo.setId(generatedKeys.getLong(1));
        }
    }

    public VoteVO selectOne(Long id) throws Exception {
        String sql = "select * from vote where id = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        VoteVO vo = VoteVO.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .build();

        return vo;
    }

    public void deleteOne(Long id) throws Exception {

        String sql = "delete from vote where id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, id);

        preparedStatement.executeUpdate();
    }

    public void updateOne(VoteVO voteVO) throws Exception {

        String sql = "update vote set title =? where id =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, voteVO.getTitle());
        preparedStatement.setLong(2, voteVO.getId());

        preparedStatement.executeUpdate();
    }


    public List<VoteVO> searchByKeyword(String keyword) throws Exception {
        String sql = "SELECT * FROM vote WHERE title LIKE ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + keyword + "%");

        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();

        List<VoteVO> list = new ArrayList<>();

        while (resultSet.next()) {
            VoteVO vo = VoteVO.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .build();
            list.add(vo);
        }

        return list;
    }
}
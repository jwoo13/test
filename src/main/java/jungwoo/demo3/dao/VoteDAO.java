package jungwoo.demo3.dao;


import lombok.Cleanup;
import jungwoo.demo3.domain.VoteVO;

import java.sql.*;
import java.util.*;

public class VoteDAO {

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
        String voteSql = "INSERT INTO vote (title) VALUES (?)";
        String optionSql = "INSERT INTO vote_option (vote_id, option_text) VALUES (?, ?)";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        connection.setAutoCommit(false);

        try {
            @Cleanup PreparedStatement voteStmt = connection.prepareStatement(voteSql, Statement.RETURN_GENERATED_KEYS);
            voteStmt.setString(1, vo.getTitle());
            voteStmt.executeUpdate();

            @Cleanup ResultSet generatedKeys = voteStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                vo.setId(generatedKeys.getLong(1));
            }

            @Cleanup PreparedStatement optionStmt = connection.prepareStatement(optionSql);
            for (String option : vo.getOptions()) {
                optionStmt.setLong(1, vo.getId());
                optionStmt.setString(2, option);
                optionStmt.addBatch();
            }
            optionStmt.executeBatch();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        }
    }

    public VoteVO selectOne(Long id) throws Exception {
        String voteSql = "SELECT * FROM vote WHERE id = ?";
        String optionSql = "SELECT * FROM vote_option WHERE vote_id = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement voteStmt = connection.prepareStatement(voteSql);
        voteStmt.setLong(1, id);
        @Cleanup ResultSet voteResult = voteStmt.executeQuery();

        if (!voteResult.next()) {
            throw new IllegalArgumentException("ID 투표가 존재하지 않음");
        }

        @Cleanup PreparedStatement optionStmt = connection.prepareStatement(optionSql);
        optionStmt.setLong(1, id);
        @Cleanup ResultSet optionResult = optionStmt.executeQuery();

        List<String> options = new ArrayList<>();
        while (optionResult.next()) {
            options.add(optionResult.getString("option_text"));
        }

        return VoteVO.builder()
                .id(voteResult.getLong("id"))
                .title(voteResult.getString("title"))
                .options(options)
                .build();
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

    public void incrementVoteCount(Long voteId, String selectedOption) throws Exception {
        String sql = "UPDATE vote_option SET vote_count = vote_count + 1 WHERE vote_id = ? AND option_text = ?";

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, voteId);
        stmt.setString(2, selectedOption);

        stmt.executeUpdate();
    }


}
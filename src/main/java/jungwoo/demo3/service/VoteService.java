package jungwoo.demo3.service;


import jungwoo.demo3.dao.VoteDAO;
import jungwoo.demo3.domain.VoteVO;
import jungwoo.demo3.dto.VoteDTO;
import jungwoo.demo3.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum VoteService {

    INSTANCE;

    private VoteDAO dao;
    private ModelMapper modelMapper;

    VoteService() {
        dao = new VoteDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<VoteDTO> listAll()throws Exception {

        List<VoteVO> voList = dao.selectAll();

        log.info(voList);

        List<VoteDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, VoteDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public void createVote(VoteDTO voteDTO) throws Exception {
        VoteVO voteVO = modelMapper.map(voteDTO, VoteVO.class);

        log.info(voteVO);

        dao.insert(voteVO);
    }

    public VoteDTO getVote(Long id) throws Exception {

        VoteVO voteVO = dao.selectOne(id);
        VoteDTO voteDTO = modelMapper.map(voteVO, VoteDTO.class);

        return voteDTO;
    }



    public void removeVote(Long id)throws Exception {

        dao.deleteOne(id);
    }

    public void modifyVote(VoteDTO voteDTO)throws Exception {


        VoteVO voteVO = modelMapper.map(voteDTO, VoteVO.class);

        dao.updateOne(voteVO);

    }

    public List<VoteDTO> searchVotesByKeyword(String keyword) throws Exception {
        List<VoteVO> voList = dao.searchByKeyword(keyword);

        log.info("검색된 투표 목록: " + voList);

        return voList.stream()
                .map(vo -> modelMapper.map(vo, VoteDTO.class))
                .collect(Collectors.toList());
    }


}
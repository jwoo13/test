package jungwoo.demo3.service;

import jungwoo.demo3.domain.MemberVO;
import jungwoo.demo3.dto.MemberDTO;
import jungwoo.demo3.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import jungwoo.demo3.dao.MemberDAO;

import java.sql.Connection;
import java.sql.DriverManager;


@Log4j2
public enum MemberService {
    INSTANCE;

    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService() {

        dao = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();

    }


    public MemberDTO login(String mid, String mpw)throws Exception {

        MemberVO vo = dao.getWithPassword(mid, mpw);

        MemberDTO memberDTO = modelMapper.map(vo, MemberDTO.class);

        return modelMapper.map(vo, MemberDTO.class);
    }

    public void updateUuid(String mid, String uuid)throws Exception {

        dao.updateUuid(mid, uuid);

    }

    public MemberDTO getByUUID(String uuid) throws  Exception {

        MemberVO vo = dao.selectUUID(uuid);

        if (vo == null) {
            throw new Exception("User not found with UUID: " + uuid);
        }

        return modelMapper.map(vo, MemberDTO.class);
    }

    public void register(MemberDTO memberDTO) throws Exception {

        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);

        dao.insertMember(memberVO);
    }


}
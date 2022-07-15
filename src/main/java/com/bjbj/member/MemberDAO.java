package com.bjbj.member;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession session;
	
	/* *************** �α��� *************** */
	public MemberDTO login(String email, String password)throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);
		return session.selectOne("memberMapper.login", map);
	}
	
	/* *************** ȸ������ *************** */
	public int insert(MemberDTO dto)throws Exception{
		return session.insert("memberMapper.insert", dto);
	}
	
	/* *************** �̸��� �ߺ�Ȯ�� *************** */
	public boolean confirmEmail(String email)throws Exception{
		return session.selectOne("memberMapper.confirmEmail", email);
	}
	
	/* *************** �г��� �ߺ�Ȯ�� *************** */
	public boolean confirmNickname(String nickname)throws Exception{
		return session.selectOne("memberMapper.confirmNickname", nickname);
	}
	
	/* *************** Email ã�� *************** */
	public MemberDTO searchEmail(String name, String phone)throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		map.put("phone", phone);
		return session.selectOne("memberMapper.searchEmail", map);
	}
	
	/* *************** ��й�ȣ ���� *************** */
	public void modifyPw(String email, String tempPw)throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("password", tempPw);
		
		System.out.println(email + " : " + tempPw);
		System.out.println(map.toString());
		
		session.update("memberMapper.modifyPw", map);
	}

	
}

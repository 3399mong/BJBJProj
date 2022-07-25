package com.bjbj.bookclub;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bjbj.manager.ReportBookroomDTO;
import com.bjbj.manager.ReportDTO;
import com.bjbj.member.MemberDTO;

@Repository
public class BookclubDAO {
	@Autowired
	private SqlSession session;

	/* 전제 조회 */
	public List<BookclubDTO> selectAll() throws Exception {
		return session.selectList("clubMapper.selectAll");
	}
	
	/* 최근순으로 조회 */
	public List<BookclubDTO> selectLately() throws Exception {
		return session.selectList("clubMapper.selectLately");
   }

	public List<BookclubDTO> selectList() throws Exception {
		return session.selectList("clubMapper.selectList");
	}

	// bookroom table insert
	public void insert(BookclubDTO dto) throws Exception {
		session.insert("clubMapper.insert", dto);
	}

	// role table insert
	public void insertRole(RoleDTO dto) throws Exception {
		session.insert("roleMapper.insert", dto);
	}

	// room_id 시퀀스 번호 생성
	public int selectSeq() throws Exception {
		return session.selectOne("clubMapper.selectSeq");
	}

	public BookclubDTO selectOne(int room_id) throws Exception {
		return session.selectOne("clubMapper.selectOne", room_id);
	}

	public RoleDTO selectRole(String email) throws Exception {
		return session.selectOne("roleMapper.selectRole", email);

	}

	// 날짜 형식 변경("MM월 dd일" / String to String)
	public String getStrDate(String string) {
		String rs = null;
		try {
			// String to Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(sdf.parse(string).getTime());

			// Date to String
			SimpleDateFormat sdf2 = new SimpleDateFormat("MM월 dd일(E)");
			rs = sdf2.format(date);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	//모임 신고하기 데이터 삽입
	public void insertReportBookroom(ReportBookroomDTO dto) throws Exception{
		session.insert("clubMapper.insertReportBookroom", dto);
	}
	
	//회원 신고하기
	public void insertReport(ReportDTO dto) throws Exception{
		session.insert("clubMapper.insertReport", dto);
	}
	
	public BookclubDTO selectOne(String room_title) throws Exception {
		return session.selectOne("clubMapper.selectOne", room_title);
	}
	
	public MemberDTO selectNickname(String nickname) throws Exception {
		return session.selectOne("clubMapper.selectNickname", nickname);
	}
	
	// waiting 테이블에 데이터 삽입
	public void insertWaiting(WaitingDTO dto) throws Exception {
		session.insert("waitingMapper.insertWaiting", dto);
	}

	// 이메일로 waiting 테이블 데이터 받기
	public WaitingDTO selectByEmail(String email) throws Exception {
		return session.selectOne("waitingMapper.selectByEmail", email);
	}

	// Room_id 에 따른 지원 인원 보기
	public List<String> selectByRoom(int room_id) throws Exception {
		return session.selectList("waitingMapper.selectByRoom", room_id);
	}

	// waiting 테이블에서 이메일로 해당 데이터 삭제
	public void deleteByEmail(String email) throws Exception {
		session.delete("waitingMapper.deleteByEmail", email);
	}
	
	public BookclubDTO selectBookroom(int book_id) throws Exception {
		return session.selectOne("clubMapper.selectOne", book_id);
	}

	// 현재인원 + 1
	public void updateCurrent(int room_id) throws Exception {
		session.update("clubMapper.updateCurrent", room_id);
	}

	public int updateStatus(String room_status, int room_id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("room_status", room_status);
		map.put("room_id", room_id);

		return session.update("clubMapper.updateStatus", map);
	}

	/* 페이징 */
	public int getCount() throws Exception {
		return session.selectOne("clubMapper.getCount");
	}
	
	/* 페이징 */
	public List<BookclubDTO> selectPage(int start, int end) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return session.selectList("clubMapper.selectPage", map);
	}
	
	/* 찜한 모임 조회 */
	public List<BookclubDTO> likeClub() throws Exception {
		return session.selectList("clubMapper.likeClub");
	}
}

package com.bjbj.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value = "/member")
@Controller
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MemberService memberService;
	

	//DB�۾��� �ʿ��� ��ŭ DAO�� �����ؾ���
	
	//���̵�� �̸����� ������ Ư���� ������ ����..
	@RequestMapping("/noticeMail")
	public ModelAndView sendEmail(String email) throws Exception {
		// ���� ������
		ModelAndView mv = new ModelAndView();
		
		String addr = "bjbjcommunity@gmail.com";
		String subject = "Notice";
		String tempPw = mailService.makePw();
		String body = "Test : " + tempPw ;
		
		mailService.sendEmail(email, addr, subject, body);
		
		// ��й�ȣ ����
		memberService.modifyPw(email, tempPw);
		
		mv.setViewName("redirect:/");

		return mv;
	}
	


}
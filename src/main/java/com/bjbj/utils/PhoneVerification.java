package com.bjbj.utils;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@GetMapping("/confirmEmail")
public class PhoneVerification {
	public static String sendPhoneMail(String phone) {
		String api_key = "NCSVC9WIIKEOQ2L1";
		String api_secret = "ROYFZ315BXKDOPLHEOHO9WMW1SPYXDEM";
		Message coolSms = new Message(api_key, api_secret);
		
		// 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    
	    String randNum = PhoneVerification.randomNumGenerator();
	    params.put("to", phone);
	    params.put("from", "01033260864");
	    params.put("type", "SMS");
	    params.put("text", "[��������Ŀ�´�Ƽ] [" + randNum + "] ȸ������ �������� �߼��Դϴ�. ȸ������ â�� �Է��ϼ���!");
	    params.put("app_version", "test app 1.2"); // application name and version
	
		try {
			JSONObject obj = (JSONObject)coolSms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
		
		return randNum;
	}
	
	public static String randomNumGenerator() {
		Random rand = new Random();
		
		int createNum = 0; // 1�ڸ� ����
		String randNum = ""; // 1�ڸ� ���� -> ���ڷ� ����ȯ
		String resultNum = ""; // ��� ����
		int letter = 5; // �ڸ���
		
		for (int i = 0; i < letter; i++) {
			createNum = rand.nextInt(9); // 0-9 ������ �� �߿��� ���� ����
			randNum = Integer.toString(createNum); // ������ ������ ���ڷ� ��ȯ
			resultNum += randNum; // ������ ���� ���ڿ��� ����ŭ ����
		}
		System.out.println(resultNum);
		return resultNum;
	}
}

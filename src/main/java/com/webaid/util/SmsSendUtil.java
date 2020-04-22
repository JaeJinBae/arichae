package com.webaid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;

public class SmsSendUtil {
	public void sendSMS(String type, String name, String phone){

		try{
			
			final String encodingType = "utf-8";
			final String boundary = "____boundary____";
		
			/**************** 문자전송하기 예제 ******************/
			/* "result_code":결과코드,"message":결과문구, */
			/* "msg_id":메세지ID,"error_cnt":에러갯수,"success_cnt":성공갯수 */
			/* 동일내용 > 전송용 입니다.  
			/******************** 인증정보 ********************/
			String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL
			
			Map<String, String> sms = new HashMap<String, String>();
			
			sms.put("user_id", "bjj7425"); // SMS 아이디
			sms.put("key", "uybnfxh6xc0wbogbgu7nqgfnbqvx8xy8"); //인증키
			
			//iq1426 유한통증 아이디
			// 1af4k1p8j29ulusxjdkctfgfasgaby8i 유한통증 인증키
			//uybnfxh6xc0wbogbgu7nqgfnbqvx8xy8 웹에이드 인증키
			/******************** 인증정보 ********************/
			String msg = type+" 문의가 접수되었습니다."; 
			msg += "\n문의자:\n"+phone+"\n";

			String remainSms = smsRemain();
			msg += "잔여: "+remainSms;
			/******************** 전송정보 ********************/
			sms.put("msg", msg); // 메세지 내용
			sms.put("receiver", "010-2837-7425"); // 수신번호
			sms.put("destination", ""); // 수신인 %고객명% 치환
			sms.put("sender", ""); // 발신번호
			sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
			sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
			sms.put("testmode_yn", "Y"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
			sms.put("title", ""); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)
			
			String image = "";
			//image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치
			
			/******************** 전송정보 ********************/
			
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			
			builder.setBoundary(boundary);
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.setCharset(Charset.forName(encodingType));
			
			for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
				String key = i.next();
				builder.addTextBody(key, sms.get(key)
						, ContentType.create("Multipart/related", encodingType));
			}
			
			File imageFile = new File(image);
			if(image!=null && image.length()>0 && imageFile.exists()){
		
				builder.addPart("image",
						new FileBody(imageFile, ContentType.create("application/octet-stream"),
								URLEncoder.encode(imageFile.getName(), encodingType)));
			}
			
			HttpEntity entity = builder.build();
			
			HttpClient client = HttpClients.createDefault();
			HttpPost post = new HttpPost(sms_url);
			post.setEntity(entity);
			
			HttpResponse res = client.execute(post);
			
			String result = "";
			if(res != null){
				BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
				String buffer = null;
				while((buffer = in.readLine())!=null){
					result += buffer;
				}
				in.close();
			}
			
			System.out.println(result);
			if(result.contains("\"success_cnt\":1")){
				System.out.println("result= "+result);
			}else{
				System.out.println("error");
			}
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public String smsRemain(){
		ResponseEntity<Map<String, String>> entity = null;
		String res = "";
		try{

			/**************** 최근 전송 목록 ******************/
			/* "result_code":결과코드,"message":결과문구, */
			/** list : 전송된 목록 배열 ***/
			/******************** 인증정보 ********************/
			String sms_url = "https://apis.aligo.in/remain/"; // 전송요청 URL
			
			String sms = "";
			sms += "user_id=" + "bjj7425"; // SMS 아이디 
			sms += "&key=" + "uybnfxh6xc0wbogbgu7nqgfnbqvx8xy8"; //인증키
			
			//iq1426 원통증아이디
			//1af4k1p8j29ulusxjdkctfgfasgaby8i 유한통증인증키
			//uybnfxh6xc0wbogbgu7nqgfnbqvx8xy8 웹에이드 인증키
			/******************** 인증정보 ********************/
			
			URL url = new URL(sms_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			OutputStream os = conn.getOutputStream();
			os.write(sms.getBytes());
			os.flush();
			os.close();
			
			String result = "";
			String buffer = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			
			while((buffer = in.readLine())!=null){
				result += buffer;
			}
			
			in.close();
			
			System.out.println(result);
			String[] splitRes = result.split(",");
			for(int i=0; i< splitRes.length; i++){
				System.out.println(splitRes[i]);
			}
			
			
			res = splitRes[2].split(":")[1]+"/"+splitRes[3].split(":")[1];

		}catch(MalformedURLException e1){
			System.out.println(e1.getMessage());
		}catch(IOException e2){
			System.out.println(e2.getMessage());
		}
		return res;
	}
}

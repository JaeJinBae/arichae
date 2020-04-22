package com.webaid.util;

import java.io.File;

public class FileDelete {
	public String fileDelete(String path, String fileName){
		try {
			File file = new File(path+fileName);
	        
	        if( file.exists() ){
	            if(file.delete()){
	                System.out.println("파일삭제 성공");
	            }else{
	                System.out.println("파일삭제 실패");
	            }
	        }else{
	            System.out.println("파일이 존재하지 않습니다.");
	        }
	        return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
        
	}
}

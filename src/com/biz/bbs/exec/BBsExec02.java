package com.biz.bbs.exec;

import com.biz.bbs.service.BBsMainService;

public class BBsExec02 {

	public static void main(String[] args) {
		
		BBsMainService bs=new BBsMainService();
		
		bs.bbsMenu();
		System.out.println("게시판 종료");
	}
}

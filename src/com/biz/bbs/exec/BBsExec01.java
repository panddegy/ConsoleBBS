package com.biz.bbs.exec;

import com.biz.bbs.service.BBsMainService;

public class BBsExec01 {
	
	public static void main(String[] args) {
		
		BBsMainService bs=new BBsMainService();
		
		bs.viewBBsList();
	}

}

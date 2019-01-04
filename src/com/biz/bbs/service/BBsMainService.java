package com.biz.bbs.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.bbs.dao.BBsMainDao;
import com.biz.bbs.dao.BBsMainDaoImp;
import com.biz.bbs.vo.BBsVO;

public class BBsMainService {

	List<BBsVO> bbsMainList;
	BBsMainDao mainDao;
	Scanner sc;
	
	public BBsMainService() {
		bbsMainList=new ArrayList<>();
		mainDao=new BBsMainDaoImp();
		sc=new Scanner(System.in);
	}
	
	public void bbsMenu() {
		while(true) {
			this.viewBBsList();
			System.out.println("==============================================");
			System.out.println("1.내용보기 / 2.추가 / 3.수정 / 4.삭제 / 0.종료");
			System.out.println("==============================================");
			System.out.print(">> ");
			String strInput=sc.nextLine();
			
			int intInput=Integer.valueOf(strInput);
			if(intInput==0) return;
			if(intInput==1) this.viewBBsText();
			if(intInput==2) this.insertBBs();
			if(intInput==3) this.updateBBs();
			if(intInput==4) this.deleteBBs();	
		}
	}
	
	private void deleteBBs() {
		// TODO Auto-generated method stub
		this.viewBBsList();
		System.out.println("==============================================");
		System.out.println(">> 삭제할 no를 입력하세요");
		System.out.print(">> ");
		String strID=sc.nextLine();
		if(strID.equals("")) {
			System.out.println(">> 삭제가 취소되었습니다.");
			return;
		}
		long longID=Long.valueOf(strID);
		BBsVO vo=mainDao.fintByID(longID);
		System.out.println(">> 작성일자 : "+vo.getB_date());
		System.out.println(">> 작성자   : "+vo.getB_auth());
		System.out.println(">> 제목     : "+vo.getB_subject());
		System.out.println(">> "+strID+"번을 삭제하시겠습니까? y/n");
		System.out.print(">> ");
		String strYN=sc.nextLine();
		if(strYN.equalsIgnoreCase("y")) {
			mainDao.delete(longID);
			System.out.println(">> 삭제 되었습니다.");
		} else {
			System.out.println(">> 취소 되었습니다.");
		}
		
	}

	private void updateBBs() {
		// TODO Auto-generated method stub
		this.viewBBsList();
		System.out.println("==============================================");
		System.out.println(">> 수정할 no를 입력하세요");
		System.out.print(">> ");
		String strID=sc.nextLine();
		if(strID.equals("")) {
			System.out.println(">> 삭제가 취소되었습니다.");
			return;
		}
		long longID=Long.valueOf(strID);
		BBsVO vo=mainDao.fintByID(longID);
		System.out.println(">> 수정 : 내용입력 취소 : Enter");
		System.out.println("----------------------------------------------");
		System.out.println(">> 작성자 : "+vo.getB_auth());
		System.out.print(">> 수정   : ");
		String strAuth=sc.nextLine();
		if(!strAuth.equals("")) vo.setB_auth(strAuth);
		System.out.println(">> 제목   : "+vo.getB_subject());
		System.out.print(">> 수정   : ");
		String strSubject=sc.nextLine();
		if(!strSubject.equals("")) vo.setB_subject(strSubject);
		System.out.println(">> 내용   : "+vo.getB_text());
		System.out.print(">> 수정   : ");
		String strText=sc.nextLine();
		if(!strText.equals("")) vo.setB_text(strText);
		LocalDate ld=LocalDate.now();
		String strDate=ld.toString();
		vo.setB_date(strDate);
		mainDao.update(vo);
		System.out.println(">> 수정이 완료되었습니다.");
		
	}

	private void insertBBs() {
		// TODO Auto-generated method stub
		System.out.println(">> 작성자를 입력하세요");
		System.out.print(">> ");
		String strAuth=sc.nextLine();
		System.out.println(">> 제목을 입력하세요");
		System.out.print(">> ");
		String strSubject=sc.nextLine();
		System.out.println(">> 내용을 입력하세요");
		System.out.print(">> ");
		String strText=sc.nextLine();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
		Date today=new Date();
		String strDate=sf.format(today);
		LocalDate ld=LocalDate.now();
		strDate=ld.toString();
		BBsVO vo=new BBsVO();
		vo.setB_date(strDate);
		vo.setB_auth(strAuth);
		vo.setB_subject(strSubject);
		vo.setB_text(strText);
		mainDao.insert(vo);
		}

	public void viewBBsList() {
		bbsMainList=mainDao.selectAll();
		System.out.println("==============================================");
		System.out.println("게시판 v1.1");
		System.out.println("==============================================");
		System.out.println("no\t작성일자\t작성자\t제목");
		System.out.println("----------------------------------------------");
		if(bbsMainList==null) {
			System.out.println(">> 데이터가 없습니다.");
		} else {
			for(BBsVO v:bbsMainList) {
			System.out.print(v.getB_id()+"\t");
			System.out.print(v.getB_date()+"\t");
			System.out.print(v.getB_auth()+"\t");
			System.out.print(v.getB_subject()+"\n");
			}
		}
	}
	
	public void viewBBsText() {
		
		System.out.println(">> 내용을 확인할 번호 ");
		System.out.print(">> ");
		String strID=sc.nextLine();
		if(strID.equals("")) {
			System.out.println(">> 삭제가 취소되었습니다.");
			return;
		}
		long longID=Long.valueOf(strID);
		BBsVO vo=mainDao.fintByID(longID);
		System.out.println("----------------------------------------------");
		System.out.println(">> 작성일자 : "+vo.getB_date());
		System.out.println(">> 작성자   : "+vo.getB_auth());
		System.out.println(">> 제목     : "+vo.getB_subject());
		System.out.println(">> 내용     : "+vo.getB_text());
		
	} 
}























package room.model.dao;

import java.util.ArrayList;

import room.controll.rboard.rewrite;
import room.model.dto.USERDTO;

public class userDao extends Dao{
	
	private static userDao udao = new userDao();
	public static userDao getInstance() {return udao;}
	
	//1.회원가입
	public boolean signup( USERDTO dto ) {
		String sql = " insert into room(mname,mid,mpw,mphone) values (?,?,?,?) ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getMname());
			ps.setString(2, dto.getMid());
			ps.setString(3, dto.getMpw());
			ps.setString(4, dto.getMphone());
			ps.executeUpdate();
			System.out.println("다오"+dto);
			return true;
		} catch (Exception e) {System.out.println("signup 오류"+e);}
			return false;
	}
	//2.아이디 중복체크
	public boolean idcheck(String mid) {
		String sql = "select * from room where mid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) {return true;}
		} catch (Exception e) {System.out.println("아이디중복체크 오류"+ e);}
		return false;
	}
	//3.로그인
	public int login(String mid , String mpw) {
		String sql = "select * from room where mid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) {
				sql = "select * from room where mid = ? and mpw= ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, mid);
				ps.setString(2, mpw);
				rs = ps.executeQuery();
				if(rs.next()) {
					return 1;	//성공
				} return 2;	//비번틀림
			}
		} catch (Exception e) {System.out.println("로그인"+e);}
		return 0;	//아이디없음
	}
	//4.아이디 찾기
	public String findID(String mname , String mphone) {
		String sql = " select * from room where mname = ? and mphone= ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mname);
			ps.setString(2, mphone);
			rs = ps.executeQuery();
			if(rs.next()) 
			return rs.getString(3);
		} catch (Exception e) {System.out.println("아이디찾기" + e);}
		return null;
	}
	//5.비번찾기[임시비번 발급]
	public boolean findPW(String mid , String mphone) {
		String sql = " select * from room where mid = ? and mphone= ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mphone);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {System.out.println("임시비번"+e);}
		return false;
	}
	//6.임시비번 업뎃
	public boolean PWchage(String mid , String ranstr) {
		String sql = " update room set mpw = ? where mid = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ranstr);
			ps.setString(2, mid);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {System.out.println("임시비번업뎃"+e);}
		return false;
	}
	
	//7.회원아이디->회원번호고은시[10/24]
	public int getMno(String mid) {
		String sql = "select * from room where mid= ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (Exception e) {System.out.println("회원번호 오류"+e);}
			return 0;
	}
	
	//8.유저출력 리스트
	public ArrayList<USERDTO> userlist() {	
		ArrayList<USERDTO> list = new ArrayList<>();	
		String sql= "select * from room";
		
		try {
			ps= con.prepareStatement(sql);
			rs =ps.executeQuery();
			while(rs.next()) {
				USERDTO dto = new USERDTO(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(dto);
			}
			return list;
			
		} 
		catch (Exception e) {System.out.println("연결안대"+e);} return list;
		
	}
	

	public boolean delete(int mno) {
		String sql = "delete from room "
				+ " where mno="+mno;
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if (count == 1) return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
}

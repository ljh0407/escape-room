package room.model.dao;

import java.util.ArrayList;

import javax.swing.text.html.parser.DTD;

import room.model.dto.NDTO;



public class ndao extends Dao{

	private static ndao ndao = new ndao();
	public static ndao getInstance() {return ndao;}
	
	
	// 글 입력
		public boolean nwirte(String ntitle , String ncontent  , int mno) {
		String sql = "insert into notice( ntitle , ncontent ,  mno) values(?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, ntitle);
			ps.setString(2, ncontent);
			ps.setInt(3, mno);
			ps.executeUpdate(); 
			return true;
		} catch (Exception e) {System.out.println(e);}
			return false;
		}
	
	//글 출력
	// 글 출력한뒤 글 페이지 하기위에 코드가 좀 바뀜 -주혁- 11 03
	public ArrayList<NDTO> getlist( int startrow, int listsize ,String key, String keyword){
		ArrayList<NDTO> list = new ArrayList<>();
		
		String sql = "";
		if( !key.equals("") && !keyword.equals("") ) { // 검색이 있을경우 
			sql = "select n.* , r.mid "
					+ " from room r , notice n "
					+ " where r.mno = n.mno and "+key+" like '%"+keyword+"%' "
					+ " order by n.ndate desc "
					+ " limit "+startrow+" , "+listsize;
		}else { // 검색이 없을경우
			sql = "select n.* , r.mid from room r , notice n "
					+ " where r.mno = n.mno "
					+ " order by n.ndate desc limit "+startrow+" , "+listsize;	
		}	
		try {
			ps= con.prepareStatement(sql);
			rs= ps.executeQuery();
			while(rs.next()) {
			NDTO dto = new NDTO(
					rs.getInt(1), rs.getString(2),
					rs.getString(3) , rs.getString(4),
					rs.getInt(5),rs.getInt(6),
					rs.getString(7));
				list.add(dto);

			}
			return list;
		} catch (Exception e) {System.out.println("출력되나"+e);}
		return null;
	}
	
	//3. 상세페이지 11/01 주혁
	public NDTO getboard(int nno) {
		String sql ="select n.* , r.mid from room r , notice n where r.mno = n.mno and nno = "+nno;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				NDTO dto = new NDTO(
				rs.getInt(1), rs.getString(2),
				rs.getString(3) , rs.getString(4),
				rs.getInt(5),rs.getInt(6),
				rs.getString(7));
				System.out.println("연결"+dto);
				return dto;
			}
		} catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	//4. 글삭제
	
	public boolean delete(int nno) {
		String sql = "delete from notice"
				+ " where nno="+nno;
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if (count == 1) return true;
		} catch (Exception e) {
			System.out.println(e);
		}return false;
	}

	
	// 첨부파일이 없음
	
	//5. 게시물 수정
	public boolean nupdate( int nno , String ntitle , String ncontent) {
	
		String sql = "update notice set ntitle = ? ,"
				+ " ncontent = ? where nno =?";
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(3, nno);
			ps.setString(1, ntitle);
			ps.setString(2, ncontent);
			ps.executeUpdate(); return true;
		} catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	//6. 조회수 증가
	public void nviewupdate( int nno) {
		String sql = " update notice "
				+ " set nview = nview+1 "
				+ " where nno = "+nno;
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {System.out.println(e);}
				
	}
	
	//7. 페이지( 전체게시물수)
	public int gettotalsize(String key , String keyword) {
		String sql = "";
		// 검색이 있을경우
		if( !key.equals("") && !keyword.equals("") ) { // 검색이 있을경우
			 sql = "select count(*) from room r , notice n where r.mno = n.mno and "+key+" like '%"+keyword+"%'";
		}else { // 검색이 없을경우 
			 sql = "select count(*) from room r , notice n where r.mno = n.mno";
		}
		 try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
		} catch (Exception e) {System.out.println(e);} return 0;
	}
	
	
	}





















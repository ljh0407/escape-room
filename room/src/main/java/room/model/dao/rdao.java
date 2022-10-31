package room.model.dao;

import java.util.ArrayList;

import room.model.dto.RDTO;

public class rdao extends Dao{

	private static rdao rdao = new rdao();
	public static rdao getInstance() {return rdao;}

	
	
	  // 글등록
	  public boolean setrwrite( String rtitle , String rcontent , String rfile , int mno ) {
	      String sql = "insert into rboard( rtitle , rcontent , rfile , mno ) "
	            + " values( ? , ? , ? , ? )";
	      try {
	         ps = con.prepareStatement(sql);
	         ps.setString( 1 , rtitle );
	         ps.setString( 2 , rcontent );
	         ps.setString( 3 , rfile );
	         ps.setInt(4, mno);
	         ps.executeUpdate(); return true;
	      }catch (Exception e) { System.out.println(e); } return false;
	   }
	  
	  
	  // 글 리스트
	  public ArrayList<RDTO> getrlist (RDTO dto){
	      ArrayList<RDTO> list = new ArrayList<>();
	      String sql = "select * from rboard";

	      try {
	         ps = con.prepareStatement(sql);
	         rs = ps.executeQuery();
	         while( rs.next() ) {
	            RDTO dto = new RDTO(
	                  rs.getInt(1),    rs.getString(2),
	                  rs.getString(3), rs.getInt(4),
	                  rs.getString(5), rs.getInt(6), 
	                  rs.getString(7)  rs.getInt(8),
	                  rs.getInt(9) ) ;
	            	list.add(dto);
	         }
	      }catch (Exception e) {System.out.println(e);}
	      return list;

	  
	  
	  
}

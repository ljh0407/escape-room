package room.controll.qboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import room.model.dao.qDao;
import room.model.dto.QDTO;

/**
 * Servlet implementation class qview
 */
@WebServlet("/qview")
public class qview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public qview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//고은시 새 서블릿(기존 서블릿 삭제)[10/28] 개별 글 조회
		int bno = (Integer) request.getSession().getAttribute("bno") ;
		System.out.println("ddd : " + bno );
		//다오 처리
		QDTO qdto = qDao.getIncetance().getqboard(bno);
		
		//상세게시물 호출[11/01]호출수정
		JSONObject object = new JSONObject();
		object.put("bno", qdto.getBno());
		object.put("btitle", qdto.getBtitle());
		object.put("bcontent", qdto.getBcontent());
		object.put("bfile", qdto.getBfile());
		object.put("bdate", qdto.getBdate());
		object.put("reply", qdto.getReply());
		object.put("bview", qdto.getBview());
		object.put("mno", qdto.getMno());
		object.put("mid", qdto.getMid());
		
		//삭제버튼활성화 고은시[11/01]
		String mid = (String)request.getSession().getAttribute("mid");
		if(mid != null && mid.equals(qdto.getMid())) {
			object.put("btnaction", true);
		}
		response.setCharacterEncoding(mid);
		object.put("button", true);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(object);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//고은시 상세게시물 세션에 저장[10/28]
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		//세션객체
		HttpSession session = request.getSession();
		//클릭한 게시물 번호 세션저장
		session.setAttribute("bno", bno);
		//로그인한 아이디
		String mid = (String)session.getAttribute("mid");
	}

}


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import customTools.DbBullhorn;
import model.Bhpost;
import model.Bhuser;
import oracle.sql.DATE;
import java.util.Date;
import java.util.List;

/**
 * Servlet Newsfeed.java
 */

@WebServlet("/Newsfeed")
public class Newsfeed extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Newsfeed() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get the search text and the current user id from the session
		String searchText = request.getParameter("searchtext");
		String userid = request.getParameter("userid");
		
		if(searchText != null) {
			System.out.println("search text is: " + searchText);
		} else {
			System.out.println("search text is : null");
		}
		List<Bhpost> posts = null;

		if (userid != null) {
			posts = DbBullhorn.postsofUser(Long.parseLong(userid));
		} else if(searchText == null || searchText.trim().length()<=0) {
			posts = DbBullhorn.bhPost();
		} else {
			posts = DbBullhorn.searchPosts(searchText);
		}
		
				
		HttpSession session = request.getSession();
		session.setAttribute("posts", posts);
		
		//redirect to newsfeed page		
		String nextURL = "/newsfeed.jsp";	
		request.getRequestDispatcher(nextURL).forward(request,response);
		
		//response.sendRedirect(request.getContextPath() + nextURL);
		return;		

	}

}

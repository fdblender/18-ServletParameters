
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbBullhorn;
import model.Bhpost;

/**
 * Servlet implementation class Home
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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

		// get the current user from the session
		String userid = request.getParameter("userid");

		List<Bhpost> posts = null;

		if (userid != null) {
			// get the posts for this user
			posts = DbBullhorn.postsofUser(Long.parseLong(userid));
		}

		// save the posts for this user in the session attributes
		HttpSession session = request.getSession();
		session.setAttribute("posts", posts);

		// redirect to home page
		String nextURL = "/home.jsp";
		request.getRequestDispatcher(nextURL).forward(request, response);

		return;

	}

}

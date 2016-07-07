

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbBullhorn;
import model.Bhpost;
import model.Bhuser;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String posttext = request.getParameter("posttext");		
		HttpSession session = request.getSession();
		Bhpost post = new Bhpost();
		Bhuser user = (Bhuser) session.getAttribute("user");	
		
		if (session.getAttribute("user")==null) {
			String nextURL = "/login.jsp";
			session.invalidate();
			response.sendRedirect(request.getContextPath() + nextURL);;
			return;
		}
		
		if(posttext != null) {
			
			// create a new post with the posttext
			System.out.println("Post text is: " + posttext);			
			
			// set the post date
			Date date = new Date();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = fmt.parse("2015-07-07");
			} catch (Exception e) {
				
			}
			post.setPostdate(date);			
			post.setPosttext(posttext);
			post.setBhuser(user);
			
			// insert the new post into the database
			DbBullhorn.insert(post);
			
		} else {
			System.out.println("Post text is : null");
		}	
		
		// redirect to home page
		String nextURL = "/home.jsp";
		request.getRequestDispatcher(nextURL).forward(request, response);
		
		return;		
	}

}


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
 * Servlet implementation class ProcessFormList<Bhpost> posts = null;

posts = DbBullhorn.bhPost();
 */
@WebServlet("/ProcessForm")
public class ProcessForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * helper method to populate a table of Bhusers
	 */
	protected String writeUserTable() {
		ArrayList<Bhuser> users = new ArrayList<Bhuser>();
		
		Bhuser user = new Bhuser();	
		user.setBhuserid(1);			
		user.setUsername("Fran");
		user.setUseremail("fran@domain.com");
		Date date = new Date();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = fmt.parse("2015-02-27");
		} catch (Exception e) {
			
		}
		user.setJoindate(date);	
		user.setMotto("Keep on learning");
		users.add(user);
		
		user = new Bhuser();	
		user.setBhuserid(2);			
		user.setUsername("Bob");
		user.setUseremail("bob@domain.com");
		user.setJoindate(date);	
		user.setMotto("Be curious");
		users.add(user);
		
		user = new Bhuser();	
		user.setBhuserid(3);			
		user.setUsername("Susan");
		user.setUseremail("susan@domain.com");
		user.setJoindate(date);	
		user.setMotto("Mentor others");
		users.add(user);
		
		StringBuilder table = UserUtil.getUserTable(users);		
		return table.toString();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 
		
		boolean invalid = true;

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		response.setContentType("text/html");
		String nextURL = "/error.jsp";	
		
		// create a session  and a message variable to pass data to each web page
		HttpSession session = request.getSession();
		String message;		

		//PrintWriter out = response.getWriter();
		//message = "Your email is " + email + " your password is  " + password;
		//out.println("<h1>" + message + "</h1>");		

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try {
			// model.Bhuser user = em.find(model.Bhuser.class, (long)2);

			if (UserUtil.isValidUser(email, password)) {
				invalid = false;				
				model.Bhuser user = UserUtil.getUserByEmail(email);
				message = "Valid User: " + user.getUsername();
				System.out.println("<h2>" + message + "</h2>");
				
				// create or update the session 
				session = request.getSession();
				session.setAttribute("user", user);
				
				if (session.getAttribute("user")==null) {
					message = "Null user: " + email + " " + password;
					System.out.println("<h2>" + message + "</h2>");
					
					//session.invalidate();
					
					//redirect to login page
					nextURL = "/login.html";					
					response.sendRedirect(request.getContextPath() + nextURL);
					return;
					
				} else {
					
					//session.invalidate();
					String messages = user.getUsername();
					String emailaddr = user.getUseremail();
					
					request.setAttribute("messages", messages);
					request.setAttribute("emailaddr", emailaddr);
					request.setAttribute("userlist", writeUserTable());
					
					// read in the list of posts from the database
					List<Bhpost> posts = null;
					posts = DbBullhorn.bhPost();
					String posttable = UserUtil.getPostTable(posts).toString();					
					request.setAttribute("posttable", posttable);
					
					//redirect to home page						
					nextURL = "/home.jsp";	
					request.getRequestDispatcher(nextURL).forward(request,response);
					
					//response.sendRedirect(request.getContextPath() + nextURL);
					return;
				}

			} else {
				message = "Invalid User/password: " + email + " " + password;
				System.out.println("<h2>" + message + "</h2>");			
				
				//session.invalidate();
				
				//redirect to login page				
				nextURL = "/login.html";
				//String messages = "Error: user not found";
				//request.setAttribute("messages", messages);
				//request.getRequestDispatcher(nextURL).forward(request,response);
				response.sendRedirect(request.getContextPath() + nextURL);
				return;
			}

		} catch (Exception e) {
			System.out.println(e);

		} finally {
			em.close();
			System.out.println("closed");
		}

	}

}

/*
 * the login servlet processes login.jsp. The servlet has one job
 * which is to validate the user and add them to the session so
 * that user will be available to all pages. If the user is not valid
 * then the login servlet will redirect back to the login page.
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbBullhorn;
import customTools.DbUser;
//import customTools.DBUtil;
import model.Bhuser;
import model.Bhpost;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //this page does not require user to be logged in
        String useremail = request.getParameter("email");
        String userpassword = request.getParameter("password");
       // String action = request.getParameter("action");
        //String remember = request.getParameter("remember");
        String action = request.getParameter("submit");
        String nextURL = "/error.jsp";
        
        //get an instance of the session so we can set attributes to it
        //the JSP and NavBar will read from the session 
        //The session is one of the primary ways we maintain state
        //in an otherwise stateless web application
        HttpSession session = request.getSession();
        
        //create a variable to hold our user. The variable must be defined
        //so it is visible to all code within the method. So define it here
        //but set it to null since we don't have a user yet.
        Bhuser user = null;
        //create an instance of the user and put it in the session
        //only add the user to the session if the user if valid.
        //The presence of the user is used to determine who 
        //owns the site and will be used to connect to the database
        if (action.equals("logout")){
            session.invalidate();
            nextURL = "/BHlogin.jsp";
            
        }else{
            user = DbUser.getUserByEmail(useremail);
            if (DbUser.isValidUser(useremail, userpassword)){
            	System.out.println("found valid user"+useremail+" "+userpassword);
                session.setAttribute("user", user);
                int size = 30;
                String gravatarURL = DbUser.getGravatarURL(useremail, size);
                System.out.println(gravatarURL);
                session.setAttribute("gravatarURL", gravatarURL);
                
                List<Bhpost> posts = DbBullhorn.bhPost();
                session.setAttribute("posts", posts);
                
                nextURL = "/newsfeed.jsp";
            }else{
                nextURL = "/BHlogin.jsp";
            }
            
        }
        //redirect to next page as indicated by the value of the nextURL variable
        //getServletContext().getRequestDispatcher(nextURL).forward(request,response);
        response.sendRedirect(request.getContextPath() + nextURL);
    }

}
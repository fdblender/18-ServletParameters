package customTools;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import customTools.DBUtil;
import model.Bhpost;
import model.Bhuser;

public class DbUser {

	// returns a string containing the HTML to generate a table of posts
	public static StringBuilder getPostTable(List<Bhpost> posts) {

		StringBuilder table = new StringBuilder();
		long postid;
		Date postdate;
		String posttext;
		long userid;
		

		table.append("<table>");
		table.append("<tr><th>User</th><th>Post</th><th>Date</th></tr>");
		
		for (Bhpost post : posts) {

			table.append("<tr><td>" + post.getBhuser().getUseremail() + "</td><td>" 
			+ post.getPosttext()+" " + "</td><td>" 
			+ post.getPostdate() + "</td></tr>");
		}
		table.append("</table>");
		return table;

	}
	
	// returns a string containing the HTML to generate a table of users
	public static StringBuilder getUserTable(ArrayList<Bhuser> users) {

		StringBuilder table = new StringBuilder();
		String email, username;

		table.append("<table>");
		table.append("<tr><th>User ID</th><th>User Name</th><th>Email</th><th>Join Date</th><th>Motto</th></tr>");
		
		for (Bhuser user : users) {

			table.append("<tr><td>" + user.getBhuserid() + "</td><td>" + user.getUsername() + "</td><td>" + user.getUseremail() + "</td><td>"
					+ user.getJoindate() + "</td><td>" + user.getMotto() + "</td></tr>");
		}
		table.append("</table>");
		return table;

	}

	public static Bhuser getUserByEmail(String email) {

		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Bhuser u " + "where u.useremail = :useremail";

		TypedQuery<Bhuser> q = em.createQuery(qString, Bhuser.class);
		q.setParameter("useremail", email);
		Bhuser user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return user;
	}

	public static boolean isValidUser(String email, String password) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();

		// create the query and set the parameters
		String qString = "Select count(b.bhuserid) from Bhuser b " + "where b.useremail = :email "
				+ " and b.userpassword = :password";
		TypedQuery<Long> q = em.createQuery(qString, Long.class);
		q.setParameter("email", email);
		q.setParameter("password", password);

		boolean result = false;

		try {
			long userId = q.getSingleResult();
			if (userId > 0) {
				result = true;
			}
		} catch (Exception e) {
			result = false;
		} finally {
			em.close();
		}

		return result;
	}
	
	public static String getGravatarURL(String useremail, int size) {
		return "tempURL";
	}

}

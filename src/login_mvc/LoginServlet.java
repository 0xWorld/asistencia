package login_mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login_mvc.LoginBean;
import login_mvc.LoginDao;

import clase_mvc.ClaseBean;
import clase_mvc.ClaseDao;

import clase_mvc.AlumnoBean;
import clase_mvc.AlumnoDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private ClaseDao claseDao;
    private AlumnoDao alumnoDao;
    public void init() {
    	loginDao = new LoginDao();
    	claseDao = new ClaseDao();    	
    	alumnoDao = new AlumnoDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dd=request.getRequestDispatcher("login.jsp");		
		dd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		//doGet(request, response);
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String page="login";
		LoginBean loginBean = new LoginBean();
		loginBean.setEmail(email);
		loginBean.setPassword(password);
		
		try {
			if(loginDao.validate(loginBean)) {
				page ="clase";
				
				HttpSession session= request.getSession();
				session.setAttribute("email",email);
				session.setAttribute("password", password);		
				LoginBean user = loginDao.getUser(request);
				session.setAttribute("nombre", user.getNombre());								
			}else {				
				//HttpSession session= request.getSession();
				//session.setAttribute("email", email);
				//response.sendRedirect("login.jsp");
			}			
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		response.sendRedirect(page);		
	}

}

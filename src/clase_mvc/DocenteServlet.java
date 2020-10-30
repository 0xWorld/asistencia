package clase_mvc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login_mvc.LoginBean;
import login_mvc.LoginDao;

/**
 * Servlet implementation class DocenteServlet
 */
@WebServlet("/docente")
public class DocenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDao loginDao;
    private ClaseDao claseDao;    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	loginDao = new LoginDao();
    	claseDao = new ClaseDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			String[] alumno = request.getParameterValues("cbalumno");
			HttpSession session= request.getSession();
			LoginBean user = loginDao.getUser(request);
			ClaseBean clase = claseDao.getClase(user);
			claseDao.SaveSalida(clase);
		} catch (ClassNotFoundException e){
			//e.printStackTrace();
		}
		
		response.sendRedirect("clase");
	}

}

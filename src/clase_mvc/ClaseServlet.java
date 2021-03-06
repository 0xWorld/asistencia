package clase_mvc;

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

/**
 * Servlet implementation class ClaseServlet
 */
@WebServlet("/clase")
public class ClaseServlet extends HttpServlet {
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
    public ClaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		try {
			HttpSession session= request.getSession();			
			LoginBean user = loginDao.getUser(request);
			ClaseBean clase = claseDao.getClase(user);
			request.setAttribute("clase", clase);
			request.setAttribute("alumnos", alumnoDao.getAlumnos(clase));
		} catch (ClassNotFoundException e){
			//e.printStackTrace();
		}		
		RequestDispatcher dd=request.getRequestDispatcher("home.jsp");
		dd.forward(request, response);
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
				alumnoDao.SaveAsistenciaAlumno(clase,alumno);
			} catch (ClassNotFoundException e){
				//e.printStackTrace();
			}

		doGet(request, response);
	}
}
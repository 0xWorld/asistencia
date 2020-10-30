package login_mvc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import login_mvc.LoginBean;

public class LoginDao {
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException{
		boolean status = false;
		Class.forName("com.mysql.jdbc.Driver");
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement("select * from usuario  where email = ? and password = ? ")){
				preparedStatement.setString(1,loginBean.getEmail());
				preparedStatement.setString(2,loginBean.getPassword());
				//System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				status = rs.next();				
				
		} catch(SQLException e) {
			printSQLException(e);
		}
		return status;
	}
	
	public LoginBean getUser(HttpServletRequest request) throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		LoginBean loginBean = new LoginBean();
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
		PreparedStatement preparedStatement = connection.prepareStatement("select * from usuario  where email = ? and password = ? ")){
			preparedStatement.setString(1,request.getSession().getAttribute("email")+"");
			preparedStatement.setString(2,request.getSession().getAttribute("password")+"");
			ResultSet rs = preparedStatement.executeQuery();			
			while (rs.next()) {
				loginBean.setNombre(rs.getString("NOMBRE"));
				loginBean.setId(rs.getInt("id"));
			}
		} catch(SQLException e) {
			printSQLException(e);
		}
		return loginBean;
	}
	
	
	
	private void printSQLException(SQLException ex) {
		for(Throwable e: ex) {
			if(e instanceof SQLException) {
				System.err.println("SQLState: "+((SQLException) e).getSQLState());
				System.err.println("SQLError: "+((SQLException) e).getErrorCode());
				System.err.println("SQLMessage: "+((SQLException) e).getMessage());
				Throwable t = ex.getCause();
				while(t != null) {
					System.out.println("Cause: "+t);
					t = t.getCause();
				}
			}
		}
	}
}

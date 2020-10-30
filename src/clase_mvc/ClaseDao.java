package clase_mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login_mvc.LoginBean;

public class ClaseDao {
	public ClaseBean getClase(LoginBean user) throws ClassNotFoundException{		
		ClaseBean claseBean = new ClaseBean();		
		Class.forName("com.mysql.jdbc.Driver");
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT cla.idcla,idcur,DATE_FORMAT(horini,'%d/%m/%y %T') as horini,DATE_FORMAT(horfin,'%d/%m/%y %T') as horfin,iddoc,idcurso,nomcur FROM clase cla JOIN clase_docente doc ON cla.idcla=doc.idcla JOIN cursos cur ON cur.IDCURSO=cla.idcur WHERE iddoc=? AND SYSDATE() between horini AND horfin")){
				preparedStatement.setInt(1,user.getId());
				//System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					claseBean.setIdcla(rs.getInt("idcla"));
					claseBean.setIdcur(rs.getInt("idcur"));
					claseBean.setNomcur(rs.getNString("nomcur"));
					claseBean.setIddoc(rs.getInt("iddoc"));
					claseBean.setHorini(rs.getNString("horini")+"");
					claseBean.setHorfin(rs.getNString("horini")+"");
				}
				SaveAsistenciaDocente(claseBean);
		} catch(SQLException e) {
			printSQLException(e);
		}
		return claseBean;
	}
	
	public void SaveAsistenciaDocente(ClaseBean claseBean) {						
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM asistencia_docente WHERE idcla=? AND iddoc=?")){
			preparedStatement.setInt(1,claseBean.getIdcla());
			preparedStatement.setInt(2,claseBean.getIddoc());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if(!rs.next()) {
				PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO asistencia_docente VALUES (?,?,SYSDATE(),NULL) ");
				preparedStatement1.setInt(1,claseBean.getIdcla());
				preparedStatement1.setInt(2,claseBean.getIddoc());
				preparedStatement1.executeUpdate();
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
	}
	
	public void SaveSalida(ClaseBean claseBean) {
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE asistencia_docente set fecsal = sysdate() where idcla = ? and iddoc = ?")){
			preparedStatement.setInt(1,claseBean.getIdcla());
			preparedStatement.setInt(2,claseBean.getIddoc());			
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}		
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

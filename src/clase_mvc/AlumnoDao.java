package clase_mvc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlumnoDao {
	
	public List<AlumnoBean> getAlumnos(ClaseBean clase) throws ClassNotFoundException{
		List<AlumnoBean> alumnos = new ArrayList<AlumnoBean>();
		Class.forName("com.mysql.jdbc.Driver");
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = 
				connection.prepareStatement("SELECT mat.codalu,alu.nomalu,case when asi.IDCLA IS NULL then 0 ELSE 1 END asistio FROM matricula mat JOIN alumnos alu ON mat.codalu=alu.codalu LEFT JOIN asistencia_alumno asi ON asi.CODALU=mat.codalu AND asi.IDCLA=? WHERE idcur = ?")){				
				preparedStatement.setInt(1,clase.getIdcla());
				preparedStatement.setInt(2,clase.getIdcur());
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					alumnos.add(new AlumnoBean(rs.getString("codalu"),rs.getString("nomalu"),rs.getBoolean("asistio")));									
				}
		} catch(SQLException e) {
			printSQLException(e);
		}
		return alumnos;
	}
	
	public void SaveAsistenciaAlumno(ClaseBean clase,String[] alumnos) {
		try(	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/asistencia?useSSL=false", "root", "");
				PreparedStatement preparedStatement = connection.prepareStatement("delete from asistencia_alumno where idcla= ? ")){
				preparedStatement.setInt(1,clase.getIdcla());
				preparedStatement.executeUpdate();
				if(alumnos != null) {
					for(int i=0;i<alumnos.length;i++) {
						PreparedStatement preparedStatement1 = connection.prepareStatement("insert into asistencia_alumno values (?,?) ");
						preparedStatement1.setString(1,alumnos[i]);
						preparedStatement1.setInt(2,clase.getIdcla());						
						preparedStatement1.executeUpdate();
					}
				}				
		} catch(SQLException e) {
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
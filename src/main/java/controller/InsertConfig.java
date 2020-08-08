package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/insert" })
public class InsertConfig extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		
		
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String fileFormat = request.getParameter("fileFormat");
//		String remoteDir = request.getParameter("remoteDir");
//		String localDir = request.getParameter("localDir");
		
		
		// Nhập nội dung Config
		 String name_Config= request.getParameter("nameconfig");
		 String hostname = request.getParameter("hostname");
		 int port = Integer.parseInt(request.getParameter("port"));
		 String userNameAccount= request.getParameter("username");
		 String passwordAccount= request.getParameter("password");
		 String file_Format= request.getParameter("fileformat") ;
		 String remote_Dir=  request.getParameter("remotedir");
		 String local_Dir=  request.getParameter("localdir");
		
		
		 String name_db_Control=  request.getParameter("namedbcontrol");
		 String name_table_config=  request.getParameter("nametbconfig");
		 String name_table_log=  request.getParameter("nametblog");
		
		 String name_db_Staging=  request.getParameter("namedbstaging");
		 String name_table_staging=  request.getParameter("nametbstaging");
		
		 String name_db_Warehouse=  request.getParameter("namedbwarehouse");
		 String name_table_warehouse=  request.getParameter("nametbwarehouse");
		
		//Kết nối đến database Control
		Connection conn = ConnectDB.connect("control", "root", "");
		String sqlInsertString = null;
		PreparedStatement pre = null;
		
		
		
		//Thêm dữ liệu vừa nhập vào bảng "config" 
		try {
			//String sqlupdate = "UPDATE config SET hostname = ?, port = ?, username_account = ?, password_account = ?, file_format = ?, remote_dir = ?, local_dir = ? WHERE name_config = ?";
			
			sqlInsertString = "Insert into config (name_config, hostname, port, username_account, password_account, file_format, remote_dir, local_dir, name_dbControl, name_tbConfig, name_tbLog, name_dbStaging, name_tbStaging,\r\n" + 
					"name_dbWarehouse, name_tbWarehouse) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			pre = conn.prepareStatement(sqlInsertString);
			
			pre.setString(1, name_Config);
			pre.setString(2, hostname);
			pre.setInt(3, port);
			pre.setString(4, userNameAccount);
			pre.setString(5, passwordAccount);
			pre.setString(6, file_Format);
			pre.setString(7, remote_Dir);
			pre.setString(8, local_Dir );
			pre.setString(9, name_db_Control);
			pre.setString(10, name_table_config);
			pre.setString(11, name_table_log);
			pre.setString(12, name_db_Staging);
			pre.setString(13, name_table_staging);
			pre.setString(14, name_db_Warehouse);
			pre.setString(15, name_table_warehouse);
			pre.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		//Load dòng dữ liệu vừa được thêm vào
		String sql_getLastRow = "SELECT * FROM config ORDER BY id_config DESC LIMIT 1";
		int idOfConfig = 0;
		try {
			pre = conn.prepareStatement(sql_getLastRow);
			ResultSet rs = pre.executeQuery();
			
			while(rs.next()) {
				idOfConfig = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			//Lấy "id" của config để ghi vào file bath
			 FileWriter myWriter = new FileWriter("C:\\Users\\WIN10\\Desktop\\runDown.bat");
		      myWriter.write("java -jar datawarehouse.jar " + idOfConfig);
		      myWriter.close();
			
		      //Gọi file bath để chạy file jar
            String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\WIN10\\Desktop\\runDown.bat"};
            Runtime.getRuntime().exec(command);           
        } catch (IOException ex) {
        }
		
		//Chuyển đến màn hình xem Log
		response.sendRedirect(request.getContextPath() + "/viewLog");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	

	
}
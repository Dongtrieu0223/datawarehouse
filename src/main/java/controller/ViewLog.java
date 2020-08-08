
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Log;

@WebServlet(urlPatterns = { "/viewLog" })
public class ViewLog extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		Connection conn = ConnectDB.connect("control", "root", "");
		String sql = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		
		try {
			sql = "SELECT * from log";
			pre = conn.prepareStatement(sql);
			rs = pre.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		ArrayList<Log> logs = new ArrayList<Log>();
		try {
			while (rs.next()) {
				Log l = new Log();
				l.setId(rs.getInt(1));
				l.setNameLog(rs.getString(2));
				l.setUrlLocal(rs.getString(3));
				l.setStatus(rs.getString(4));
				l.setComment(rs.getString(5));
				
				
				l.setTime_download(rs.getString(6));
				l.setTime_uploadStraging(rs.getString(7));
				l.setTime_uploadWarehouse(rs.getString(8));
				
				
				logs.add(l);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		request.setAttribute("logs", logs);
		RequestDispatcher rd = request.getRequestDispatcher("/views/view-log.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public static void main(String[] args) {
		
	}
	
}

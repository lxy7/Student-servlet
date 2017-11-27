package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.apache.jasper.tagplugins.jstl.core.Out;

import Bean.Student;
import Bean.User;
import UserDao.StudentMothod;
import UserDao.UserDao;

public class dologin extends HttpServlet {

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		System.out.println(action);
		
		if(action.equals("login"))
		{
			login (req,resp);
		}
		else if(action.equals("add"))
		{
			add(req,resp);
		}
		else if(action.equals("update"))
		{
			update(req,resp);
		}
		else if(action.equals("delete"))
		{
		   delete(req, resp);
		}
		
	} 
	public void login(HttpServletRequest request,HttpServletResponse response)
	{
		
		
		
		String name = request.getParameter("name");
		
		String password = request.getParameter("password");
		PrintWriter out;
		UserDao usDao = new UserDao();
		ArrayList<User> uList = usDao.getUser();
		User us = new User();
		for(int i= 0;i<uList.size();i++)
		{
			if(uList.get(i).getName().equals(name)&&uList.get(i).getPassword().equals(password))
			{
				//session.setAttribute("user", request.getParameter("name") );
				
					//request.getRequestDispatcher("allStudent.jsp").forward(request, response);
				try {
					
					out=response.getWriter();
					//String a= new String("登录成功！".getBytes("ISO8859-1"),"utf-8");
					out.print("<script>alert('登录成功！'); location.href='allStudent.jsp';</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				break;
			}
			else if(i==uList.size()-1)
			{
				try {
					response.sendRedirect("loginfailure.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}


	}
	public void update(HttpServletRequest request,HttpServletResponse response)
	{
		Student us = new Student();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex").equals("1")?"男":"女";
		String grad = request.getParameter("grad");
		String address = request.getParameter("address");
		us.setId(id);
		us.setName(name);
		us.setAge(age);
		us.setGrad(grad);
		us.setSex(sex);
		us.setAddress(address);
		
	StudentMothod sm = new StudentMothod();
	boolean flag;
	PrintWriter out;
		System.out.println("ha");
	 
	try {
		System.out.println("ha");
		out = response.getWriter();
		flag = sm.update(us);
		if(flag)
		{
			System.out.println("haa");
			out.print("<script>alert('修改成功！'); location.href='allStudent.jsp';</script>");
			
		}
		else
		{
			System.out.println("haaa");
			out.print("<script>alert('修改失败！'); location.href='allStudent.jsp';</script>");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}
	public void add(HttpServletRequest request,HttpServletResponse response)
	{
		
		Student us = new Student();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex").equals("1")?"男":"女";
		String grad = request.getParameter("grad");
		String address = request.getParameter("address");
		us.setId(id);
		us.setName(name);
		us.setAge(age);
		us.setGrad(grad);
		us.setSex(sex);
		us.setAddress(address);
		
	StudentMothod sm = new StudentMothod();
	boolean flag;
	PrintWriter out;
		System.out.println("ha");
	 
	try {
		System.out.println("ha");
		out = response.getWriter();
		flag = sm.add(us);
		if(flag)
		{
			System.out.println("haa");
			out.print("<script>alert('新增成功！'); location.href='allStudent.jsp';</script>");
			
		}
		else
		{
			System.out.println("haaa");
			out.print("<script>alert('添加失败！'); location.href='allStudent.jsp';</script>");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	}
	public void delete(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("进删除啦！");
		String id = request.getParameter("id");
		StudentMothod sm = new StudentMothod();
		PrintWriter out;
		try {
			out = response.getWriter();
			int flag = sm.delete(id);
			if(flag!=0)
			{
				out.print("<script>alert('删除成功！'); location.href='allStudent.jsp';</script>");
				}
			else
			{
				out.print("<script> alert('删除失败！'+id); location.href='allStudent.jsp';</script>");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}

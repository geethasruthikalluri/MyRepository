package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CrudDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.ContactInfo;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	private CrudDao dao;

	public Controller() {
		dao = new CrudDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		List<ContactInfo> contactList = new ArrayList<ContactInfo>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("list")) {
					// Fetch Data from Student Table
					contactList = dao.getAllContacts();

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", contactList);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				} else if (action.equals("create") || action.equals("update")) {
					ContactInfo contactInfo = new ContactInfo();
					if (request.getParameter("firstName") != null) {
						// int studentId =
						// Integer.parseInt(request.getParameter("studentId"));
						String firstName = request.getParameter("firstName");
						contactInfo.setFirstName(firstName);
					}

					if (request.getParameter("lastName") != null) {
						String lastName = request.getParameter("lastName");
						contactInfo.setLastName(lastName);
					}

					if (request.getParameter("address1") != null) {
						String address1 = request.getParameter("address1");
						contactInfo.setAddress1(address1);
					}

					if (request.getParameter("address2") != null) {
						String address2 = request.getParameter("address2");
						contactInfo.setAddress2(address2);
					}
					if (request.getParameter("city") != null) {
						String city = request.getParameter("city");
						contactInfo.setCity(city);
					}
					if (request.getParameter("postalCode") != null) {
						int postalCode = Integer.parseInt(request.getParameter("postalCode"));
						contactInfo.setPostalCode(postalCode);
					}
					if (request.getParameter("mobile") != null) {
						int mobile = Integer.parseInt(request.getParameter("mobile"));
						contactInfo.setMobile(mobile);
					}
					if (request.getParameter("email") != null) {
						String email = request.getParameter("email");
						contactInfo.setEmail(email);
					}

					if (action.equals("create")) {
						// Create new record
						int seqId = dao.addContact(contactInfo);

						contactInfo.setId(seqId);

					} else if (action.equals("update")) {
						// Update existing record
						if (request.getParameter("Id") != null) {
							int id = Integer.parseInt(request.getParameter("Id"));
							contactInfo.setId(id);
						}
						dao.updateContact(contactInfo);
					}

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", contactInfo);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("Id") != null) {
						int id = Integer.parseInt(request.getParameter("Id"));
						dao.deleteStudent(id);

						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
					}
				}
			} catch (Exception ex) {
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
		}
	}
}
package by.haikou.bicycle_rental.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class CRUDController extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null) {
            action = action.trim().toLowerCase();
        }
        switch (action) {
            case "create":
                this.create(request, response);
                break;
            case "delete":
                this.delete(getIdParameter(request), request, response);
                break;
            case "update":
                this.update(getIdParameter(request), request, response);
                break;
            case "edit":
                this.edit(getIdParameter(request), request, response);
                break;
            case "list":
                this.list(request, response);
                break;
            case "add":
                this.add(request, response);
                break;
            default:
                list(request, response);
        }
    }

    abstract void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void delete(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void update(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    abstract void edit(Integer id, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected Integer getIdParameter(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    void forward(String forward, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}

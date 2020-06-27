package controllers.follower;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowerDestroyServlet
 */
@WebServlet("/follower/destroy")
public class FollowerDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowerDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DBに接続
        EntityManager em = DBUtil.createEntityManager();
        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        //follow_idを削除
        List<Follow> follow = em.createNamedQuery("getFollowDestroy", Follow.class)
                .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee"))
                .setParameter("follow_id", e)
                .getResultList();

        follow.forEach(em::remove);

        //DBの更新
        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();

        //indexページにリダイレクト
        response.sendRedirect(request.getContextPath() + "/follower/index");
    }

}

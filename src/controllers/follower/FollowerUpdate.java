package controllers.follower;

import java.io.IOException;
import java.sql.Timestamp;

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
 * Servlet implementation class FollowerUpdate
 */
@WebServlet("/follower/update")
public class FollowerUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowerUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //DBにアクセス
      EntityManager em = DBUtil.createEntityManager();

      //インスタンス作成
      Follow f = new Follow();
      Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

      //ログイン中の従業員のidを保存
      f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
      f.setFollow(e);
      Timestamp currentTime = new Timestamp(System.currentTimeMillis());
      f.setCreated_at(currentTime);
      f.setUpdated_at(currentTime);


      //DBの更新
      em.getTransaction().begin();
      em.persist(f);
      em.getTransaction().commit();
      em.close();

      //indexページにリダイレクト
      response.sendRedirect(request.getContextPath() + "/follower/index");
    }

}

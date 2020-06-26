package controllers.follower;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowerIndexServlet
 */
@WebServlet("/follower/index")
public class FollowerIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowerIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //DBへ接続
        EntityManager em = DBUtil.createEntityManager();

        //loginアカウントをセッションスコープへ登録
        Employee login_employee = (Employee)request.getSession().getAttribute("login_employee");

        // 開くページ数を取得（デフォルトは1ページ目）
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }

        // 最大件数と開始位置を指定してメッセージを取得
        List<Employee> employees = em.createNamedQuery("getAllEmployees", Employee.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        //follow_idを取得
        List<Follow> follows = em.createNamedQuery("getFollow_id", Follow.class)
                .setParameter("employee", login_employee)
                .getResultList();

        // 全件数を取得
        long employee_count = (long)em.createNamedQuery("getEmployeesCount", Long.class)
                .getSingleResult();

        em.close();

        request.setAttribute("login_employee", login_employee);
        request.setAttribute("follows", follows);
        request.setAttribute("employees", employees);
        request.setAttribute("employee_count", employee_count);
        request.setAttribute("page", page);                        // ページ数

        // フラッシュメッセージがセッションスコープにセットされていたら
        // リクエストスコープに保存する（セッションスコープからは削除）
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        //ビューとなるindex.jspを呼び出す
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follower/index.jsp");
        rd.forward(request, response);
    }

}

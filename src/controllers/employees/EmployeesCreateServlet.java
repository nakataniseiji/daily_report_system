package controllers.employees;

import java.io.IOException;
import java.sql.Timestamp;
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
import models.validators.EmployeeValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class EmployeesCreateServlet
 */
@WebServlet("/employees/create")
public class EmployeesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //RCSF対策
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            //DBに接続
            EntityManager em = DBUtil.createEntityManager();

            //インスタンス作成
            Employee e = new Employee();
            Follow f = new Follow();

            //formの内容をプロパティーに保存する
            e.setCode(request.getParameter("code"));
            e.setName(request.getParameter("name"));
            e.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            e.setAdmin_flag(Integer.parseInt(request.getParameter("admin_flag")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            e.setCreated_at(currentTime);
            e.setUpdated_at(currentTime);
            e.setDelete_flag(0);

            List<String> errors = EmployeeValidator.validate(e, true, true);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("employee", e);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(e);
                em.getTransaction().commit();
                em.close();

                //DBに接続
                EntityManager _em = DBUtil.createEntityManager();

                //作成した従業員データをemployee_idとfollow_idへ保存
                Employee e_id = _em.createNamedQuery("checkLoginCodeAndPassword", Employee.class)
                        .setParameter("code", request.getParameter("code"))
                        .setParameter("pass", EncryptUtil.getPasswordEncrypt(
                                request.getParameter("password"),
                                (String)this.getServletContext().getAttribute("salt")
                                ))
                        .getSingleResult();
                f.setEmployee(e_id);
                f.setFollow(e_id);
                f.setCreated_at(currentTime);
                f.setUpdated_at(currentTime);
                request.getSession().setAttribute("flush", "登録が完了しました。");

                _em.getTransaction().begin();
                _em.persist(f);
                _em.getTransaction().commit();
                _em.close();

                response.sendRedirect(request.getContextPath() + "/employees/index");
            }
        }
    }

}
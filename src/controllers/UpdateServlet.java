package controllers;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //sessionスコープに格納されている"_token"をformから送信された際に取得
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            //Task型の参照変数tは、右辺のTaskクラスから取得されたIDを持つデータをオブジェクトとして参照
            Task t=em.find(Task.class,(Integer)(request.getSession().getAttribute("task_id")));

            //フォームから送信された値をTaskクラスのsetterを使って、該当するフィールドの値を際代入して上書きする。
            //上記でTaskクラスのオウジェクトが生成されているので、Task型のtはメソッドを使用できる
            String contet=request.getParameter("content");
            t.setContent(contet);

            Timestamp currentTime=new Timestamp(System.currentTimeMillis());
            t.setUpdated_at(currentTime);


            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            //セッションスコープに格納されている"task_id"は不要なので削除
            request.getSession().removeAttribute("task_id");

            response.sendRedirect(request.getContextPath() + "/index");
        }

    }

}

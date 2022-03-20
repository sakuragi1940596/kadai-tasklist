package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CSRF対策
        request.setAttribute("_token",request.getSession().getId());

        //Taskオブジェクトを生成して、リクエストスコープに格納
        request.setAttribute("task",new Task());

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tasks/new.jsp");
        rd.forward(request, response);

        /* createservletへ
        //DBに接続
        EntityManager em=DBUtil.createEntityManager();
        em.getTransaction().begin();
        //DTOクラスであるTaskのインスタンスオブジェクトを生成（コンストラクタ実行）
        Task t=new Task();
        //Taskクラスの各フィールド（tasksテーブルのカラム）に値を代入する
        String content="今日中にやろう";
        t.setContent(content);

        Timestamp currentTime=new Timestamp(System.currentTimeMillis());
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        //Taskオブジェクトの各フィールドの値をtasksテーブルの対応するカラムにpersistで保存し、commitで更新
        em.persist(t);
        em.getTransaction().commit();

        //自動採番した値の表示
        response.getWriter().append(Integer.valueOf(t.getId()).toString());
        */

    }

}

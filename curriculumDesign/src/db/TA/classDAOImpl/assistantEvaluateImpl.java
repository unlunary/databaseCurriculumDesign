package db.TA.classDAOImpl;

import db.DAO.dbconnection.ConnectionImpl;
import db.TA.classDAO.assistantEvaluateDAO;
import db.TA.class_.assistantEvaluate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class assistantEvaluateImpl extends ConnectionImpl implements assistantEvaluateDAO {

    //学生功能2：可以查看自身的助教成果（已通过）与助教记录（通过或不通过），若是返回了多条记录？需要检查一下。
    public void SearchEvaluation(String s_id){
        String sql="SELECT *FROM assistant_evaluate WHERE s_id=?";
        Connection con = null;
        try{
            con=getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, s_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                String sid=rs.getString(1);
                String cid=rs.getString(2);
                String my_eva=rs.getString(3);
                String t_eva=rs.getString(4);
                String is_qualified=rs.getString(5);
                System.out.println(sid+" "+cid+" "+my_eva+" "+t_eva+" "+is_qualified);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //学生功能4：对当前学期的助教工作进行助教自述.(需要输入自己的学号+课程号，以及自我评价)
    public void update_myeva(String s_id,String c_id,String my_eva){
        Connection con = null;
        String sql="UPDATE assistant_evaluate SET my_eva=? WHERE s_id=? AND c_id=?";
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,my_eva);
            psmt.setString(2,s_id);
            psmt.setString(3,c_id);
            psmt.executeUpdate();
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //学生功能5：查看授课老师对自己的评价与最终通过结果
    public void searcheva(String s_id,String c_id){
        String sql="SELECT teacher_eva,is_qualified FROM assistant_evaluate WHERE s_id=? AND c_id=?";
        Connection con = null;
        try{
            con=getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1, s_id);
            psmt.setString(2, c_id);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                String teacher_eva=rs.getString("teacher_eva");
                String is_qualified=rs.getString("is_qualified");
                System.out.println(teacher_eva+" "+is_qualified);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //添加一条完整的记录
    public void add_assistant_evaluate(assistantEvaluate assis_eva){
        Connection con = null;
        String sql="INSERT INTO assistant_evaluate(s_id,c_id,my_eva,teacher_eva,is_qualified)VALUES(?,?,?,?,?)";
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,assis_eva.getS_id());
            psmt.setString(2,assis_eva.getC_id());
            psmt.setString(3,assis_eva.getMy_eva());
            psmt.setString(4,assis_eva.getTeacher_eva());
            psmt.setString(5,assis_eva.getIs_qualified());
            psmt.executeUpdate();
            System.out.println("成功输入助教评价表！");
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //教师功能2：对学生的助教过程给予评价并评判通过与不通过
    public void update_t_eva(String s_id,String c_id,String teacher_eva,String is_qualified){
        Connection con = null;
        String sql="UPDATE assistant_evaluate SET teacher_eva=?,is_qualified=? WHERE s_id=? AND c_id=?";
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            psmt.setString(1,teacher_eva);
            psmt.setString(2,is_qualified);
            psmt.setString(3,s_id);
            psmt.setString(4,c_id);
            psmt.executeUpdate();
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //管理员功能5：查看所有学生已认定的助教工作的参与次数
    public void select_ta_sum(){
        Connection con = null;
        String sql="SELECT s_id,COUNT(*)AS TA_sum FROM assistant_evluate WHERE is_qualified=True GROUP BY s_id";
        try{
            con = getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                String sid=rs.getString(1);
                Integer sum=rs.getInt(2);
                System.out.println(sid+" "+sum);
            }
            psmt.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //管理员功能6：查看合格的助教表字段
    public void Searchqualified(){
        String sql="select * from assistant_evaluate WHERE is_qualified=True";
        Connection con = null;
        try{
            con=getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()){
                String sid=rs.getString(1);
                String cid=rs.getString(2);
                String my_eva=rs.getString(3);
                String t_eva=rs.getString(4);
                String is_qualified=rs.getString(5);
                System.out.println(sid+" "+cid+" "+my_eva+" "+t_eva+" "+is_qualified);
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}







/*
    Connection con = null;
    String sql="";
        try{
                con = getConnection();
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.close();
                }catch(Exception e){
                e.printStackTrace();
                }finally {
                try{
                con.close();
                }catch (Exception e){
                e.printStackTrace();
                }
                }*/
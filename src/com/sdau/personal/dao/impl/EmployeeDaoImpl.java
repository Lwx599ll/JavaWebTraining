package com.sdau.personal.dao.impl;

import com.sdau.personal.dao.IEmployeeDao;
import com.sdau.personal.pojo.EmpCountVO;
import com.sdau.personal.pojo.Employee;
import com.sdau.personal.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements IEmployeeDao{

    @Override
    public List<Employee> selectAll() {
//		1、加载驱动Class.forName("");
//		2、获得连接对象Connection
//		3、写sql语句
//		4、创建Statement(一艘船)
//		5、执行sql语句
//		     (1) 更新类（更改了表里面数据）：delete/update/insert     executeUpdate()
//		           返回值：int，表示你影响的行数
//		      (2)查询（没有改变表里面数据）:  select                              executeQuery()
//		           返回值：结果集ResultSet
//		6、关闭连接
        // Ctrl+1
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList<Employee>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender,dept_id from employee";
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age  = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int basic = Integer.parseInt(resultSet.getString("basic"));
                int jiangjin = Integer.parseInt(resultSet.getString("jiangjin"));
                int baoxiao = Integer.parseInt(resultSet.getString("baoxiao"));
                Employee employee = new Employee(id, name, age, gender, basic,jiangjin,baoxiao);
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public void deleteById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from employee where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public long selectTotalCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        long count = 0;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select count(*) from employee";
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }

        return count;
    }

    @Override
    public List<Employee> selectByPage(int offset, Integer limit) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList<Employee>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select * from employee LIMIT ?,?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age  = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int basic = Integer.parseInt(resultSet.getString("basic"));
                int jiangjin = Integer.parseInt(resultSet.getString("jiangjin"));
                int baoxiao = Integer.parseInt(resultSet.getString("baoxiao"));
                int total = basic + jiangjin + baoxiao;
                Employee employee= new Employee(id, name, age, gender,basic,jiangjin ,baoxiao,total);
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

    @Override
    public void add(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into employee(name,age,gender,basic,jiangjin,baoxiao) values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getBasic());
            preparedStatement.setInt(5, employee.getJiangjin());
            preparedStatement.setInt(6, employee.getBaoxiao());
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public Employee selectById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id,name,age,gender,basic,jiangjin,baoxiao from employee where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                // int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age  = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                int basic = Integer.parseInt(resultSet.getString("basic"));
                int jiangjin = Integer.parseInt(resultSet.getString("jiangjin"));
                int baoxiao = Integer.parseInt(resultSet.getString("baoxiao"));
                employee = new Employee(id, name, age, gender, basic,jiangjin,baoxiao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }

        return employee;
    }

    @Override
    public void update(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update employee set name=?,age=?,gender=?,basic=?,jiangjin=?,baoxiao=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setInt(2, employee.getAge());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getBasic());
            preparedStatement.setInt(5, employee.getJiangjin());
            preparedStatement.setInt(6, employee.getBaoxiao());
            preparedStatement.setInt(7, employee.getId());
            System.out.println(preparedStatement);
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, null);
        }
    }

    @Override
    public List<EmpCountVO> selectEmpCount() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EmpCountVO> list = new ArrayList<EmpCountVO>();
        try {
            connection = JDBCUtil.getConnection();
            String sql = "SELECT gender as 'name',basic+jiangjin+baoxiao as 'value'\n" +
                    "FROM employee\n" +
                    "GROUP BY gender";
            preparedStatement = connection.prepareStatement(sql);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                int value  = resultSet.getInt("value");
                EmpCountVO empCountVO = new EmpCountVO(name, value);
                list.add(empCountVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection, preparedStatement, resultSet);
        }
        return list;
    }

}
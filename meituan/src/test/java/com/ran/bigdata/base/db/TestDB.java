package com.ran.bigdata.base.db;

import com.ran.bigdata.base.domain.Book;
import com.ran.bigdata.base.utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author rk
 * @Date 2018/9/28 14:32
 * @Description:
 **/
public class TestDB {

    @Test
    public void testDB() {
        QueryRunner qr = new QueryRunner(DBCPUtils.getDataSource());

        String sql = "SELECT * FROM book";

        try {
            List<Book> list = qr.query(sql, new BeanListHandler<Book>(Book.class));
            for (Book book : list) {
                System.out.println(book);
            }

            sql = "INSERT INTO book VALUES(?,?,?,?)";
            qr.update(sql, list.get(0).getBid() + 10, list.get(0).getBname() + "10", list.get(0).getAuthor() + "10", list.get(0).getPrice() + 10);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}


package ren.yuxiang.part02.E05_jdbc;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import ren.yuxiang.part02.E05_jdbc.Dao.BaseDao;

public class BlobTest extends BaseDao {

	@Test
	public void testSaveImage() throws Exception {
		
		Connection connection = getConnectionInstance();
		String sqlString = "UPDATE t_person SET image = ? WHERE id = 11;";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		
		preparedStatement.setBlob(1, new FileInputStream("/Users/yuxiang/Desktop/timg.png"));
		preparedStatement.execute();
		
		close(connection, preparedStatement);
		
	}
	
	@Test
	public void testGetImage() throws Exception {
		Connection connection = getConnectionInstance();
		String sqlString = "SELECT image FROM t_person WHERE id = 11;";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			Blob blob = resultSet.getBlob("image");
			// 获取舒服流
			InputStream inputStream = blob.getBinaryStream();
			// 文件拷贝
			Files.copy(inputStream, Paths.get("/Users/yuxiang/Desktop/timg-1.png"));
		}
		
		close(connection, preparedStatement, resultSet);
	}
}

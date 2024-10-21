package practice.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import practice.dao.ProductDAO;
import practice.entities.Product;

public class ProductDAOImp implements ProductDAO {
	@Resource(name = "jdbc/tuongvan")
	private DataSource dataSource;
	
	public ProductDAOImp(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		this.dataSource=dataSource;
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		String sql="select * from product";
		List<Product> list=new ArrayList<Product>();
		
		try (Connection connection = this.dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int id= resultSet.getInt("id");
				String name=resultSet.getString("name");
				Double price= resultSet.getDouble("price");
				String image= resultSet.getString("image");
				list.add(new Product(id, name, price, image));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		String sql= "select *from product where id=?";
		Product product=null;
		
		try (Connection connection = this.dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			
			try (ResultSet resultSet = statement.executeQuery()) {
				while(resultSet.next()) {
					String name= resultSet.getString("name");
					Double price= resultSet.getDouble("price");
					String image= resultSet.getString("image");
					product=new Product(id, name, price, image);
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		String sql="insert into product (name,price,image) values (?,?,?)";
		
		try (Connection connection = this.dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, product.getName());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getImage());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

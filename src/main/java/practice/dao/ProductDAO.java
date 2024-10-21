package practice.dao;

import java.util.List;

import practice.entities.Product;

public interface ProductDAO {
	public List<Product> findAll();
	public Product getById(int id);
	public void addProduct(Product product);
}

package practice.controller;

import java.io.IOException;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import practice.dao.ProductDAO;
import practice.daoImpl.ProductDAOImp;
import practice.entities.Product;

@WebServlet(urlPatterns = { "/product" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Resource(name = "jdbc/tuongvan")
	private DataSource dataSource;

	private ProductDAO productDAO;

	public ProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		productDAO = new ProductDAOImp(this.dataSource);
		System.out.println("ok");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		Product p1 = new Product("Chuột xam", 40000, "img2.jpg");
//		productDAO.addProduct(p1);
		req.setAttribute("products", productDAO.findAll());
		req.getRequestDispatcher("views/product/index.jsp").forward(req, resp);
		System.out.println("ok1");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

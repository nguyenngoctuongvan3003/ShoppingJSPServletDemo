package practice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import practice.dao.ProductDAO;
import practice.daoImpl.ProductDAOImp;
import practice.entities.ItemCart;

@WebServlet(urlPatterns = {"/cart","/cart*"})
public class CartController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/tuongvan")
	private DataSource dataSource;
	
	private ProductDAO dao;
	
	public CartController() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao=new ProductDAOImp(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=req.getParameter("action")!=null?req.getParameter("action"):"";
		
		switch (action) {
		case "buy":
			doGetBuy(req, resp);
			break;
		case "remove":
			doGetRemove(req, resp);
			break;

		default:
			doGetDisplayCart(req, resp);
			break;
		}
	}
	
	protected void doGetDisplayCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/cart/index.jsp").forward(req, resp);
	}
	protected void doGetBuy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=req.getSession();
		List<ItemCart> carts=null;
		
		if (session.getAttribute("cart")==null) {
			carts=new ArrayList<ItemCart>();
		} else {
			carts=(List<ItemCart>) session.getAttribute("cart");
		}
		int index= isProductExisting(Integer.parseInt(req.getParameter("id")), carts);
		
		if (index==-1) {
			carts.add(new ItemCart(dao.getById(Integer.parseInt(req.getParameter("id"))), 1));
		} else {
			int quantity=carts.get(index).getQuantity()+1;
			carts.get(index).setQuantity(quantity);
		}
		
		session.setAttribute("cart", carts);
		resp.sendRedirect("cart");
	}
	
	protected void doGetRemove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		List<ItemCart> carts= (List<ItemCart>) session.getAttribute("cart");
		int index= isProductExisting(Integer.parseInt(req.getParameter("id")), carts);
		carts.remove(index);
		session.setAttribute("cart", carts);
		resp.sendRedirect("cart");
		
	}
	
	private int isProductExisting(int id,List<ItemCart> carts) {
		for (int i = 0; i < carts.size(); i++) {
			if(carts.get(i).getProduct().getId()==id) {
				return i;
			}
		}
		return -1;
	}
	
	
}

package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.modelo.Fabricante;
import com.ipartek.formacion.modelo.FormularioBusqueda;
import com.ipartek.formacion.modelo.Producto;
import com.ipartek.formacion.modelo.ProductoDao;



/**
 * Servlet implementation class InicioController
 */
@WebServlet("/inicio")
public class InicioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(InicioController.class);
	private final static ProductoDao dao = ProductoDao.getInstance();
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nombre = request.getParameter("nombre");
		String pmin = request.getParameter("pmin");
		String pmax = request.getParameter("pmax");
		String fabricante = request.getParameter("fabricante");
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		ArrayList<Fabricante> fabricantes = new ArrayList<Fabricante>();
		FormularioBusqueda form = new FormularioBusqueda();
		
		String mensaje = "";
		
		try {
			LOG.trace("Entramos al controlador/inicio");
			
			form = new FormularioBusqueda(nombre, pmin, pmax, fabricante);
			
			LOG.debug(String.format("filtro busqueda nombre=%s precioMinimo=%s precioMaximo=%s idFabricante=%s", nombre, pmin, pmax, fabricante));
			
			productos = dao.buscar( form.getNombre(), form.getPmin(), form.getPmax(), form.getIdFabricante());
			
			fabricantes = dao.GetAllFabricantes();
			
		} catch (Exception e) {
			LOG.error(e);
			mensaje = "existe un error";
		}finally {
			
			request.setAttribute("formulario", form);
			request.setAttribute("productos", productos);
			request.setAttribute("fabricantes", fabricantes);
			request.setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

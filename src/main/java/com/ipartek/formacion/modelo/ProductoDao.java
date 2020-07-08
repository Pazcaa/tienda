package com.ipartek.formacion.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;



public class ProductoDao {
	private final static Logger LOG = Logger.getLogger(ProductoDao.class);
	private static ProductoDao INSTANCE = null;//patrón singleton
	
	//constructor del INSTANCE
		private ProductoDao() {
			super();
		}
	
		//metodo del INSTANCE
		public static synchronized ProductoDao getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new ProductoDao();
			}
			
			return INSTANCE;
		}
		
	/**
	 * Busca productos según los parametros indicados
	 * @param nombreProducto busca la palabra 'nombreProducto' dentro del nombre del producto (LIKE), si queremos todos pasar "".
	 * @param precioMin float si no queremos filtrar pasar 0 o negativo.
	 * @param precioMax float si no queremos filtrar pasar 0 o negativo.
	 * @param idFabricante int identificador del fabricante, si queremos todos pasar un 0.
	 * @return listado de productos, si no encuentra nada, una lista vacia pero inicializada.
	 */
	
	public ArrayList<Producto> buscar(String nombreProducto, float precioMin, float precioMax, int idFabricante) throws Exception{
		
		
		//TODO que funcione con todos los parametros
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		String sql = " SELECT p.codigo, p.nombre, precio, descripcion, f.codigo 'id_fabricante', f.nombre 'fabricante' " + 
						" FROM productos p, fabricantes f " ; 
						//" WHERE p.codigo_fabricante = f.codigo " + 
						//" ORDER BY p.codigo  DESC LIMIT 500;";
		
		
		String where = " WHERE p.codigo_fabricante = f.codigo " ;
		
		if (nombreProducto != null) {
			where += " AND p.nombre LIKE '%" + nombreProducto + "%' ";
		}
		
		if (precioMin > 0 ) {
			where += " AND precio >= " + precioMin +  " ";
		}
		
		if ( precioMax >0) {
			where += " AND precio <= " + precioMax + " ";
		}
		
		if (idFabricante > 0) {
			where += " AND f.codigo = " + idFabricante +" ";
		}
		
		
		String order = " ORDER BY p.codigo  DESC LIMIT 500; ";
		
		try ( Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql + where + order);
				ResultSet rs = pst.executeQuery();
			){
			
		
			LOG.debug(pst);
			
			
				
				Producto p;
				Fabricante f;
				while (rs.next()) {
					p = new Producto();
					
					p.setId(rs.getInt("p.codigo"));
					p.setNombre(rs.getString("p.nombre"));
					p.setPrecio(rs.getFloat("precio"));
					p.setDescripcion(rs.getString("descripcion"));
				
					f = new Fabricante();
					f.setId(rs.getInt("id_fabricante"));
					f.setNombre(rs.getString("fabricante"));
					p.setFabricante(f);
					
					
					resultado.add(p);
					
				}//while
			
		
		}
		
		return resultado;
	}

	public ArrayList<Fabricante> GetAllFabricantes() {
		ArrayList<Fabricante> resultado = new ArrayList<Fabricante>();
		String sql = " SELECT codigo, nombre FROM fabricantes ORDER BY nombre ASC LIMIT 500;";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
		) {
			
			LOG.debug(pst);
			Fabricante fab;
			while (rs.next()) {
				fab = new Fabricante();
				fab.setId(rs.getInt("codigo"));
				fab.setNombre(rs.getString("nombre"));
				
				resultado.add(fab);
				
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return resultado;
	}

}


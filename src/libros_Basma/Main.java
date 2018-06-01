package libros_Basma;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;



import libros_Basma.Tlibro;

import libros_Basma.Tlibro;

public class Main {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		insertar();
		editar();
		eliminar();
		leerTodo();
		//leerPorDocumentoIdentidad("5555");
		leerTodoSinNamedQuery();

	}

	private static void leerTodoSinNamedQuery() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=null;
		EntityManager em=null;
		
		Logger log = Logger.getLogger("Main");
		try
		{
			emf=Persistence.createEntityManagerFactory("libros");
			em=emf.createEntityManager();
//			EntityTransaction et=em.getTransaction();

//			//En una sola linea
//			List<Tpersona> lista = em.createQuery("SELECT t FROM Tpersona t",Tpersona.class).getResultList();
			
			TypedQuery<Tlibro> query=em.createQuery("SELECT t FROM Tpersona t",Tlibro.class);
			List<Tlibro> lista = query.getResultList();
//			
//			System.out.println(lista);
			for(Tlibro item : lista)
			{
//				System.out.println(item.getNombre()+"......."+item.getCorreoElectronico());
				System.out.printf("%-30s %-10s %-30s\n", item.getAutor(),item.getPrecio(),item.getTitulo());
			}
			
			log.info("FIN");			
		}
		catch(Exception ex)
		{
			System.out.println("Error en listar: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}		
		
	}

//	private static void leerPorDocumentoIdentidad(String string) {
//		// TODO Auto-generated method stub
//		
//	}

	private static void leerTodo() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("libros");;
		EntityManager em=emf.createEntityManager();
//		EntityTransaction et=em.getTransaction();
		
		Logger log = Logger.getLogger("Main");
		
		try
		{
//			et.begin();
			
			//De cq de las 2 formas
			TypedQuery<Tlibro> query=em.createNamedQuery("Tpersona.findAll", Tlibro.class);
//			TypedQuery query=(TypedQuery)em.createNamedQuery("Tpersona.findAll");
			
			List<Tlibro> listaTlibro = query.getResultList();
			
//			et.commit();
			
//			System.out.println(listaTpersona);			
			for(Tlibro item : listaTlibro)
			{
//				System.out.println(item.getNombre()+"......."+item.getCorreoElectronico());
				System.out.printf("%-30s %-10s %-30s\n", item.getAutor(),item.getPrecio(),item.getTitulo());
				
			}
			
			log.info("FIN");	
		}
		catch(Exception ex)
		{
//			et.rollback();
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
		// TODO Auto-generated method stub
		
	}

	private static void eliminar() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("libros");;
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		try
		{
			int idlibro=12;
			Tlibro tlibro=em.find(Tlibro.class, idlibro);
//			System.out.println("tPersona: " + tPersona); //Si esto es null, no hacer lo siguiente
			
			et.begin();
			em.remove(tlibro);
			et.commit();
			
			System.out.println("Registro eliminado correctamente");
			
//			//Para comprobar si ha sido correctamente borrado
//			Tpersona tPersona2=em.find(Tpersona.class, 12);
//			if(tPersona2 == null) 
//				System.out.println("Definitivamente borrado");
//			else System.out.println("NO ha sido borrado");
		}
		catch(Exception ex)
		{
//			et.rollback();
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
		
	}

	private static void editar() {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try
		{		
			emf=Persistence.createEntityManagerFactory("libros");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();

			//FORMA 1: FIND + MERGE
			int idlibro=3;
			Tlibro tlibro=em.find(Tlibro.class, idlibro);
			
			tlibro.setAutor("Mohammed");
			tlibro.setTitulo("Gaza");
			tlibro.setFecha(new Date("2020/01/01"));
			tlibro.setPrecio(22.99);
			

			et.begin();
			em.merge(tlibro);
			et.commit();

			
//			//FORMA 2: Solo FIND
//			Tpersona tPersona=em.find(Tpersona.class, 3);		
//			
//			et.begin();
//			tPersona.setDocumentoIdentidad("2222");
//			et.commit();
			
			
//			//FORMA 3: Solo MERGE
//			Tpersona tPersona = new Tpersona(3);
//			tPersona.setDocumentoIdentidad("7777");
//			
//			et.begin();
//			em.merge(tPersona);
//			et.commit();
			
			
//			//Ventajas de MERGE
//			Tpersona tPersona2 = em.find(Tpersona.class, 2);
//			em.detach(tPersona2);
//			tPersona2.setIdPersona(3);
//			et.begin();
//			em.merge(tPersona2);
//			et.commit();
			
			
			System.out.println("Edición realizada correctamente");
		}
		catch(Exception ex)
		{
			System.out.println("Error: "+ex.getMessage());
//			et.rollback();
		}
		finally
		{
//			em.close();
			emf.close();
//			System.out.println("em.isOpen(): " + em.isOpen());
//			System.out.println("emf.isOpen(): " + emf.isOpen());
		}

		
	}

	private static void insertar() {
		// TODO Auto-generated method stub
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("libros");;
		EntityManager em=emf.createEntityManager();
		EntityTransaction et=em.getTransaction();
		try
		{
			Tlibro tlibro=new Tlibro();
			
//			tlibro.setIdLibro(4); //Da EXCEPCION. Habria que quitar el "GenerationType" para que funcione esto.
			tlibro.setAutor("Basma");
			tlibro.setTitulo("Palestina");
			tlibro.setFecha(new Date("2020/01/01"));
			tlibro.setPrecio(22.99);
			
			
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			String fechaRegistro = sdf.format(new Date());
//			tlibro.setFechaRegistro(new Date ());
//			
			et.begin(); //ES LO MISMO QUE: em.getTransaction().begin();
			em.persist(tlibro);
//			//Para saber que ID le ha asignado
//			System.out.println("tPersona.getIdPersona(): " + tPersona.getIdPersona());
			
			et.commit();
//			et.rollback(); //Aqui se puede poner esto
			
			System.out.println("Registro realizado correctamente");
		}
		catch(Exception ex)
		{
//			et.rollback(); //Aqui no se puede poner esto
			System.out.println("Error: "+ex.getMessage());
		}
		finally
		{
//			em.close();
			emf.close();
		}
		
	}

}

package libros_Basma;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tlibro database table.
 * 
 */
@Entity
@Table(name="tlibro")
@NamedQuery(name="Tlibro.findAll", query="SELECT t FROM Tlibro t")
public class Tlibro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=50)
	private String autor;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	@Column(nullable=false)
	private int id;

	@Column(nullable=false)
	private double precio;

	@Column(nullable=false, length=50)
	private String titulo;

	public Tlibro() {
	}

	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
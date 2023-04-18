
package modelo.pojos;


public class Catalogo {
   
    private Integer idCatalogo;
    private Integer idCategoria;
    private String nombre;
    private String activo;
    private Integer  orden;

    public Catalogo() {
    }

    public Catalogo(Integer idCatalogo, Integer idCategoria, String nombre, String activo, Integer orden) {
        this.idCatalogo = idCatalogo;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.activo = activo;
        this.orden = orden;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    
    

}

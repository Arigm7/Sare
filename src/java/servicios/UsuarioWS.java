
package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import utils.JavaUtils;


@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
    
    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario>getAllUsers(){
    List<Usuario> list = new ArrayList<Usuario>();
    SqlSession conn=null;
    try{
        conn=MyBatisUtil.getSession();
        list=conn.selectList("Usuario.getAllUsers");
    }catch(Exception ex){
        ex.printStackTrace();
    }finally{
        if(conn!=null){
            conn.close();
        }
    }
    return list;
    }
    
    @POST
    @Path("registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("celular") String celular,
            @FormParam("usuario") String usuario,
            @FormParam("contrasena") String contrasena,
            @FormParam("idRol") Integer idRol,
            @FormParam("idEstatus") Integer idEstatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("celular", celular);
            param.put("usuario", usuario);
            param.put("contrasena", JavaUtils.hashString(contrasena.toUpperCase()));
            param.put("idRol", idRol);
            param.put("idEstatus", idEstatus);
            
            conn.insert("Usuario.registrarUsuario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Usuario registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el usuario");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    
    @POST
    @Path("actualizarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarUsuario(
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("celular") String celular,
            @FormParam("usuario") String usuario,
            @FormParam("contrasena") String contrasena,
            @FormParam("idRol") Integer idRol,
            @FormParam("idEstatus") Integer idEstatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idUsuario", idUsuario);
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("celular", celular);
            param.put("usuario", usuario);
            param.put("contrasena", JavaUtils.hashString(contrasena.toUpperCase()));
            param.put("idRol", idRol);
            param.put("idEstatus", idEstatus);
            
            conn.update("Usuario.actualizarUsuario",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Usuario actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el usuario");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    
    @POST
    @Path("actualizarEstatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusUsuario(
            @FormParam("idUsuario") Integer idUsuario,
            @FormParam("idEstatus") Integer idEstatus){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idUsuario", idUsuario);
            param.put("idEstatus", idEstatus);
            
            conn.update("Usuario.actualizarEstatus",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Estatus actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar el estatus");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
}



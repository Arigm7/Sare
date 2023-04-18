/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Categoria;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;


@Path("categoria")
public class CategoriaWS {

    @Context
    private UriInfo context;


    public CategoriaWS() {
    }

    
    @GET
    @Path("getAllCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria>getAllCategoria(){
        List<Categoria> list = new ArrayList<Categoria>();
        SqlSession conn=null;
        try{
            conn=MyBatisUtil.getSession();
            list=conn.selectList("Categoria.getAllCategoria");
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
    @Path("registrarCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarCategoria(
            @FormParam("idCategoria") Integer idCategoria,
            @FormParam("nombre") String nombre,
            @FormParam("activo") String activo,
            @FormParam("orden") Integer orden){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCategoria", idCategoria);
            param.put("nombre", nombre);
            param.put("activo", activo);
            param.put("orden", orden);
            
            conn.insert("Categoria.registrarCategoria",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Categoria registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar la categoria");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    
    @POST
    @Path("actualizarCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarCategoria(
            @FormParam("idCategoria") Integer idCategoria,
            @FormParam("nombre") String nombre,
            @FormParam("activo") String activo,
            @FormParam("orden") Integer orden){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCategoria", idCategoria);
            param.put("nombre", nombre);
            param.put("activo", activo);
            param.put("orden", orden);
            
            conn.update("Categoria.actualizarCategoria",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Categoria actualizado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo actualizar la categoria");
        }finally{
            conn.close();
        }
        return res;
    }
    
    
    
    @POST
    @Path("actualizarEstatusCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatusCatalogo(
            @FormParam("idCategoria") Integer idCategoria,
            @FormParam("activo") String activo){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("idCategoria", idCategoria);
            param.put("activo", activo);
        
            
            conn.update("Categoria.actualizarEstatusCategoria",param);
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

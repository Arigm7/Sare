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
import modelo.pojos.Personal;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;
import utils.JavaUtils;

/**
 * REST Web Service
 *
 * @author Alex
 */
@Path("personal")
public class PersonalWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonalWS
     */
    public PersonalWS() {
    }

    @GET
    @Path("getAllPersonal")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Personal>getAllPersonal(){
    List<Personal> list = new ArrayList<Personal>();
    SqlSession conn=null;
    try{
        conn=MyBatisUtil.getSession();
        list=conn.selectList("Personal.getAllPersonal");
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
    @Path("registrarPersonal")
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarPersonal(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("curp") String curp,
            @FormParam("rfc") String rfc,
            @FormParam("fechaNacimiento") String fechaNacimiento,
            @FormParam("sexo") String sexo,
            @FormParam("calle") String calle,
            @FormParam("colonia") String colonia,
            @FormParam("numExt") String numExt,
            @FormParam("numInt") String numInt,
            @FormParam("cp") Integer cp,
            @FormParam("idPais") Integer idPais,
            @FormParam("idEntidadFederativa") Integer idEntidadFederativa,
            @FormParam("idMunicipio") Integer idMunicipio,
            @FormParam("email") String email,
            @FormParam("celular") String celular,
            @FormParam("idEstadoCivil") Integer idEstadoCivil,
            @FormParam("ultimoGradoEstudio") String ultimoGradoEstudio,
            @FormParam("profecion") String profecion,
            @FormParam("idEstatus") Integer idEstatus,
            @FormParam("tiempoCreacion") String tiempoCreacion,
            @FormParam("tiempoActualizacion") Integer tiempoActualizacion){
        
        Respuesta res = new Respuesta();
        SqlSession conn = MyBatisUtil.getSession();
        
        try{
            HashMap<String,Object> param = new HashMap<String,Object>();
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("curp", curp);
            param.put("rfc", rfc);
            param.put("fechaNacimiento", fechaNacimiento);
            param.put("sexo", sexo);
            param.put("calle", calle);
            param.put("colonia", colonia);
            param.put("numExt", numExt);
            param.put("numInt",numInt);
            param.put("cp", cp);
            param.put("idPais", idPais);
            param.put("idEntidadFederativa", idEntidadFederativa);
            param.put("idMunicipio", idMunicipio);
            param.put("email", email);
            param.put("celular", celular);
            param.put("idEstadoCivil", idEstadoCivil);
            param.put("ultimoGradoEstudio", ultimoGradoEstudio);
            param.put("profecion", profecion);
            param.put("tiempoCreacion", tiempoCreacion);
            param.put("tiempoActualizacion", tiempoActualizacion);

            
            conn.insert("Personal.registrarPersonal",param);
            conn.commit();
            res.setError(false);
            res.setMensaje("Personal registrado correctamente...");
         
        }catch(Exception ex){
            ex.printStackTrace();
            res.setError(true);
            res.setMensaje("No se pudo registrar el personal");
        }finally{
            conn.close();
        }
        return res;
    }
    
}

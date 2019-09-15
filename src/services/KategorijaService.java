package services;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.KategorijaDAO;
import beans.Kategorija;

@Path("")
public class KategorijaService {

		@Context
		ServletContext context;
		
		@PostConstruct 
		public void init() {
			
			if(context.getAttribute("KategorijaDAO") == null) {
				context.setAttribute("KategorijaDAO", new KategorijaDAO());
			}
		}
		
		@GET
		@Path("/categoryinfo")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Collection<Kategorija> getKategorije(@Context HttpServletRequest request)
		{
			KategorijaDAO kategorije = (KategorijaDAO) context.getAttribute("KategorijaDAO");
			return kategorije.getKategorije().values();
		}
		
		
	
}


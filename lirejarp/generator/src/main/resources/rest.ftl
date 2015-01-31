package de.starwit.lirejarp.api.rest;


import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.starwit.lirejarp.api.rest.response.EntityResponse;
import de.starwit.lirejarp.ejb.${domain}Service;
import de.starwit.lirejarp.entity.${domain}Entity;

@Path("/${domainLower}")
@Consumes("application/json")
@Produces("application/json")
public class ${domain}Rest extends AbstractRest<${domain}Entity> {
	
	@Inject
	protected ${domain}Service service;
	
	@Override
	protected ${domain}Service getService() {
		return service;
	}
	
	//Create
	@Path("/create")
	@PUT
	@Override
	public EntityResponse<${domain}Entity> create(${domain}Entity entity) {
		return super.createGeneric(entity);
	}

	//Update
	@Path("/update")
	@POST
	@Override
	public EntityResponse<${domain}Entity> update(${domain}Entity entity) {
		return super.updateGeneric(entity);
	}
}
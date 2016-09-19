package com.darius.phonesystem;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bicboi on 9/5/16.
 */
@Path("/rest")
public class PhoneRestService {
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntry(PhoneEntryBean phoneEntryBean){
        try{
            PhoneBL phoneBL = new PhoneBL();
            phoneBL.addPhoneEntry(phoneEntryBean);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEntry(PhoneEntryBean phoneEntryBean){
        try{
            PhoneBL phoneBL = new PhoneBL();
            phoneBL.deletePhoneEntry(phoneEntryBean);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return Response.status(200).build();
    }

    @GET
    @Path("/display")
    @Produces(MediaType.APPLICATION_JSON)
    public Response displayTable(){
       List<PhoneEntryBean> phoneDirectory = new ArrayList<PhoneEntryBean>();
        try {
            PhoneBL phoneBL = new PhoneBL();
            phoneDirectory = phoneBL.getPhoneDirectory();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //Necessary in order to avoid type erasure
        GenericEntity<List<PhoneEntryBean>> list = new GenericEntity<List<PhoneEntryBean>>(phoneDirectory) {};
        return Response.status(Response.Status.OK).entity(list).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("/entry")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByPhoneNumber(@QueryParam(value = "phoneNumber") final String phoneNumber){
        PhoneEntryBean phoneEntryBean = null;
        try {
            PhoneBL phoneBL = new PhoneBL();
            phoneEntryBean = phoneBL.getByPhoneNumber(phoneNumber);
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return Response.status(Response.Status.OK).entity(phoneEntryBean).type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntry(PhoneEntryBean phoneEntryBean){
        try{
            PhoneBL phoneBL = new PhoneBL();
            phoneBL.updatePhoneEntry(phoneEntryBean);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return Response.status(200).build();
    }


}

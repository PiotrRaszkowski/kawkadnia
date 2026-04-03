package pl.kawkadnia.briefings.boundary;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kawkadnia.briefings.control.BriefingService;
import pl.kawkadnia.briefings.entity.Briefing;
import pl.kawkadnia.briefings.entity.CreateBriefingRequest;

import java.time.LocalDate;
import java.util.List;

@Path("/api/briefings")
@Slf4j
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BriefingsResource {

    private final BriefingService briefingService;

    @GET
    @Path("/today")
    public Briefing getToday() {
        return briefingService.getBriefingByDate(LocalDate.now());
    }

    @GET
    @Path("/dates")
    public List<LocalDate> getDates() {
        return briefingService.getAllDates();
    }

    @GET
    @Path("/{date}")
    public Briefing getByDate(@PathParam("date") LocalDate date) {
        return briefingService.getBriefingByDate(date);
    }

    @POST
    @RolesAllowed("admin")
    public Response create(@Valid CreateBriefingRequest request) {
        Briefing briefing = briefingService.createBriefing(request);
        return Response.status(Response.Status.CREATED).entity(briefing).build();
    }
}

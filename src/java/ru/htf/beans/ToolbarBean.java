package ru.htf.beans;

import ru.htf.datatypes.Airport;
import ru.htf.service.AirportService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chudin_e_v
 */
@ManagedBean
@ViewScoped
public class ToolbarBean {

    private List<Airport> airports = new ArrayList<Airport>();
    private Integer airportFrom;
    private Integer airportTo;
    private Date dateFrom;
    private Date dateBack;
    private Date maxDate;

    @ManagedProperty("#{airportService}")
    private AirportService aService;
    
    @PostConstruct
    void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        aService.init();
    }

    public void preRenderedViewListener() {
    }

    public final void fetchAirportList() {
        airports = aService.getAirports();
    }

    public ToolbarBean() {
        //ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        fetchAirportList();
    }

    public void find() {
    }

    public void changePorts() {
    }

    public List<Airport> completePort(String query) {
        List<Airport> filteredPorts = new ArrayList<Airport>();

        for (Airport port : airports) {
            if (port.getCity().toLowerCase().startsWith(query)
                    || port.getName().toLowerCase().startsWith(query)
                    || port.getShortName().toLowerCase().startsWith(query)) {
                filteredPorts.add(port);
            }
        }
        return filteredPorts;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public Integer getAirportFrom() {
        return airportFrom;
    }

    public void setaService(AirportService aService) {
        this.aService = aService;
    }
    
    public void setAirportFrom(Integer airport) {
        this.airportFrom = airport;
    }

    public Integer getAirportTo() {
        return airportTo;
    }

    public void setAirportTo(Integer airport) {
        this.airportTo = airport;
    }

    public void setDateBack(Date dateBack) {
        this.dateBack = dateBack;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateBack() {
        return dateBack;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getMinBackDate() {
        return dateFrom == null ? new Date() : dateFrom;
    }
}

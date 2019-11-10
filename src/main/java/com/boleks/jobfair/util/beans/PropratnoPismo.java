
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PropratnoPismo {
    
    private int idPropratnoPismo;
    private int idPrijave;
    private String propratnoPismo;

    public PropratnoPismo() {
    }

    public PropratnoPismo(int idPropratnoPismo, int idPrijave, String propratnoPismo) {
        this.idPropratnoPismo = idPropratnoPismo;
        this.idPrijave = idPrijave;
        this.propratnoPismo = propratnoPismo;
    }
    
    public PropratnoPismo(ResultSet rs) throws SQLException {
        this.idPropratnoPismo = rs.getInt("idPropratnoPismo");
        this.idPrijave = rs.getInt("idPrijava");
        this.propratnoPismo = rs.getString("propratnoPismo");
    }

    public int getIdPropratnoPismo() {
        return idPropratnoPismo;
    }

    public void setIdPropratnoPismo(int idPropratnoPismo) {
        this.idPropratnoPismo = idPropratnoPismo;
    }

    public int getIdPrijave() {
        return idPrijave;
    }

    public void setIdPrijave(int idPrijave) {
        this.idPrijave = idPrijave;
    }

    public String getPropratnoPismo() {
        return propratnoPismo;
    }

    public void setPropratnoPismo(String propratnoPismo) {
        this.propratnoPismo = propratnoPismo;
    }
    
    
            
    
}

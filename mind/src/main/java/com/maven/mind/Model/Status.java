package com.maven.mind.Model;

import com.maven.mind.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Status {

    private int id;
    private int opId;
    private float temp;
    private float pressure;
    private int rpm;
    private Timestamp timestamp;

    public Status(){};

    public Status( int id,int opId, float temp, float pressure, int rpm, Timestamp timestamp ){
        this.id = id;
        this.opId = opId;
        this.temp = temp;
        this.pressure = pressure;
        this.rpm = rpm;
        this.timestamp = timestamp;
    }

    public int getId() {        return id;    }
    public int getOpId() {        return opId;    }
    public float getPressure() {        return pressure;    }
    public float getTemp() {        return temp;    }
    public int getRpm() {        return rpm;    }
    public Timestamp getTimestamp() {        return timestamp;    }

    public void setId(int id) {        this.id = id;    }
    public void setOpId(int opId) {        this.opId = opId;    }
    public void setPressure(float pressure) {        this.pressure = pressure;    }
    public void setRpm(int rpm) {        this.rpm = rpm;    }
    public void setTemp(float temp) {        this.temp = temp;    }
    public void setTimestamp(Timestamp timestamp) {        this.timestamp = timestamp;    }

    public int saveStatus ( Status status, Connection con ){
        int done = 0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("INSERT INTO status ( opId, temp, pressure, rpm ) VALUES(?,?,?,?)");
            pst.setInt(1,status.getOpId());
            pst.setFloat(2,status.getTemp());
            pst.setFloat(3,status.getPressure());
            pst.setInt(4,status.getRpm());
            pst.execute();
            done = 1;

        } catch (Exception ex ){
            System.out.println(ex);
        } finally {
            if ( con != null ){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return done;
    }

    public Collection<Status> getStatusByOpId (Status id, Connection con ){
        Collection<Status> statuses = new ArrayList<>();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id,temp,pressure,rpm,timestamp from status where opId=? and id>? ");
            pst.setInt(1,id.getOpId());
            pst.setInt(2,id.getId());
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ){
                statuses.add(new Status(rs.getInt("id"),id.getOpId(),rs.getFloat("temp"),rs.getFloat("pressure"),rs.getInt("rpm"),rs.getTimestamp("timestamp")));
            }
        } catch (Exception ex ){
            System.out.println(ex);
        } finally {
            if ( con != null ){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return statuses;
    }

}

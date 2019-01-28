package com.maven.mind.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Warning {

    private int id;
    private int opId;
    private int rpmCurr;
    private int rpmReq;
    private float temp;
    private Timestamp timestamp;

    public Warning(){};

    public Warning( int id, int opId, int rpmCurr, int rpmReq, Timestamp timestamp, float temp ){
        this.id = id;
        this.opId = opId;
        this.rpmCurr = rpmCurr;
        this.rpmReq = rpmReq;
        this.timestamp = timestamp;
        this.temp = temp;
    }

    public void setOpId(int opId) {        this.opId = opId;    }
    public void setId(int id) {        this.id = id;    }
    public void setTimestamp(Timestamp timestamp) {        this.timestamp = timestamp;    }
    public void setRpmCurr(int rpmCurr) {        this.rpmCurr = rpmCurr;    }
    public void setRpmReq(int rpmReq) {        this.rpmReq = rpmReq;    }
    public void setTemp(float temp) {        this.temp = temp;    }

    public int getOpId() {        return opId;    }
    public int getId() {        return id;    }
    public int getRpmCurr() {        return rpmCurr;    }
    public int getRpmReq() {        return rpmReq;    }
    public Timestamp getTimestamp() {        return timestamp;    }
    public float getTemp() {        return temp;    }

    public int saveWarning (Warning warn, Connection con ){
        int done = 0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("INSERT INTO warning ( opId, rpmCurr , rpmReq ) VALUES(?,?,?)");
            pst.setInt(1,warn.getOpId());
            pst.setInt(2,warn.getRpmCurr());
            pst.setInt(3,warn.getRpmReq());
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

    public Collection<Warning> getWarningByOpId (Warning info, Connection con ){
        Collection<Warning> warn = new ArrayList<>();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id,opId,timestamp,rpmCurr,rpmReq,temp from warning where opId = ? and id>? ");
            pst.setInt(1,info.getOpId());
            pst.setInt(2,info.getId());
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ){
                warn.add(new Warning(rs.getInt("id"),info.getOpId(),rs.getInt("rpmCurr"),rs.getInt("rpmReq"),rs.getTimestamp("timestamp"),rs.getFloat("temp")));
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
        return  warn;
    }

}

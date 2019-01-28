package com.maven.mind.Model;

import com.maven.mind.Utility;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class Operation {

    private int id;
    private int hospitalId;
    private String name;
    private boolean live;
    private Timestamp timestamp;

    public Operation(){};

    public Operation( int hospitalId, String name ){
        this.hospitalId = hospitalId;
        this.name = name;
    }
    public Operation(int id, int hospitalId, String name, boolean live, Timestamp timestamp ){
        this.hospitalId = hospitalId;
        this.name = name;
        this.live = live;
        this.id = id;
        this.timestamp = timestamp;
    }

    public void setName(String name) {this.name = name;}
    public void setId(int id) {this.id = id;}
    public void setHospitalId(int hospitalId) {this.hospitalId = hospitalId;}
    public void setLive(boolean live) {this.live = live;}
    public void setTimestamp(Timestamp timestamp) {        this.timestamp = timestamp;    }

    public int getId() {return id;}
    public String getName() {return name;}
    public boolean isLive() {        return live;    }
    public int getHospitalId() {        return hospitalId;    }
    public Timestamp getTimestamp() {        return timestamp;    }

    public int saveOp (Operation op, Connection con ){
        int done = 0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("INSERT INTO operations ( hospitalId,  name ) VALUES(?,?)");
            pst.setInt(1,op.getHospitalId());
            pst.setString(2,op.getName());
            pst.execute();
            new Info().newOp(op.getHospitalId(),con);
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

    public Collection<Operation> getOpsByHospital ( int id, Connection con ){
        Collection<Operation> ops= new ArrayList<>();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id,name,live,timestamp from operations where hospitalId = ? order by id desc");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ){
                ops.add(new Operation(rs.getInt("id"),id,rs.getString("name"),rs.getBoolean("live"),rs.getTimestamp("timestamp")));
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
        return ops;
    }

    public Collection<Operation> getLiveOpsByHospital ( int id, Connection con ){
        Collection<Operation> ops= new ArrayList<>();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id,name,live,timestamp from operations where hospitalId = ? and live = 1 order by id desc");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ){
                ops.add(new Operation(rs.getInt("id"),id,rs.getString("name"),rs.getBoolean("live"),rs.getTimestamp("timestamp")));
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
        return ops;
    }

    public Collection<Operation> getDoneOpsByHospital ( int id, Connection con ){
        Collection<Operation> ops= new ArrayList<>();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id,name,live,timestamp from operations where hospitalId = ? and live=0 order by id desc");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while ( rs.next() ){
                ops.add(new Operation(rs.getInt("id"),id,rs.getString("name"),rs.getBoolean("live"),rs.getTimestamp("timestamp")));
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
        return ops;
    }

    public int opDone (Operation op, Connection con ){
        int done=0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("UPDATE operations set live=0 where id=?");
            pst.setInt(1,op.getId());
            pst.execute();
            new Info().opDone(op.getHospitalId(),con);
            done=1;
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

}

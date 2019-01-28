package com.maven.mind.Model;

import java.sql.*;

public class Info {

    private int id;
    private String emailId;
    private String name;
    private String password;
    private int liveOp;
    private int totalOp;

    public Info(){};

    public Info ( String emailId, String name, String password ){
        this.emailId = emailId;
        this.name = name;
        this.password = password;
    }
    public Info ( int id, String emailId, String name, String password, int totalOp, int liveOp ){
        this.emailId = emailId;
        this.name = name;
        this.password = password;
        this.id = id;
        this.totalOp = totalOp;
        this.liveOp = liveOp;
    }
    public Info ( String name, int totalOp, int liveOp ){
        this.name = name;
        this.totalOp = totalOp;
        this.liveOp = liveOp;
    }

    public void setName(String name) {this.name = name;}
    public void setId(int id) {this.id = id;}
    public void setEmailId(String emailId) {this.emailId = emailId;}
    public void setPassword(String password) {this.password = password;}
    public void setLiveOp(int liveOp) {this.liveOp = liveOp;}
    public void setTotalOp(int totalOp) {this.totalOp = totalOp;}

    public int getId() {return id;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public String getEmailId() {return emailId;}
    public int getLiveOp() {return liveOp;}
    public int getTotalOp() { return totalOp;}

    public int saveInfo (Info info, Connection con ){
        int done = 0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("INSERT INTO info ( emailId, name , password ) VALUES(?,?,?)");
            pst.setString(2,info.getName());
            pst.setString(1,info.getEmailId());
            pst.setString(3,info.getPassword());
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

    public int getId (Info info, Connection con ){
        int id=0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT id from info where emailId = ? and password = ?");
            pst.setString(1,info.getEmailId());
            pst.setString(2,info.getPassword());
            ResultSet rs = pst.executeQuery();
            if ( rs.next() ){
                id = rs.getInt("id");
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
        return id;
    }

    public Info getInfo (int id, Connection con ){
        Info info= new Info();
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("SELECT name,liveOp,totalOp from info where id = ?");
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            if ( rs.next() ){
                info = new Info(rs.getString("name"), rs.getInt("totalOp"), rs.getInt("liveOp"));
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
        return info;
    }

    public int opDone (int id, Connection con ){
        int done=0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("UPDATE info set liveOp = liveOp-1, totalOp = totalOp+1 where id=?");
            pst.setInt(1,id);
            pst.execute();
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

    public int newOp (int id, Connection con ){
        int done=0;
        try {
            if ( con == null ){
                String url ="jdbc:mysql://mindsphere.mysql.database.azure.com:3306/mind?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                con = DriverManager.getConnection(url, "haniket@mindsphere", "11@1998Hack");
            }
            PreparedStatement pst = con.prepareStatement("UPDATE info set liveOp = liveOp+1 where id=?");
            pst.setInt(1,id);
            pst.execute();
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

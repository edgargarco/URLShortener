package root.URLShortener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Statistics {
    private int linuxUser;
    private int windowsUser;
    private int iOSUser;
    private int androidUser;
    private List<Visit> ips;
    @JsonIgnore
    private List<TempVisits> tempIps;


    public Statistics(int linuxUser, int windowsUser, int iOSUser, int androidUser, List<Visit> ips) {
        this.linuxUser = linuxUser;
        this.windowsUser = windowsUser;
        this.iOSUser = iOSUser;
        this.androidUser = androidUser;
        this.ips = ips;
    }
    public Statistics(List<TempVisits> tempIps,int linuxUser, int windowsUser, int iOSUser, int androidUser) {
        this.linuxUser = linuxUser;
        this.windowsUser = windowsUser;
        this.iOSUser = iOSUser;
        this.androidUser = androidUser;
        this.tempIps = tempIps;
    }


    public int getLinuxUser() {
        return linuxUser;
    }

    public void setLinuxUser(int linuxUser) {
        this.linuxUser = linuxUser;
    }

    public int getWindowsUser() {
        return windowsUser;
    }

    public void setWindowsUser(int windowsUser) {
        this.windowsUser = windowsUser;
    }

    public int getiOSUser() {
        return iOSUser;
    }

    public void setiOSUser(int iOSUser) {
        this.iOSUser = iOSUser;
    }

    public int getAndroidUser() {
        return androidUser;
    }

    public void setAndroidUser(int androidUser) {
        this.androidUser = androidUser;
    }

    public List<Visit> getIps() {
        return ips;
    }

    public void setIps(List<Visit> ips) {
        this.ips = ips;
    }

    public List<TempVisits> getTempIps() {
        return tempIps;
    }

    public void setTempIps(List<TempVisits> tempIps) {
        this.tempIps = tempIps;
    }
}

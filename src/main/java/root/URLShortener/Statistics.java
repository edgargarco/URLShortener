package root.URLShortener;

import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private int linuxUser;
    private int windowsUser;
    private int iOSUser;
    private int androidUser;
    private List<Object> ips = new ArrayList<>();

    public Statistics(int linuxUser, int windowsUser, int iOSUser, int androidUser, List<Object> ips) {
        this.linuxUser = linuxUser;
        this.windowsUser = windowsUser;
        this.iOSUser = iOSUser;
        this.androidUser = androidUser;
        this.ips = ips;
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

    public List<Object> getIps() {
        return ips;
    }

    public void setIps(List<Object> ips) {
        this.ips = ips;
    }
}

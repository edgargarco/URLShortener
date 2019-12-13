package root.URLShortener;

public class Browser{
    private String browser;
    private long cantVisit;

    public Browser() {
    }

    public Browser(String browser, long cantVisit) {
        this.browser = browser;
        this.cantVisit = cantVisit;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public long getCantVisit() {
        return cantVisit;
    }

    public void setCantVisit(long cantVisit) {
        this.cantVisit = cantVisit;
    }

    @Override
    public String toString() {
        return "Browser{" +
                "browser='" + browser + '\'' +
                ", cantVisit=" + cantVisit +
                '}';
    }
}

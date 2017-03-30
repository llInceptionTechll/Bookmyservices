package tech.inception.bookmyservices.volley;

public class Global   {

    private static Global instance = null;
    private String IP = "";

    public Global()
    {
        instance = this;
    }

    public static synchronized Global getInstance()
    {
        if(instance == null)
        {
            new Global();
            return instance;
        }
        return instance;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }
}


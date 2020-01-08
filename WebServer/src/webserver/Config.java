package webserver;

import java.io.File;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config
{
    @XmlElement
    public File web_root = new File(".");
    @XmlElement
    public String default_file = "index.html";
    @XmlElement
    public String file_not_found = "404.html";
    @XmlElement
    public String method_not_supported = "not_supported.html";
    @XmlElement
    public int port = 8080;
    @XmlElement
    public boolean verbose = true;

    public Config(){}
}

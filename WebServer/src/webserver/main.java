package webserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class main
{
    public static void main(String[] args) throws JAXBException {
		try {
                    Config con = new Config();
                    JAXBContext jaxbContext = JAXBContext.newInstance( Config.class );
                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    //jaxbMarshaller.marshal(con, new File("maronnamia.xml"));
                    Config cfg = (Config) jaxbUnmarshaller.unmarshal(new File("maronnamia.xml"));
                    
			ServerSocket serverConnect = new ServerSocket(cfg.port);
			System.out.println("Server started in " + cfg.web_root.getAbsolutePath() + "\nListening for connections on port : " + cfg.port + " ...\n");
			
			// we listen until user halts server execution
			while (true) {
				WebServer myServer = new WebServer(serverConnect.accept(), cfg);
				
				if (cfg.verbose) {
					System.out.println("Connecton opened. (" + new Date() + ")");
				}
				
				// create dedicated thread to manage the client connection
				Thread thread = new Thread(myServer);
				thread.start();
			}
			
		} catch (IOException e) {
			System.err.println("Server Connection error : " + e.getMessage());
		}
	}
}


package Servicio;

import Thrift.FileData;
import Thrift.FileInfoExtractService;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

public class ServiceDownload {
    
    public void download(String rutaServer, String rutaClient){
        try {
            TSocket socket = new TSocket("localhost",9095);
            socket.setTimeout(60*1000);
            TFramedTransport framedTransport = new TFramedTransport(socket);
            framedTransport.open();
            TBinaryProtocol binaryProtocol = new TBinaryProtocol(framedTransport);
            FileInfoExtractService.Client client = new FileInfoExtractService.Client(binaryProtocol);
            FileData fileData = client.downloadFile(rutaServer);
            try{
                java.io.File file = new java.io.File(rutaClient);
                FileOutputStream fos = new FileOutputStream(file);
                FileChannel channel = fos.getChannel();
                channel.write(fileData.buff);
                channel.close();
            }catch (FileNotFoundException ex){
                System.out.println(ex);
            } catch (IOException ex) {
                Logger.getLogger(ServiceDownload.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (TTransportException ex) {
            Logger.getLogger(ServiceDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TException ex) {
            Logger.getLogger(ServiceDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

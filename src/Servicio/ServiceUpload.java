package Servicio;

import Thrift.FileData;
import Thrift.FileInfoExtractService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import org.apache.thrift.transport.TSocket;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TFramedTransport;
public class ServiceUpload {
    
    public boolean uploadFile(String routeClient,String routeServer){
        byte[] bytes = toByteArray(routeClient);
        FileData filedata = new FileData();
        filedata.name = routeClient;
        filedata.buff = ByteBuffer.wrap(bytes);
        try{
            TSocket socket = new TSocket("localhost", 9095);
            socket.setSocketTimeout(60 * 1000);
            TFramedTransport framedTransport = new TFramedTransport(socket);
            framedTransport.open();
            TBinaryProtocol binaryProtocol = new TBinaryProtocol(framedTransport);
            FileInfoExtractService.Client client = new FileInfoExtractService.Client(binaryProtocol);
            client.uploadFile(filedata, routeServer);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    private byte[] toByteArray(String route) {
        byte[] buffer = null;
        try{
            File file = new File(route);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            
            while ((n = fis.read(b))!= -1){
                bos.write(b,0,n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return buffer;
    }
}

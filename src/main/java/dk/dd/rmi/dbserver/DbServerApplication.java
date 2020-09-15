package dk.dd.rmi.dbserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

@SpringBootApplication
public class DbServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DbServerApplication.class, args);
    }
}

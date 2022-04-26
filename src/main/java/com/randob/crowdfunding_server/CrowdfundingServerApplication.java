package com.randob.crowdfunding_server;

import com.github.lalyos.jfiglet.FigletFont;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CrowdfundingServerApplication {

  public static void main(String[] args) throws IOException {
    SpringApplication.run(CrowdfundingServerApplication.class, args);
    System.out.println(FigletFont.convertOneLine(FigletFont.class.getResourceAsStream("/flf/smslant.flf"), "PoweredByRandoB"));
  }

}

package com.rocketseat.certification_nlw.seed;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.imageio.IIOException;
import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateSeed {

    private final JdbcTemplate jdbcTemplate;


    public CreateSeed(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public static void main(String[]  args){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5413/rocketseanlw");
        dataSource.setUsername("postgres");
        dataSource.setPassword("PnCdEL");

        CreateSeed createSeed = new CreateSeed(dataSource);
        createSeed.run(args);
    }

    public void run(String... args){
        executeSqlFile("src/main/resources/create.sql");
    }

    public void executeSqlFile(String filePath){
        try{
            String sqlScript = new String(Files.readAllBytes(Paths.get(filePath)));

            jdbcTemplate.execute(sqlScript);

            System.out.println("Seed realizado com sucesso");
        }catch (IOException e){
            System.err.println("Erro ao executar arquivo " + e.getMessage());
        }
    }
}

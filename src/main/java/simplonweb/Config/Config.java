package simplonweb.Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Config {
  private EntityManagerFactory emf;
  private static Config instance;

  private Config() {
    emf = Persistence.createEntityManagerFactory("simplonweb");
  }

  public static Config getInstance() {
    if (instance == null) {
      instance = new Config();
    }
    return instance;
  }

  public EntityManagerFactory getEmf() {
    return emf;
  }

  public EntityManager getEm() {
    return emf.createEntityManager();
  }

}

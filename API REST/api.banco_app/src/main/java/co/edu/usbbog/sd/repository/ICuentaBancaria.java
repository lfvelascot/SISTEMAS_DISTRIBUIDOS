package co.edu.usbbog.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usbbog.sd.model.CuentaBancaria;

public interface ICuentaBancaria extends JpaRepository<CuentaBancaria, String> {

}



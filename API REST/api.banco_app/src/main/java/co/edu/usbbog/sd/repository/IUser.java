package co.edu.usbbog.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usbbog.sd.model.User;

public interface IUser extends JpaRepository<User, String> {

}



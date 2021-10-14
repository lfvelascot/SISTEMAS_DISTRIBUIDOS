package co.edu.usbbog.sd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.usbbog.sd.model.Log;
import co.edu.usbbog.sd.model.LogPK;

public interface ILog extends JpaRepository<Log, LogPK> {

}



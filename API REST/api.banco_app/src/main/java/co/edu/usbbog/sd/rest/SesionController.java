package co.edu.usbbog.sd.rest;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.usbbog.sd.crypt.AesUtil;
import co.edu.usbbog.sd.model.Accion;
import co.edu.usbbog.sd.model.CredencialesBasic;
import co.edu.usbbog.sd.model.CuentaApp;
import co.edu.usbbog.sd.model.Log;
import co.edu.usbbog.sd.model.LogPK;
import co.edu.usbbog.sd.repository.IAccion;
import co.edu.usbbog.sd.repository.ICuentaApp;
import co.edu.usbbog.sd.repository.ILog;

@RestController
@RequestMapping("/session")
public class SesionController {

	@Autowired
	private ICuentaApp ic;
	@Autowired
	private ILog il;
	@Autowired
	private IAccion ia;
	private AesUtil codac = new AesUtil();

	@PostMapping("/login")
	public String login(@RequestBody String x) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		CredencialesBasic t = mapper.readValue(x, CredencialesBasic.class);  
		CuentaApp ci = findCuentaApp(t.getUsern());
		if (ci != null) {
			LocalDateTime now = obtenerfh();
			LogPK pk = null;
			Log l = new Log(null, "Inicio Normal");
			if (ci.getEstadoCuentaNombre().getNombre().equals("ACTIVA")) {
				if (ci.getContrasena().equals(t.getContra())) {
					l.setDescrip("Sin problemas");
					pk = new LogPK(now, t.getUsern(), "Login exitoso");
					l.setLogPK(pk);
					l.setCuentaApp(ci);
					l.setAccion1(new Accion("Login exitoso", ""));
					il.save(l);
					ci.setUltimoIngreso(now);
					ic.save(ci);
					return "Login Exitoso";
				} else {
					l.setDescrip("Contraseña erronea");
					pk = new LogPK(now, t.getUsern(), "Login fallido");
					l.setAccion1(ia.findById("Login fallido").get());
					l.setCuentaApp(ci);
					l.setLogPK(pk);
					il.save(l);
					return "Contraseña y/o Username Erroneos";
				}
			} else {
				l.setDescrip("cuenta bloqueada/suspendida");
				pk = new LogPK(now, t.getUsern(), "Login fallido");
				l.setAccion1(ia.findById("Login fallido").get());
				l.setCuentaApp(ci);
				l.setLogPK(pk);
				il.save(l);
				return "Cuenta bloqueada/suspendida";
			}
		} else {
			return "Contraseña y/o Username Erroenos";
		}
	}

	@GetMapping("/find/{id}")
	public CuentaApp findCuenta(@PathVariable("id") String id) {
		CuentaApp ci = findCuentaApp(id);
		if (ci != null) {
			return ci;
		} else {
			return null;
		}
	}

	@GetMapping("/logout/{id}")
	public Boolean logout(@PathVariable("id") String id) {
		CuentaApp ci = findCuentaApp(id);
		if (ci != null) {
			LocalDateTime now = obtenerfh();
			LogPK pk = new LogPK(now, id, "Logout Exitoso");
			Log l = new Log(pk, "Salida Normal");
			il.save(l);
			return true;
		} else {
			return false;
		}
	}

	public CuentaApp findCuentaApp(String id) {
		try {
			CuentaApp ci = ic.findById(id).get();
			if (ci != null) {
				return ci;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	private LocalDateTime obtenerfh() {
		return LocalDateTime.now();
	}

}

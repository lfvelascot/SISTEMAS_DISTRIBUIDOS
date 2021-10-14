package co.edu.usbbog.sd.rest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usbbog.sd.crypt.AesUtil;
import co.edu.usbbog.sd.model.Accion;
import co.edu.usbbog.sd.model.CuentaApp;
import co.edu.usbbog.sd.model.CuentaBancaria;
import co.edu.usbbog.sd.model.Log;
import co.edu.usbbog.sd.model.LogPK;
import co.edu.usbbog.sd.model.Transferencia;
import co.edu.usbbog.sd.model.TransferenciaBasic;
import co.edu.usbbog.sd.repository.ICuentaApp;
import co.edu.usbbog.sd.repository.ICuentaBancaria;
import co.edu.usbbog.sd.repository.ILog;
import co.edu.usbbog.sd.repository.ITransferencia;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

	@Autowired
	private ITransferencia it;
	@Autowired
	private ICuentaBancaria icb;
	@Autowired
	private ICuentaApp ica;
	@Autowired
	private ILog il;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private AesUtil codac = new AesUtil();
	private SesionController sc = new SesionController();
	

	@PostMapping("/create")
	public String guardarTransaccion(@RequestBody TransferenciaBasic t) {
		CuentaBancaria co = findCuentaBancaria(t.getCuentaOrigen()), cd = findCuentaBancaria(t.getCuentaDestino());
		CuentaApp ca = findCuentaApp(t.getCuentaApp());
		if (co != null && cd!=null) {
			if (ca != null) {
				LocalDateTime now = LocalDateTime.parse(LocalDateTime.now().format(formatter), formatter);
				LogPK pk = null;
				Log l = new Log();
				if (co.getEstadoCuenta().getNombre().equals("ACTIVA")
						&& cd.getEstadoCuenta().getNombre().equals("ACTIVA")) {
					if (co.getSaldo() >= t.getValor()) {
						Transferencia tr = new Transferencia(this.crearId(), t.getValor(), now);
						tr.setCuentaOrigen(co);
						tr.setCuentaDestino(cd);
						it.save(tr);
						l.setDescrip("Sin problemas");
						pk = new LogPK(now, t.getCuentaApp(), "Transferencia exitosa");
						l.setLogPK(pk);
						l.setCuentaApp(sc.findCuenta(t.getCuentaApp()));
						l.setAccion1(new Accion("Transferencia exitosa", ""));
						l.setDescrip("Sin problemas");
						il.save(l);
						co.setSaldo(co.getSaldo()-t.getValor());
						cd.setSaldo(cd.getSaldo()+t.getValor());
						icb.save(co);
						icb.save(cd);
						return "Transferencia exitosa," + tr.getIdTrans();
					} else {
						l.setDescrip("Saldo insuficiente");
						pk = new LogPK(now, t.getCuentaApp(), "Transferencia Fallida");
						l.setCuentaApp(sc.findCuenta(t.getCuentaApp()));
						l.setAccion1(new Accion("Transferencia fallida", ""));
						l.setLogPK(pk);
						il.save(l);
						return "Saldo insuficiente";
					}
				} else {
					l.setDescrip("Cuenta/as bloqueada/as");
					pk = new LogPK(now, t.getCuentaApp(), "Transferencia Fallida");
					l.setCuentaApp(sc.findCuenta(t.getCuentaApp()));
					l.setAccion1(new Accion("Transferencia fallida", ""));
					l.setLogPK(pk);
					il.save(l);
					return "Alguna de las cuentas se encuentra bloqueada o suspendida";
				}
			} else {
				return "Not Defined";
			}
		} else {
			return "Error cuentas";
		}
	}

	@GetMapping("/findtransacciones/{id}")
	public List<Transferencia> encontrarTransacciones(@PathVariable("id") String id) {
		List<Transferencia> l = it.findAll();
		if (l.isEmpty() || l.equals(null)) {
			return null;
		} else {
			for (int i = 0; i < l.size(); i++) {
				if (!l.get(i).getCuentaOrigen().getNum().equals(id) && !id.equals(l.get(i).getCuentaDestino().getNum())) {
					l.remove(i);
				}
			}
			if (l.isEmpty() || l.equals(null)) {
				return null;
			} else {
				return l;
			}
		}
	}

	private String crearId() {
		String theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789", vf = "";
		StringBuilder builder = new StringBuilder(3);
		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 5; m++) {
				int myindex = (int) (theAlphaNumericS.length() * Math.random());
				builder.append(theAlphaNumericS.charAt(myindex));
			}
			if (n == 2) {
				vf += builder.toString();
			} else {
				vf += builder.toString() + "-";
			}
		}
		return codac.encrypt(vf);
	}
	
	public CuentaBancaria findCuentaBancaria(String id) {
		try {
			CuentaBancaria ci = icb.findById(id).get();
			if (!ci.equals(null)) {
				return ci;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	public CuentaApp findCuentaApp(String id) {
		try {
			CuentaApp ci = ica.findById(id).get();
			if (ci != null) {
				return ci;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}


}

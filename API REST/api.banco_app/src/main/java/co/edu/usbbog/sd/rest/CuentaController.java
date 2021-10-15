package co.edu.usbbog.sd.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usbbog.sd.model.CuentaBancaria;
import co.edu.usbbog.sd.repository.ICuentaBancaria;

@RestController
@RequestMapping("/cuentab")
public class CuentaController {

	@Autowired
	private ICuentaBancaria icb;
	
	@GetMapping("/find/{id}")
	public CuentaBancaria obtenerEstado(@PathVariable("id")String id) {
		List<CuentaBancaria > l = icb.findAll();
		CuentaBancaria cb = null;
		if (l.isEmpty() || l.equals(null)) {
			return null;
		} else {
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).getUsuario().getNumDoc().equals(id)) {
					cb = l.get(i);
				}
			}
			if (cb == null) {
				return null;
			} else {
				return cb;
			}
		}


	}

}

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
		try {
			CuentaBancaria ci = icb.findById(id).get();
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

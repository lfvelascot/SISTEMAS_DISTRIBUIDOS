package co.edu.usbbog.sd.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sd.crypt.AesUtil;
import co.edu.usbbog.sd.model.CuentaBancaria;
import co.edu.usbbog.sd.repository.ICuentaBancaria;

@RestController
@RequestMapping("/cuentab")
public class CuentaController {

	@Autowired
	private ICuentaBancaria icb;
	private AesUtil codac = new AesUtil();
	
	@GetMapping("/find/{id}/{c}")
	public CuentaBancaria obtenerEstado(@PathVariable("id")String id,@PathVariable("c")String c) {
		List<CuentaBancaria > l = icb.findAll();
		CuentaBancaria cb = null;
		if (l.isEmpty() || l.equals(null)) {
			return null;
		} else {
			for (int i = 0; i < l.size(); i++) {
				if (l.get(i).getUsuario().getNumDoc().equals(id)) {
					cb = l.get(i);
					if (c == "c") {
						cb.setNum(codac.decrypt(cb.getNum()));
					}
				}
			}
			return cb;
		}
	}

}

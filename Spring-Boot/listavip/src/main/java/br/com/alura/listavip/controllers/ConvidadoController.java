package br.com.alura.listavip.controllers;

import br.com.alura.enviadorEmail.enviadorEmail.EmailService;
import br.com.alura.listavip.service.ConvidadoService;
import br.com.alura.listavip.model.Convidado;
import br.com.alura.listavip.repository.ConvidadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ConvidadoController {

    @Autowired
    private ConvidadoService service;

    @RequestMapping("/")
    public String index() {
        System.out.println("OLA");
        return "index";
    }

    @RequestMapping("listaconvidados")
    public String listaConvidados(Model model) {
        Iterable<Convidado> convidados = service.obterTodos();
        model.addAttribute("convidados", convidados);
        return "listaconvidados";
    }

    @RequestMapping(value = "salvar", method = RequestMethod.POST)
    public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
                         @RequestParam("telefone") String telefone) {

        Convidado convidado = new Convidado(nome, email, telefone);
        service.salvar(convidado);
        service.enviarEmail(nome, email);
        return "redirect:listaconvidados";
    }
}

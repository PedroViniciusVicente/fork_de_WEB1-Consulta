package org.consulta.controller;

import org.consulta.domain.Consulta;
import org.consulta.service.spec.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping("/listagemConsultas")
    public String listagemConsultas(Model model) {
        List<Consulta> listaConsultas = consultaService.buscarTodos();
        model.addAttribute("listaConsultas", listaConsultas);
        return "logado/consultas/listagemConsultas";
    }

    @GetMapping("/criarConsulta")
    public String criarConsultaForm(@RequestParam("cpf") String cpf, Model model) {
        model.addAttribute("consulta", new Consulta());
        return "logado/consultas/criarConsulta";
    }

    @PostMapping("/criarConsulta")
    public String criarConsulta(Consulta consulta, RedirectAttributes redirectAttributes) {
        boolean isValid = consultaService.checkValidity(consulta.getCrm(), consulta.getCpf(), consulta.getDataHora());
        if (!isValid) {
            redirectAttributes.addFlashAttribute("errorMessage", "Consulta inválida: já existe uma consulta marcada para este horário.");
            return "redirect:/consultas/criarConsulta";
        }
        consultaService.salvar(consulta);
        return "redirect:/consultas/listagemConsultas";
    }

    @GetMapping("/editarConsulta/{id}")
    public String editarConsultaForm(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta == null) {
            return "redirect:/consultas/listagemConsultas";
        }
        model.addAttribute("consulta", consulta);
        return "logado/consultas/editarConsulta";
    }

    @PostMapping("/editarConsulta")
    public String editarConsulta(Consulta consulta, RedirectAttributes redirectAttributes) {
        boolean isValid = consultaService.checkValidity(consulta.getCrm(), consulta.getCpf(), consulta.getDataHora());
        if (!isValid) {
            redirectAttributes.addFlashAttribute("errorMessage", "Consulta inválida: já existe uma consulta marcada para este horário.");
            return "redirect:/consultas/editarConsulta/" + consulta.getId();
        }
        consultaService.atualizar(consulta);
        return "redirect:/consultas/listagemConsultas";
    }

    @GetMapping("/deletarConsulta/{id}")
    public String deletarConsulta(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        consultaService.excluir(id);
        return "redirect:/consultas/listagemConsultas";
    }

    @GetMapping("/consultasPorCpf")
    public String listarConsultasPorCpf(@RequestParam("cpf") String cpf, Model model) {
        List<Consulta> listaConsultas = consultaService.buscarPorCpf(cpf);
        model.addAttribute("listaConsultas", listaConsultas);
        model.addAttribute("cpf", cpf);
        return "logado/consultas/listagemConsultas";
    }

    @GetMapping("/consultasPorCrm")
    public String listarConsultasPorCrm(@RequestParam("crm") String crm, Model model) {
        List<Consulta> listaConsultas = consultaService.buscarPorCrm(crm);
        model.addAttribute("listaConsultas", listaConsultas);
        model.addAttribute("crm", crm);
        return "logado/consultas/listagemConsultas";
    }
}

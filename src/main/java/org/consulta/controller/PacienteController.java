package org.consulta.controller;

import org.consulta.domain.Paciente;
import org.consulta.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping("/listagemPacientes")
    public String listagemPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        model.addAttribute("listaPacientes", pacientes);
        return "logado/pacientes/listagemPacientes";
    }

    @GetMapping("/criarPacientes")
    public String criarPacientesForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "logado/pacientes/criarPacientes";
    }

    @PostMapping("/criarPacientes")
    public String criarPacientes(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes redirectAttributes) {
        if (pacienteService.buscarPorCpf(paciente.getCpf()) != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Um paciente com esse CPF já existe");
            return "redirect:/pacientes/criarPacientes";
        }
        pacienteService.salvar(paciente);
        return "redirect:/pacientes/listagemPacientes";
    }

    @GetMapping("/editarPacientes/{id}")
    public String editarPacientesForm(@PathVariable("id") Long id, Model model) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente == null) {
            return "logado/pacientes/listagemPacientes";
        }
        model.addAttribute("paciente", paciente);
        return "logado/paciente/editarPacientes";
    }

    @PostMapping("/editarPacientes")
    public String editarPacientes(@ModelAttribute("paciente") Paciente paciente, RedirectAttributes redirectAttributes) {
        pacienteService.atualizar(paciente);
        return "redirect:/pacientes/listagemPacientes";
    }

    @GetMapping("/deletarPacientes/{id}")
    public String deletarPacientes(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        pacienteService.excluir(id);
        return "redirect:/pacientes/listagemPacientes";
    }
}

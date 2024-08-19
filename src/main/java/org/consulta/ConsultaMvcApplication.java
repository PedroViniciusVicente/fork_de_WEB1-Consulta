package org.consulta;

import org.consulta.domain.Paciente;
import org.consulta.domain.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.consulta.dao.IMedicoDAO;
import org.consulta.dao.IPacienteDAO;
import org.consulta.dao.IConsultaDAO;
import org.consulta.dao.IUsuarioDAO;
import org.consulta.domain.Medico;
import org.consulta.domain.Usuario;
import org.consulta.domain.Consulta;
import org.consulta.domain.Paciente;


@SpringBootApplication
public class ConsultaMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultaMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder, IConsultaDAO consultaDAO, IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO) {
        return (args) -> {

            Usuario u1 = new Usuario();
            u1.setUsername("admin");
            u1.setEmail("admin@admin.com");
            u1.setPassword(encoder.encode("admin"));
            u1.setCpf("012.345.678-90");
            u1.setName("Administrador");
            u1.setRole("ROLE_ADMIN");
            u1.setEnabled(true);
            usuarioDAO.save(u1);

            Medico m1 = new Medico();
            m1.setUsername("medicojoao");
            m1.setEmail("medicoJoao@email.com");
            m1.setPassword("senhadojoao");
            m1.setCrm("SP-36730");
            m1.setName("Dr. João");
            m1.setEspecialidade("Cardiologia");
            medicoDAO.save(m1);
            Usuario mu1 = new Usuario();
            mu1.setUsername("medicojoao");
            mu1.setPassword(encoder.encode("senhadojoao"));
            mu1.setCpf("SP-36730");
            mu1.setName("Dr. João");
            mu1.setRole("ROLE_MEDICO");
            mu1.setEnabled(true);
            usuarioDAO.save(mu1);


            Medico m2 = new Medico();
            m2.setEmail("medicoDaniel@email.com");
            m2.setPassword("senhadodaniel");
            m2.setCrm("SC-86399");
            m2.setName("Dr. Daniel");
            m2.setEspecialidade("Cardiologia");
            medicoDAO.save(m2);
            
            Usuario u2 = new Usuario();
            u2.setUsername("beltrano");
            u2.setEmail("beltrano@email.com");
            u2.setPassword(encoder.encode("123"));
            u2.setCpf("985.849.614-10");
            u2.setName("Beltrano Andrade");
            u2.setRole("ROLE_PACIENTE");

            u2.setEnabled(true);
            usuarioDAO.save(u2);
            

            Medico m3 = new Medico();
            m3.setEmail("medicaMaria@email.com");
            m3.setPassword("senhadamaria");
            m3.setCrm("AM-45082");
            m3.setName("Dra. Maria");
            m3.setEspecialidade("Pediatria");
            medicoDAO.save(m3);
            
            Usuario u3 = new Usuario();
            u3.setUsername("fulano");
            u3.setEmail("fulano@email.com");
            u3.setPassword(encoder.encode("123"));
            u3.setCpf("367.318.380-04");
            u3.setName("Fulano Silva");
            u3.setRole("ROLE_MEDICO");
            u3.setEnabled(true);
            usuarioDAO.save(u3);

            Medico m4 = new Medico();
            m4.setEmail("medicoPedro@email.com");
            m4.setPassword("senhadopedro");
            m4.setCrm("PR-54321");
            m4.setName("Dr. Pedro");
            m4.setEspecialidade("Ortopedia");
            medicoDAO.save(m4);
            Usuario u4 = new Usuario();
            u4.setUsername("medicopedro");
            u4.setPassword(encoder.encode("senhadopedro"));
            u4.setCpf("PR-54321");
            u4.setName("Dr. Pedro");
            u4.setRole("ROLE_MEDICO");
            u4.setEnabled(true);
            usuarioDAO.save(u4);

            Medico m5 = new Medico();
            m5.setEmail("medicaLarissa@email.com");
            m5.setPassword("senhadalarissa");
            m5.setCrm("RJ-18093");
            m5.setName("Dra. Larissa");
            m5.setEspecialidade("Neurologia");
            medicoDAO.save(m5);
            Usuario u5 = new Usuario();
            u5.setUsername("medicalarissa");
            u5.setPassword(encoder.encode("senhadalarissa"));
            u5.setCpf("RJ-18093");
            u5.setName("Dra. Larissa");
            u5.setRole("ROLE_MEDICO");
            u5.setEnabled(true);
            usuarioDAO.save(u5);

            Medico m6 = new Medico();
            m6.setEmail("medicoGuilherme@email.com");
            m6.setPassword("senhadoguilherme");
            m6.setCrm("MG-48773");
            m6.setName("Dr. Guilherme");
            m6.setEspecialidade("Neurologia");
            medicoDAO.save(m6);
            Usuario u6 = new Usuario();
            u6.setUsername("medicoguilherme");
            u6.setPassword(encoder.encode("senhadoguilherme"));
            u6.setCpf("MG-48773");
            u6.setName("Dr. Guilherme");
            u6.setRole("ROLE_MEDICO");
            u6.setEnabled(true);
            usuarioDAO.save(u6);
            
            Paciente p1 = new Paciente();
            p1.setEmail("pacienteRafael@mail");
            p1.setSenha("senhadorafael");
            p1.setCpf("45545678901");
            p1.setNome("Rafael");
            p1.setTelefone("551617348261");
            p1.setSexo("M");
            p1.setDataNascimento("1993-07-13");
            pacienteDAO.save(p1);
            Usuario u7 = new Usuario();
            u7.setUsername("pacienterafael");
            u7.setPassword(encoder.encode("senhadorafael"));
            u7.setCpf("45545678901");
            u7.setName("Rafael");
            u7.setRole("ROLE_PACIENTE");
            u7.setEnabled(true);
            usuarioDAO.save(u7);

            Paciente p2 = new Paciente();
            p2.setEmail("pacienteJoana@email.com");
            p2.setSenha("senhadajoana");
            p2.setCpf("97772012614");
            p2.setNome("Joana");
            p2.setTelefone("5511930910782");
            p2.setSexo("F");
            p2.setDataNascimento("2000-01-15");
            pacienteDAO.save(p2);
            Usuario u8 = new Usuario();
            u8.setUsername("pacientejoana");
            u8.setPassword(encoder.encode("senhadajoana"));
            u8.setCpf("97772012614");
            u8.setName("Joana");
            u8.setRole("ROLE_PACIENTE");
            u8.setEnabled(true);
            usuarioDAO.save(u8);

            Paciente p3 = new Paciente();
            p3.setEmail("pacienteAna@email.com");
            p3.setSenha("senhadaana");
            p3.setCpf("54321678901");
            p3.setNome("Ana");
            p3.setTelefone("551198766271");
            p3.setSexo("F");
            p3.setDataNascimento("1988-10-10");
            pacienteDAO.save(p3);
            Usuario u9 = new Usuario();
            u9.setUsername("pacienteana");
            u9.setPassword(encoder.encode("senhadaana"));
            u9.setCpf("54321678901");
            u9.setName("Ana");
            u9.setRole("ROLE_PACIENTE");
            u9.setEnabled(true);
            usuarioDAO.save(u9);

            Paciente p4 = new Paciente();
            p4.setEmail("pacienteGabriel@email.com");
            p4.setSenha("senhadogabriel");
            p4.setCpf("48829471629");
            p4.setNome("Gabriel");
            p4.setTelefone("551473129401");
            p4.setSexo("M");
            p4.setDataNascimento("2004-10-28");
            pacienteDAO.save(p4);
            Usuario u10 = new Usuario();
            u10.setUsername("pacientegabriel");
            u10.setPassword(encoder.encode("senhadogabriel"));
            u10.setCpf("48829471629");
            u10.setName("Gabriel");
            u10.setRole("ROLE_PACIENTE");
            u10.setEnabled(true);
            usuarioDAO.save(u10);

            Consulta c1 = new Consulta();
            c1.setCpf("45545678901");
            c1.setCrm("SP-36730");
            c1.setDataHora("2024-07-22T15:30");
            consultaDAO.save(c1);

            Consulta c2 = new Consulta();
            c2.setCpf("97772012614");
            c2.setCrm("SP-36730");
            c2.setDataHora("2024-07-22T21:00");
            consultaDAO.save(c2);

            Consulta c3 = new Consulta();
            c3.setCpf("97772012614");
            c3.setCrm("AM-45082");
            c3.setDataHora("2024-07-22T16:30");
            consultaDAO.save(c3);

            Consulta c4 = new Consulta();
            c4.setCpf("54321678901");
            c4.setCrm("PR-54321");
            c4.setDataHora("2024-07-23T18:00");
            consultaDAO.save(c4);
            
        };
    }
}
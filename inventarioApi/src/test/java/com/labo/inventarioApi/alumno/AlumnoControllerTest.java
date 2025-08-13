package com.labo.inventarioApi.alumno;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import static com.labo.inventarioApi.appUser.AppUserRole.USER;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//
//@WebMvcTest(AlumnoController.class)
//public class AlumnoControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private AlumnoService alumnoService;
//
//    @WithMockUser
//    @Test
//    public void testRegistrarNuevoAlumno() throws Exception {
//        Alumno alumno = new Alumno(44555666, "Pepe", "Alvarez", "pepe.alvarez@example.com", "lalala", USER);
//
//        mockMvc.perform(post("/api/v1/alumno")
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(alumno)))
//               .andExpect(status().isOk());
//    }
//    
//    @WithMockUser
//    @Test
//    public void testObtenerAlumnos() throws Exception {
//        mockMvc.perform(get("/api/v1/alumno"))
//               .andExpect(status().isOk());
//    }
//
//    @WithMockUser
//    @Test
//    public void testEliminarAlumno() throws Exception {
//        Long alumnoId = 1L;
//
//        mockMvc.perform(delete("/api/v1/alumno/{alumnoId}", alumnoId)
//                .with(csrf()))
//               .andExpect(status().isOk());
//    }
//
//}

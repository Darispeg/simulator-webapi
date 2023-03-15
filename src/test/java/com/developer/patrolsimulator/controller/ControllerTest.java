package com.developer.patrolsimulator.controller;

import com.developer.patrolsimulator.repository.*;
import com.developer.patrolsimulator.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@AutoConfigureMockMvc
@WithMockUser
public class ControllerTest {
    @Autowired protected MockMvc mockMvc;
    @MockBean protected UserService userService;
    @MockBean protected UserRepository userRepository;
    @MockBean protected UserController userController;

    @MockBean protected MapService mapService;
    @MockBean protected MapRepository mapRepository;
    @MockBean protected MapController mapController;

    @MockBean protected PatrolsService patrolsService;
    @MockBean protected PatrolRepository patrolRepository;
    @MockBean protected PatrolController patrolController;

    @MockBean protected RecognitionService recognitionService;
    @MockBean protected RecognitionRepository recognitionRepository;
    @MockBean protected RecognitionController recognitionController;

    @MockBean protected RecognitionResultRepository resultRepository;
    @MockBean protected RecognitionResultService resultService;
    @MockBean protected RecognitionResultController resultController;

    @Autowired protected ObjectMapper objectMapper;

    @MockBean protected AuthController authController;
}

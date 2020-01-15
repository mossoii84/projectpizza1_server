package projectpizza1_server.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projectpizza1_server.demo.repository.RepositoryClient;

@RestController
@RequestMapping("/api/client")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class ControllerClient {

@Autowired
private RepositoryClient repositoryClient;






}

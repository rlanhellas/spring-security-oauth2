package com.example.resourceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/needscope")
    @PreAuthorize("#oauth2.hasScope('transfer-money')")
    public String needscope() {
        return "Congrats, you are in, with correct scopes";
    }

    /**
    * Apenas para flows que possuem autenticação de usuário, neste caso a ROLE do usuário é inserida no token JWT
    * */
    @GetMapping("/needscoperoles")
    @PreAuthorize("#oauth2.hasScope('transfer-money') and hasRole('ROLE_OWNER')")
    public String needscoperoles() {
        return "Congrats, you are in, with correct scopes and roles";
    }

    /**
     * Apenas para flows que possuem autenticação de usuário, neste caso a ROLE do usuário é inserida no token JWT
     * */
    @GetMapping("/needscope_onlyforemployee")
    @PreAuthorize("#oauth2.hasScope('transfer-money') and hasRole('ROLE_EMPLOYEE')")
    public String needscopeOnlyForEmployee() {
        return "Congrats Employee, you are in, with correct scopes";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "This is a public endpoint, everyone can access it. You're not special, sorry :(";
    }

}

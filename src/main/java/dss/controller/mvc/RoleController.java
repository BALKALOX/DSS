package dss.controller.mvc;

import dss.model.entity.Role;
import dss.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
@AllArgsConstructor
public class RoleController {

    private RoleService roleService;

    @GetMapping
    public String roles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles/list";
    }

    @GetMapping("/create")
    public String create(Model model) {

        return "roles/create";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.getRole(id));
        return "roles/edit";
    }
}

package com.hei.school.endpoint.rest.controller;

import com.hei.school.service.HazavaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HazavaoController {

    private final HazavaoService hazavaoService;

    @GetMapping("/hazavao")
    public String hazavao(@RequestParam String teny) {
        return hazavaoService.getDefinitionInMalagasy(teny);
    }
}

package com.gamepleconnect.branch.controller;

import com.gamepleconnect.branch.domain.CountryRestrictions;
import com.gamepleconnect.branch.dto.request.CountryCodeGetRequest;
import com.gamepleconnect.branch.dto.request.CountryRestrictionsRequest;
import com.gamepleconnect.branch.dto.response.IpGeolocationApiResponse;
import com.gamepleconnect.branch.service.BranchService;
import com.gamepleconnect.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Branch", description = "브랜치 - 브랜치 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/country/code")
    public ApiResponse<IpGeolocationApiResponse> getCountryCodeByIp(@ModelAttribute @Valid CountryCodeGetRequest request) {
        return branchService.getCountryCodeByIp(request);
    }

    @GetMapping("/country/restrictions")
    public ApiResponse<List<CountryRestrictions>> getRestrictionsByCountry(@ModelAttribute @Valid CountryRestrictionsRequest request) {
        return branchService.getRestrictionsByCountry(request);
    }
}

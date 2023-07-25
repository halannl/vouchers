package com.halan.vouchersweb.voucher;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class VoucherController {

    @Operation(summary = "Obter todos os Vouchers", tags = { "vouchers", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "Não existem Vouchers", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/vouchers")
    List<Integer> all() {
        return new ArrayList<>(Arrays.asList(1,2));
    }
}

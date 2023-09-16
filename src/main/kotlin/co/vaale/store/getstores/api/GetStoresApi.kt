package co.vaale.store.getstores.api

import co.com.groupware.common.api.model.ApiRequestResponse
import co.com.groupware.common.api.model.ApiResponseList
import co.vaale.store.shared.data.StoreData
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import javax.annotation.processing.Generated


@Generated(
    value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"],
    date = "2023-09-12T20:37:39.723409686Z[GMT]"
)
@Validated
interface VaaleStoreServicesApi {
    @Operation(
        summary = "get stores list",
        description = "Gets a list of stores with filtering and pagination options.",
        security = [SecurityRequirement(name = "stores_auth", scopes = ["store.read:store", "store.write:store"])],
        tags = ["Stores"]
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "successful operation",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ApiResponseList::class)
                )
            )
        ), ApiResponse(
            responseCode = "400", description = "Invalid status value",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ApiRequestResponse::class)
                )
            )
        ), ApiResponse(
            responseCode = "401",
            description = "Unauthorized",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ApiRequestResponse::class)
                )
            )
        ), ApiResponse(
            responseCode = "405", description = "Invalid input",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ApiRequestResponse::class)
                )
            )
        ), ApiResponse(
            responseCode = "500",
            description = "Server error",
            content = arrayOf(
                Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ApiRequestResponse::class)
                )
            )
        ),
        ]
    )
    @RequestMapping(
        value = ["/stores"],
        produces = ["application/json"],
        method = [RequestMethod.GET]
    )
    fun getStores(
        @Parameter(`in` = ParameterIn.QUERY, description = "", schema = Schema()) @Valid @RequestParam(
            value = "category_id",
            required = false
        ) category: Int?,
        @Parameter(`in` = ParameterIn.QUERY, description = "", schema = Schema()) @RequestParam(
            value = "name",
            required = false
        ) name: @Valid String?,
        @Parameter(`in` = ParameterIn.QUERY, description = "", schema = Schema()) @Valid @RequestParam(
            value = "city_id",
            required = false
        ) city: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "It allows to perform the requested operation on the content * `1` INITIAL PAGE * `2` NEXT PAGE * `3` PREVIOUS PAGE * `4` LAST PAGE * `5` MOVE TO PAGE SENDING * `6` CURRENT PAGE ",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationAction", required = false) apiPaginationAction: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationLimit", required = false) apiPaginationLimit: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationOrderColumn", required = false) apiPaginationOrderColumn: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "Address in which the requested records are ordered * `0` ASC * `1` DESC ",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationDirection", required = false) apiPaginationDirection: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "When this parameter is sent, it has priority and the action that accompanies it is not applied.. ",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationMoveToPage", required = false) apiPaginationMoveToPage: Int?,
        @Parameter(
            `in` = ParameterIn.QUERY,
            description = "This element is the key to being able to move forward and backward since it is the reference position.    ",
            schema = Schema()
        ) @Valid @RequestParam(value = "ApiPaginationCurrentPage", required = false) apiPaginationCurrentPage: Int?
    ): ResponseEntity<ApiResponseList<StoreData>?>?
}


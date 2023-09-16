package co.vaale.store.getstores.api

import co.com.groupware.common.api.model.ApiPagination
import co.com.groupware.common.api.model.ApiRequestResponse
import co.com.groupware.common.api.model.ApiResponseList
import co.com.groupware.common.utils.ConstantsUtil
import co.vaale.store.shared.data.StoreData
import co.vaale.store.getstores.application.port.input.GetStoresUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import javax.annotation.processing.Generated

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2023-09-05T18:30:00.920554886Z[GMT]")
@RestController
class VaaleStoreServicesApiController : VaaleStoreServicesApi {

   private val log: Logger = LoggerFactory.getLogger(VaaleStoreServicesApiController::class.java)

    @Autowired
    private lateinit var getStoresUseCase: GetStoresUseCase

  override fun getStores(
    category: Int?,
    name: String?,
    city: Int?,
    apiPaginationAction: Int?,
    apiPaginationLimit: Int?,
    apiPaginationOrderColumn: Int?,
    apiPaginationDirection: Int?,
    apiPaginationMoveToPage: Int?,
    apiPaginationCurrentPage: Int?
): ResponseEntity<ApiResponseList<StoreData>?>?  {

        val response: ApiResponseList<StoreData> = ApiResponseList<StoreData>()
        val apiResponse = ApiRequestResponse()
        apiResponse.code = 200
        apiResponse.type = "FILTER"
        apiResponse.message = "Find"
        apiResponse.systemMessage = ""
        apiResponse.stackTrace = ""

        val apiPagination = ApiPagination()
        apiPagination.action = apiPaginationAction
        apiPagination.limit = apiPaginationLimit ?: ConstantsUtil.DEFAULT_LIMIT
        apiPagination.orderColumn = apiPaginationOrderColumn ?: ConstantsUtil.DEFAULT_ORDER_COLUMN
        apiPagination.orderDireccion = apiPaginationDirection ?: ConstantsUtil.DEFAULT_ORDER_COLUMN_DIRECTION
        apiPagination.moveToPage = apiPaginationMoveToPage
        apiPagination.currentPage = apiPaginationCurrentPage

        try {
            val listDTO = getStoresUseCase.invoke(name,city,category,apiPagination)
            response.list = listDTO?.list
            response.pagination = listDTO?.apiPagination
            response.setApiResponse(apiResponse)
        } catch (e: Exception) {
            apiResponse.code = 500
            apiResponse.message = e.message
            response.list = ArrayList<StoreData>()
            response.apiResponse = apiResponse
            response.setPagination(apiPagination)
        }

        return ResponseEntity<ApiResponseList<StoreData>?>(response, HttpStatus.OK)
    }
}

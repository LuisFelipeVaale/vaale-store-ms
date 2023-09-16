package co.vaale.store.getstores.application.port.input

import co.com.groupware.common.api.model.ApiPagination
import co.com.groupware.common.api.model.ApiResponseListDTO
import co.vaale.store.shared.data.StoreData


interface GetStoresUseCase {
    fun invoke(name: String?, city: Int?, category: Int?, apiPagination: ApiPagination?): ApiResponseListDTO<StoreData?>?
}

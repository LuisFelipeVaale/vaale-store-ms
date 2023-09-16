package co.vaale.store.getstores.domain.services

import co.com.groupware.common.api.model.ApiPagination
import co.com.groupware.common.api.model.ApiResponseListDTO
import co.com.groupware.common.utils.FunctionsUtil
import co.vaale.store.getstores.application.port.input.GetStoresUseCase
import co.vaale.store.getstores.domain.repositories.IStoreRepository
import co.vaale.store.shared.data.StoreData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

@Service
class GetStoresService : GetStoresUseCase {

    @Autowired
    private lateinit var storeRepository: IStoreRepository

    override fun invoke(name: String?,city: Int?, category: Int?, apiPagination: ApiPagination?) : ApiResponseListDTO<StoreData?>? {
        val listDTO : ApiResponseListDTO<StoreData?>? = ApiResponseListDTO<StoreData?>()
        try {
            val count: Int = storeRepository.countByFilter(
                IStoreRepository.Filter(
                    name,
                    city,
                    category
                ), null
            )
            FunctionsUtil.processPagination(count, apiPagination)
            val list: List<StoreData> = storeRepository.getWithPagination(
                apiPagination,
                IStoreRepository.Filter(
                    name,
                    city,
                    category,
                ),
                null
            )
            listDTO?.apiPagination = apiPagination
            listDTO?.list = list
        } catch (e: SQLException) {
            throw Exception(e.cause)
        }
        return listDTO
    }
}
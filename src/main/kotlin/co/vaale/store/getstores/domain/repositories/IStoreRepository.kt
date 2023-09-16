package co.vaale.store.getstores.domain.repositories

import co.com.groupware.common.domain.Query
import co.vaale.store.shared.data.StoreData
import lombok.EqualsAndHashCode
import lombok.Value

interface IStoreRepository : Query<Long, StoreData, IStoreRepository.Filter> {

    @Value
    @EqualsAndHashCode(callSuper = false)
    data class Filter (
        var name: String? = null,
        var city: Int? = null,
        var category: Int? = null
    )
}
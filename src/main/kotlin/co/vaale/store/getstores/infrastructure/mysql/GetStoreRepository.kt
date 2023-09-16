package co.vaale.store.getstores.infrastructure.mysql

import co.com.groupware.common.api.model.ApiPagination
import co.vaale.store.getstores.application.port.output.IStoreRepositoryPort
import co.vaale.store.getstores.domain.repositories.IStoreRepository
import co.vaale.store.getstores.infrastructure.jooq.StoreTable
import co.vaale.store.shared.data.StoreData
import org.jooq.*
import org.jooq.impl.DSL
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.SQLException


@Repository
class GetStoreRepository : IStoreRepositoryPort {

    @Autowired
    private lateinit var dsl: DSLContext

    override fun getById(id: Long?, schema: String?): StoreData {
        var `object`: StoreData? = null
        try {
            val objectDb: Record10<Long, String, Int, Int, String, String, String, String, Double, Double>? =
                dsl.select(
                    StoreTable.ID.`as`("id"),
                    StoreTable.NAME.`as`("name"),
                    StoreTable.CATEGORY.`as`("category"),
                    StoreTable.CITY.`as`("city"),
                    StoreTable.IMAGE.`as`("image"),
                    StoreTable.PHONE.`as`("phone"),
                    StoreTable.EMAIL.`as`("email"),
                    StoreTable.ADDRESS.`as`("address"),
                    StoreTable.LAT.`as`("lat"),
                    StoreTable.LON.`as`("lon"),
                )
                    .from(StoreTable.STORES)
                    .where(StoreTable.ID.eq(id))
                    .fetchAny()
            if (objectDb != null) {
                `object` = objectDb.into(StoreData::class.java)
            }
        } catch (e: java.lang.Exception) {
            throw SQLException(e.cause)
        }

        return `object`!!
    }

    override fun get(filter: IStoreRepository.Filter?, schema: String?): MutableList<StoreData> {
        TODO("Not yet implemented")
    }

    override fun getWithPagination(
        apiPagination: ApiPagination?,
        filter: IStoreRepository.Filter?,
        schema: String?
    ): MutableList<StoreData> {
        var list: List<StoreData?>
        try {
            //Definir las condiciones del Where
            val where = whereByFilter(filter)


            //Definición del orden
            val orderBy = arrayOf<SortField<*>>(DSL.inline(apiPagination!!.orderColumn).asc())
            if (apiPagination!!.orderDireccion == 1) {
                orderBy[0] = DSL.inline(apiPagination!!.orderColumn).desc()
            }
            list = dsl.select(
                StoreTable.ID.`as`("id"),
                StoreTable.NAME.`as`("name"),
                StoreTable.CATEGORY.`as`("category"),
                StoreTable.CITY.`as`("city"),
                StoreTable.IMAGE.`as`("image"),
                StoreTable.PHONE.`as`("phone"),
                StoreTable.EMAIL.`as`("email"),
                StoreTable.ADDRESS.`as`("address"),
                StoreTable.LAT.`as`("lat"),
                StoreTable.LON.`as`("lon"),
            ) // Use plain SQL as aliased tables (be aware of syntax!)
                .from(StoreTable.STORES)
                .where(where) // Use plain SQL again as fields in GROUP BY and ORDER BY clauses
                .orderBy(*orderBy)
                .limit(apiPagination!!.limit)
                .offset(apiPagination!!.offset)
                .fetchInto(StoreData::class.java)
        } catch (e: Exception) {
            throw SQLException(e.cause)
        }

        return list
    }

    override fun countByFilter(filter: IStoreRepository.Filter?, schema: String?): Int {
        var count = 0
        count = try {
            //Definir las condiciones del Where
            val where = whereByFilter(filter)
            dsl.selectCount()
                .from(StoreTable.STORES)
                .where(where)
                .fetchOne<Int>(0, Int::class.javaPrimitiveType)!!
        } catch (e: java.lang.Exception) {
            throw SQLException(e.cause)
        }

        return count
    }

    private fun whereByFilter(filter: IStoreRepository.Filter?): Condition? {
        var where: Condition = DSL.trueCondition()
        if (!filter?.name.isNullOrEmpty()) {
            where = where.and(StoreTable.NAME.like("%" + filter?.name + "%"))
        }
        if (filter?.city!= null) {
            where = where.and(StoreTable.CITY.eq(filter.city))
        }
        if (filter?.category != null) {
            where = where.and(StoreTable.CATEGORY.eq(filter.category))
        }

        return where
    }
}
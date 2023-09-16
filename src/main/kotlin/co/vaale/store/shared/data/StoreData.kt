package co.vaale.store.shared.data


    /**
     *
     * @param id Identificador de la tienda
     * @param name Nombre de la tienda
     * @param category Categoría de la tienda
     * @param city Ciudad de la tienda
     * @param image URL de la imagen de la tienda
     * @param phone Número de teléfono de la tienda
     * @param email Dirección de correo electrónico de la tienda
     * @param address Dirección de la tienda
     * @param lat Latitud de la ubicación de la tienda
     * @param lon Longitud de la ubicación de la tienda
     */
    data class StoreData (

        /* Identificador de la tienda */
        val id: kotlin.Long? = null,
        /* Nombre de la tienda */
        val name: kotlin.String? = null,
        /* Categoría de la tienda */
        val category: kotlin.Int? = null,
        /* ciudad de la tienda */
        val city: kotlin.Int? = null,
        /* URL de la imagen de la tienda */
        val image: kotlin.String? = null,
        /* Número de teléfono de la tienda */
        val phone: kotlin.String? = null,
        /* Dirección de correo electrónico de la tienda */
        val email: kotlin.String? = null,
        /* Dirección de la tienda */
        val address: kotlin.String? = null,
        /* Latitud de la ubicación de la tienda */
        val lat: kotlin.Double? = null,
        /* Longitud de la ubicación de la tienda */
        val lon: kotlin.Double? = null
    ) {
    }
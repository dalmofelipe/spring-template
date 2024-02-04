package com.dalmofelipe.springtemplate.records;

import java.math.BigInteger;

public record LocationRecord(
    String ip,
    String network,
    String version,
    String city,
    String region,
    String region_code,
    String country,
    String country_name,
    String country_code,
    String country_code_iso3,
    String country_capital,
    String country_tld,
    String continent_code,
    Boolean in_eu,
    Integer postal,
    Integer latitude,
    Integer longitude,
    String timezone,
    String utc_offset,
    String country_calling_code,
    String currency,
    String currency_name,
    String languages,
    Integer country_area,
    BigInteger country_population,
    String asn,
    String org
) {}

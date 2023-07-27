package com.xodud1202.kotlinapi.config

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategy
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import java.util.*

class UpperCaseNamingStrategy : PhysicalNamingStrategy {
    override fun toPhysicalColumnName(columnName: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier {
        return Identifier.toIdentifier(columnName?.text?.toUpperCase(Locale.ROOT))
    }

    override fun toPhysicalTableName(tableName: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier {
        return Identifier.toIdentifier(tableName?.text?.toUpperCase(Locale.ROOT))
    }

    override fun toPhysicalSchemaName(schemaName: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? {
        return schemaName
    }

    override fun toPhysicalCatalogName(catalogName: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? {
        return catalogName
    }

    override fun toPhysicalSequenceName(sequenceName: Identifier?, jdbcEnvironment: JdbcEnvironment?): Identifier? {
        return sequenceName
    }
}
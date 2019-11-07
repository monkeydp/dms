package com.monkeydp.daios.dms.sdk.mocker

/**
 * @author iPotato
 * @date 2019/11/5
 */
object InstrJsonMocker {
    const val SELECTED = "TABLE"
    const val INSTR = """{"action":"NEW","target":"TABLE"}"""
    const val NEW_TABLE_USER_INPUT =
            """
                {
                  "name": "test_table",
                  "columns": [
                    {
                      "name": "id",
                      "dataType": "int",
                      "size": 11,
                      "constraints": [
                        "NOT NULL",
                        "PRIMARY KEY"
                      ]
                    },
                    {
                      "name": "name",
                      "dataType": "varchar",
                      "size": 64,
                      "constraints": [
                        "NOT NULL"
                      ]
                    },
                    {
                      "name": "age",
                      "dataType": "tinyint",
                      "size": 4,
                      "constraints": [
                        "NOT NULL"
                      ]
                    }
                  ]
                }
            """
}
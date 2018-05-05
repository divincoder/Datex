package com.datex.datex.model;

/**
 * Created by Francis Ilechukwu 09/04/2018.
 */

class DatexDBContract {

    static class Patients {
        static String TABLE_NAME = "patients";
        static String ID = "_id INTEGER PRIMARY KEY";
        static String FIRST_NAME = "first_name VARCHAR(30) NOT NULL";
        static String MIDDLE_NAME = "middle_name VARCHAR(30)";
        static String LAST_NAME = "last_name VARCHAR(30) NOT NULL";
        static String DOB = "dob VARCHAR(10) NOT NULL";
        static String SEX = "sex VARCHAR(1) NOT NULL";
        static String ADDRESS = "address VARCHAR(50)";
        static String STATE_OF_ORIGIN = "state_of_origin INTEGER NOT NULL";
        static String PHONE_NO = "phone_no VARCHAR(15) NOT NULL";
        static int ID_INDEX = 0;
        static int FIRST_NAME_INDEX = 1;
        static int MIDDLE_NAME_INDEX = 2;
        static int LAST_NAME_INDEX = 3;
        static int DOB_INDEX = 4;
        static int SEX_INDEX = 5;
        static int ADDRESS_INDEX = 6;
        static int STATE_OF_ORIGIN_INDEX = 7;
        static int PHONE_NO_INDEX = 8;
        static String[] CONFIG = {ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, DOB, SEX, ADDRESS, STATE_OF_ORIGIN, PHONE_NO};
    }

    static class GlycemicDataTable {
        static String TABLE_NAME = "glycemic_data";
        static String ID = "_id INTEGER PRIMARY KEY";
        static String PATIENT_ID = "patient_id INTEGER UNIQUE NOT NULL";
        static String BMI = "bmi TEXT NOT NULL";
        static String RBG = "rbg TEXT NOT NULL";
        static String HBA1C = "hba1c TEXT NOT NULL";
        static String MEDICATION = "medication TEXT";
        static String DIAGNOSIS = "diagnosis TEXT";
        static String LAST_UPDATE_TIME = "last_update_time VARCHAR(19)";
        static int ID_INDEX = 0;
        static int PATIENT_ID_INDEX = 1;
        static int BMI_INDEX = 2;
        static int RBG_INDEX = 3;
        static int HBA1C_INDEX = 4;
        static int MEDICATION_INDEX = 5;
        static int DIAGNOSIS_INDEX = 6;
        static int LAST_UPDATE_TIME_INDEX = 6;
        // TODO: FOREIGN KEYS HERE.
        static String[] CONFIG = {ID, PATIENT_ID, BMI, RBG, HBA1C, MEDICATION, DIAGNOSIS, LAST_UPDATE_TIME};
    }

    static class CoronaryRiskFactorTable {
        static String TABLE_NAME = "coronary_risk_factor";
        static String ID = "_id INTEGER PRIMARY KEY";
        static String PATIENT_ID = "patient_id INTEGER UNIQUE NOT NULL";
        static String TOTAL_CHOLESTEROL = "total_cholesterol INTEGER NOT NULL";
        static String TRYGLYCERIDES = "tryglycerides TEXT NOT NULL";
        static String HDL_C = "hdl_c TEXT NOT NULL";
        static String LDL_C = "ldl_c TEXT NOT NULL";
        static String BP = "bp TEXT NOT NULL";
        static String LAST_UPDATE_TIME = "last_update_time VARCHAR(19)";
        static int ID_INDEX = 0;
        static int PATIENT_ID_INDEX = 1;
        static int TOTAL_CHOLESTEROL_INDEX = 2;
        static int TRYGLYCERIDES_INDEX = 3;
        static int HDL_C_INDEX = 4;
        static int LDL_C_INDEX = 5;
        static int BP_INDEX = 6;
        static int LAST_UPDATE_TIME_INDEX = 7;
        // TODO: FOREIGN KEYS HERE.
        static String[] CONFIG = {ID, PATIENT_ID, TOTAL_CHOLESTEROL, TRYGLYCERIDES, HDL_C, LDL_C, BP, LAST_UPDATE_TIME};
    }

    static class DBContract {
        static String createTable(String name, String[] configs, String constraints) {
            StringBuilder sql = new StringBuilder("CREATE TABLE ").append(name).append(" (");
            for (String column : configs) {
                sql.append(column).append(", ");
            }
            if (!constraints.equals("")) {
                sql.append(constraints);
            } else {
                sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",") + 2, "");
            }
            sql.append(");");
            return sql.toString();
        }

        public static String foreign(String column, String table, String tableColumn) {
            column = column.contains(" ") ? getName(column) : column;
            tableColumn = tableColumn.contains(" ") ? getName(tableColumn) : tableColumn;
            return "FOREIGN KEY (" + column + ") REFERENCES " + table + "(" + tableColumn + ")";
        }

        /**
         * returns name part of column config
         *
         * @param config column config e.g. first_ name Varchar(15)
         * @return column name.
         */
        static String getName(String config) {
            return config.substring(0, config.indexOf(" "));
        }

        public static String[] getColumnNames(String[] configs) {
            String[] colNames = new String[configs.length];
            int l = configs.length;
            for (int x = 0; x < l; x++) {
                colNames[x] = getName(configs[x]);
            }
            return colNames;
        }

        static String chainOrs(String column, int[] values) {
            if (values.length > 0) {
                StringBuilder ors = new StringBuilder(column).append(" = ").append(values[0]).append(" ");
                for (int x = 1; x < values.length; x++) {
                    ors.append(" OR ").append(column).append(" = ").append(values[x]).append(" ");
                }
                return ors.toString().trim();
            }
            return "";
        }

    }
}

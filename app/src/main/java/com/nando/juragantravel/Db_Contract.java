package com.nando.juragantravel;
public class Db_Contract {

    // Variabel konstan untuk menyimpan alamat IP server
    public static String ip = "192.168.1.45";

    // URL untuk proses registrasi pengguna
    public static final String urlRegister = "http://"+ip+"/latihan-php-unit/latihan/user_reg.php" ;

    // URL untuk proses login pengguna
    public static final String urlLogin = "http://"+ip+"/latihan-php-unit/latihan/log.php";
    public static final String urlUser = "http://"+ip+"/latihan-php-unit/latihan/user.php";

}

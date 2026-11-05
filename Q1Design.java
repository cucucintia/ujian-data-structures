// Rancangan akhir soal 1a: interface kecil + abstract class dasar
interface LihatJadwal { void lihatJadwal(); }
interface Login { void login(String user, String pass); }
interface PenulisResep { void tulisResep(String obat); }
interface PencatatVital { void catatTandaVital(String data); }

abstract class PetugasMedis implements LihatJadwal, Login {
    protected final String nama;
    protected PetugasMedis(String nama) { this.nama = nama; }
    @Override public void login(String user, String pass) {
        System.out.println(nama + " login sebagai " + user);
    }
}

class Dokter extends PetugasMedis implements PenulisResep {
    Dokter(String nama) { super(nama); }
    @Override public void lihatJadwal() { System.out.println("Jadwal praktik dr. " + nama); }
    @Override public void tulisResep(String obat) { System.out.println("Resep: " + obat); }
}

class Perawat extends PetugasMedis implements PencatatVital {
    Perawat(String nama) { super(nama); }
    @Override public void lihatJadwal() { System.out.println("Jadwal shift " + nama); }
    @Override public void catatTandaVital(String data) { System.out.println("Vital: " + data); }
}

// Soal 1c: enkapsulasi RekamMedis
class RekamMedis {
    private String diagnosis;
    private String resep;

    public String getDiagnosis(PenulisResep dokter) { return diagnosis; }
    public void setDiagnosisResep(PenulisResep dokter, String diagnosis, String resep) {
        this.diagnosis = diagnosis;
        this.resep = resep;
    }
}

public class Q1Design {
    public static void main(String[] args) {
        Dokter d = new Dokter("Andi");
        Perawat p = new Perawat("Sari");
        d.login("andi", "x"); d.lihatJadwal(); d.tulisResep("Paracetamol");
        p.login("sari", "x"); p.lihatJadwal(); p.catatTandaVital("TD 120/80");
        RekamMedis rm = new RekamMedis();
        rm.setDiagnosisResep(d, "Demam", "Paracetamol");
        System.out.println(rm.getDiagnosis(d));
    }
}

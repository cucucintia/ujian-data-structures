/**
 * Riwayat kunjungan pasien berbasis Doubly Linked List buatan sendiri.
 * Tidak memakai java.util.LinkedList / Collections Framework.
 */
public class RiwayatKunjungan {

    // Node buatan sendiri: 2 pointer (prev & next)
    private static class Node {
        String catatan;
        Node prev;
        Node next;

        Node(String catatan) {
            this.catatan = catatan;
        }
    }

    private Node head;     // kunjungan paling lama
    private Node tail;     // kunjungan paling baru
    private Node saatIni;  // pointer "saat ini" (kursor navigasi)
    private int ukuran;

    // Tambah di akhir (O(1) karena ada pointer tail) lalu pindahkan "saat ini" ke node baru
    public void tambahKunjungan(String catatan) {
        Node baru = new Node(catatan);
        if (head == null) {
            head = tail = baru;
        } else {
            tail.next = baru;
            baru.prev = tail;
            tail = baru;
        }
        saatIni = baru;
        ukuran++;
    }

    // Mundur ke kunjungan sebelumnya, O(1)
    public void sebelumnya() {
        if (saatIni == null) {
            System.out.println("  [!] Riwayat kosong.");
        } else if (saatIni.prev == null) {
            System.out.println("  [!] Sudah di kunjungan paling awal.");
        } else {
            saatIni = saatIni.prev;
        }
    }

    // Maju ke kunjungan berikutnya, O(1)
    public void berikutnya() {
        if (saatIni == null) {
            System.out.println("  [!] Riwayat kosong.");
        } else if (saatIni.next == null) {
            System.out.println("  [!] Sudah di kunjungan paling akhir.");
        } else {
            saatIni = saatIni.next;
        }
    }

    public void tampilkanSaatIni() {
        if (saatIni == null) {
            System.out.println("  Kunjungan saat ini: (kosong)");
        } else {
            System.out.println("  Kunjungan saat ini: " + saatIni.catatan);
        }
    }

    // Untuk soal 3c: hapus node yang sudah diketahui referensinya, O(1)
    private void hapusNode(Node x) {
        if (x.prev != null) x.prev.next = x.next; else head = x.next;
        if (x.next != null) x.next.prev = x.prev; else tail = x.prev;
        if (saatIni == x) saatIni = (x.next != null) ? x.next : x.prev;
        x.prev = null;
        x.next = null;
        ukuran--;
    }

    // Hapus kunjungan yang sedang ditampilkan (demo soal 3c)
    public void hapusSaatIni() {
        if (saatIni != null) hapusNode(saatIni);
    }

    // Cetak seluruh isi dari head ke tail, untuk verifikasi struktur
    public void cetakSemua() {
        StringBuilder sb = new StringBuilder("  Isi list: ");
        Node p = head;
        while (p != null) {
            sb.append(p == saatIni ? "[" + p.catatan + "]" : p.catatan);
            if (p.next != null) sb.append(" <-> ");
            p = p.next;
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        RiwayatKunjungan r = new RiwayatKunjungan();

        System.out.println("== Tambah 4 kunjungan ==");
        r.tambahKunjungan("Kunjungan 1 - Demam");
        r.tambahKunjungan("Kunjungan 2 - Kontrol");
        r.tambahKunjungan("Kunjungan 3 - Cek lab");
        r.tambahKunjungan("Kunjungan 4 - Resep ulang");
        r.tampilkanSaatIni();
        r.cetakSemua();

        System.out.println("== Mundur (1) ==");
        r.sebelumnya();
        r.tampilkanSaatIni();

        System.out.println("== Mundur (2) ==");
        r.sebelumnya();
        r.tampilkanSaatIni();

        System.out.println("== Maju (1) ==");
        r.berikutnya();
        r.tampilkanSaatIni();
        r.cetakSemua();

        System.out.println("== Tambahan (soal 3c): hapus kunjungan saat ini ==");
        r.hapusSaatIni();
        r.tampilkanSaatIni();
        r.cetakSemua();
    }
}
